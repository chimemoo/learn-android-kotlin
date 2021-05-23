package com.chimemoo.whatsanime.data.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("image")
    val image: String
)
