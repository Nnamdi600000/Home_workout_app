package com.codennamdi.homeworkoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codennamdi.homeworkoutapp.databinding.ActivityExerciseBinding
import com.codennamdi.homeworkoutapp.databinding.CustomDialogBackComfirmationBinding
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    // - Adding a variables for the 10 seconds REST timer
    //START
    private var restTimer: CountDownTimer? =
        null // Variable for Rest Timer and later on we will initialize it.
    private var restProgress =
        0 // Variable for timer progress. As initial value the rest progress is set to 0. As we are about to start.
    //END

    // Adding a variables for the 30 seconds Exercise timer
    // START
    private var exerciseTimer: CountDownTimer? =
        null // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseProgress =
        0 // Variable for the exercise timer progress. As initial value the exercise progress is set to 0. As we are about to start.

    // END
    private var exerciseTimerDuration: Long = 1

    // The Variable for the exercise list and current position of exercise here it is -1 as the list starting element is 0
    // START
    private var exerciseList: ArrayList<ExerciseModel>? = null // We will initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.

    private var restTimeDuration: Long = 1

    // END
    // create a binding variable
    private var binding: ActivityExerciseBinding? = null

    private lateinit var exerciseTextToSpeech: TextToSpeech
    private lateinit var restPlayer: MediaPlayer

    private lateinit var exerciseAdapter: ExerciseStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseTextToSpeech = TextToSpeech(this, this)
        //inflate the layout
        binding = ActivityExerciseBinding.inflate(layoutInflater)

        // pass in binding?.root in the content view
        setContentView(binding?.root)
        // then set support action bar and get toolBar Exercise using the binding

        //variable
        setSupportActionBar(binding?.toolBarExercise)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener {
            displayBackBtnCustomDialog()
        }
        //Initializing and Assigning a default exercise list to our list variable
        // START
        exerciseList = Constants.defaultExerciseList()
        // END
        setupRestView()
        setUpExerciseStatusRecyclerView()
    }

    private fun displayBackBtnCustomDialog() {
        val customDialog = Dialog(this)
        val dialogBinding = CustomDialogBackComfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)

        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()

            exerciseTextToSpeech.stop()
            exerciseTextToSpeech.shutdown()

            if (restTimer != null) {
                restTimer?.cancel()
                restProgress = 0
            }
        }

        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setUpExerciseStatusRecyclerView() {

        binding?.recyclerViewExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.recyclerViewExerciseStatus?.adapter = exerciseAdapter
    }

    //Setting up the Get Ready View with 10 seconds of timer
    //START
    /**
     * Function is used to set the timer for REST.
     */
    private fun setupRestView() {
        //Added a media player
        try {
            val soundURL =
                Uri.parse(
                    "android.resource://com.codennamdi.homeworkoutapp/" + R.raw.whistle_sound_effect
                )
            restPlayer = MediaPlayer.create(applicationContext, soundURL)
            restPlayer.isLooping = false
            restPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // TODO (Step 3- changing the upcoming exercise label and name visibility.)
        binding?.frameLayoutProgressbar?.visibility = View.VISIBLE
        binding?.textViewTitle?.visibility = View.VISIBLE
        binding?.upcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.frameLayoutProgressbarExercise?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        // TODO (Step 2 - Setting the upcoming exercise name in the UI element.)
        // START
        // Here we have set the upcoming exercise name to the text view
        // Here as the current position is -1 by default so to selected from the list it should be 0 so we have increased it by +1.
        binding?.tvUpcomingExerciseName?.text =
            exerciseList!![currentExercisePosition + 1].getName()

        speakOutExercise(exerciseList!![currentExercisePosition + 1].getName())

        // This function is used to set the progress details.
        setRestProgressBar()
    }
    // END

    // Setting up the 10 seconds timer for rest view and updating it continuously.
    //START
    /**
     * Function is used to set the progress of timer using the progress
     */
    private fun setRestProgressBar() {

        binding?.progressBar?.progress =
            restProgress // Sets the current progress to the specified value.

        /**
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(restTimeDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased by 1
                binding?.progressBar?.progress =
                    10 - restProgress // Indicates progress bar progress
                binding?.textViewTimer?.text =
                    (10 - restProgress).toString()  // Current progress is set to text view in terms of seconds.
            }

            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter.notifyDataSetChanged()
                setupExerciseView()
            }
        }.start()
    }
    //END

    // Setting up the Exercise View with a 30 seconds timer
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View.
     */
    private fun setupExerciseView() {
// TODO (Step 4- changing the upcoming exercise label and name visibility.)
        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        binding?.frameLayoutProgressbar?.visibility = View.INVISIBLE
        binding?.textViewTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.frameLayoutProgressbarExercise?.visibility = View.VISIBLE
        binding?.textViewTimerExercise?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE

        /**
         * Here firstly we will check if the timer is running and it is not null then cancel the running timer and start the new one.
         * And set the progress to the initial value which is 0.
         */
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        // Setting up the current exercise name and imageview to the UI element.
        // START
        /**
         * Here current exercise name and image is set to exercise view.
         */
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()

        speakOutExercise(exerciseList!![currentExercisePosition].getName())
        // END
        setExerciseProgressBar()
    }
    // END

    // After REST View Setting up the 30 seconds timer for the Exercise view and updating it continuously
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View for 30 Seconds
     */
    private fun setExerciseProgressBar() {

        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress =
                    exerciseTimerDuration.toInt() - exerciseProgress
                binding?.textViewTimerExercise?.text =
                    (exerciseTimerDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsComplete(true)
                exerciseAdapter.notifyDataSetChanged()

                // Updating the view after completing the 30 seconds exercise
                // START
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    setupRestView()
//                    if (currentExercisePosition > exerciseList?.size!!) {
//                        val intent = Intent(this@ExerciseActivity, ExerciseEndScreen::class.java)
//                        startActivity(intent)
//                    }
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                    val intent = Intent(this@ExerciseActivity, ExerciseEndScreen::class.java)
                    startActivity(intent)
                }
                // END
            }
        }.start()
    }
    // END

    private fun speakOutExercise(text: String) {
        exerciseTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = exerciseTextToSpeech.setLanguage(Locale.UK)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("Text to speech", "The language specified is not supported")
            } else {
                Log.e("Text to speech", "Initialization failed")
            }
        }
    }

    override fun onBackPressed() {
        displayBackBtnCustomDialog()
       // super.onBackPressed()
        finish()
        exerciseTextToSpeech.stop()
        exerciseTextToSpeech.shutdown()

        restPlayer.pause()
        restPlayer.stop()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
    }

    override fun onPause() {
        super.onPause()
        exerciseTextToSpeech.stop()
        exerciseTextToSpeech.shutdown()

        restPlayer.pause()
        restPlayer.stop()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        finish()
    }

    override fun onStop() {
        super.onStop()

        exerciseTextToSpeech.stop()
        exerciseTextToSpeech.shutdown()

        restPlayer.pause()
        restPlayer.stop()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        finish()
    }

    // Destroying the timer when closing the activity or app
    //START
    /**
     * Here in the Destroy function we will reset the rest timer if it is running.
     */
    public override fun onDestroy() {
        super.onDestroy()
        binding = null

        exerciseTextToSpeech.stop()
        exerciseTextToSpeech.shutdown()

        restPlayer.pause()
        restPlayer.stop()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        finish()
    }
    //END
}