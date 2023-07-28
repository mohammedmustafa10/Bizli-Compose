package com.example.bottomsheet2

import androidx.annotation.DrawableRes

data class OnboardingData(
    val title: String,
    val subTitle:String,
    @DrawableRes val imageResourceId:Int)

//Here the @DrawableRes is the annotation that tells the compiler
// that the value of the imageResourceId will be a drawable resource.