package com.codennamdi.homeworkoutapp

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping jacks",
            R.drawable.jumping_jack,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val inclinePushUp = ExerciseModel(
            2,
            "Incline pushup",
            R.drawable.incline_pushups,
            false,
            false
        )
        exerciseList.add(inclinePushUp)

        val declinePushUp = ExerciseModel(
            3,
            "Decline pushup",
            R.drawable.decline_pushup,
            false,
            false
        )
        exerciseList.add(declinePushUp)

        val kneePushUp = ExerciseModel(
            4,
            "Knee Pushup",
            R.drawable.knee_pushup,
            false,
            false
        )
        exerciseList.add(kneePushUp)

        val wideArmPushUp = ExerciseModel(
            5,
            "Wide Arm Pushup",
            R.drawable.wide_arm_pushup,
            false,
            false
        )
        exerciseList.add(wideArmPushUp)

        val pushUp = ExerciseModel(
            6,
            "PushUp",
            R.drawable.pushup,
            false,
            false
        )
        exerciseList.add(pushUp)

        val cobraStretch = ExerciseModel(
            7,
            "Cobra stretch",
            R.drawable.cobra_stretch,
            false,
            false
        )
        exerciseList.add(cobraStretch)

        val chestStretch = ExerciseModel(
            8,
            "Chest stretch",
            R.drawable.chest_stretch,
            false,
            false
        )
        exerciseList.add(chestStretch)

        val diamondPushUp = ExerciseModel(
            9,
            "Diamond Pushup",
            R.drawable.diamond_pushup,
            false,
            false
        )
        exerciseList.add(diamondPushUp)

        val abdominalCrunches = ExerciseModel(
            10,
            "Abdominal Crunches",
            R.drawable.abdominal_crunches,
            false,
            false
        )
        exerciseList.add(abdominalCrunches)

        val mountainClimber = ExerciseModel(
            11,
            "Mountain Climber",
            R.drawable.mountain_climber,
            false,
            false
        )
        exerciseList.add(mountainClimber)

        val heelTouch = ExerciseModel(
            12,
            "Heel Touch",
            R.drawable.heel_touch,
            false,
            false
        )
        exerciseList.add(heelTouch)

        val legRaise = ExerciseModel(
            13,
            "Leg Raise",
            R.drawable.leg_raises,
            false,
            false
        )
        exerciseList.add(legRaise)

        val plank = ExerciseModel(
            14,
            "Plank",
            R.drawable.plank,
            false,
            false
        )
        exerciseList.add(plank)

        return exerciseList
    }
}