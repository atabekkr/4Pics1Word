package com.example.fourpicsoneword.data

import androidx.annotation.DrawableRes

data class Question(
    val id: Int,
    @DrawableRes val images: List<Int>,
    val answer: String
)
