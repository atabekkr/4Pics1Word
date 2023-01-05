package com.example.fourpicsoneword.data

import com.example.fourpicsoneword.R

object Constants {

    const val PREFS = "fourPicsOneWordPrefs"
    const val LEVEL = "currentLevel"
    const val CYCLE = "levelCycle"

    fun getQuestion(): List<Question> {
        return listOf(
            Question(
                id = 1,
                images = listOf(
                    R.drawable.photo1_1,
                    R.drawable.photo1_2,
                    R.drawable.photo1_3,
                    R.drawable.photo1_4
                ),
                answer = "ХОЛОД"
            ),Question(
                id = 2,
                images = listOf(
                    R.drawable.photo2_1,
                    R.drawable.photo2_2,
                    R.drawable.photo2_3,
                    R.drawable.photo2_4
                ),
                answer = "ГРОМКО"
            ),Question(
                id = 3,
                images = listOf(
                    R.drawable.photo3_2,
                    R.drawable.photo3_3,
                    R.drawable.photo3_4,
                    R.drawable.photo3_1,
                ),
                answer = "ГОРЯЧО"
            ),Question(
                id = 4,
                images = listOf(
                    R.drawable.photo4_1,
                    R.drawable.photo4_2,
                    R.drawable.photo4_3,
                    R.drawable.photo4_4
                ),
                answer = "МУЗЫКА"
            ),Question(
                id = 5,
                images = listOf(
                    R.drawable.photo5_1,
                    R.drawable.photo5_2,
                    R.drawable.photo5_3,
                    R.drawable.photo5_4
                ),
                answer = "ТОЧКА"
            ),

        )
    }
}