package com.chimemoo.whatsanime.data.model

import com.google.gson.annotations.SerializedName

data class Docs(
    @SerializedName("is_adult")
    val isAdult: Boolean = false,

    @SerializedName("synonyms_chinese")
    val synonyms_chinese: Array<String>,

    @SerializedName("synonyms")
    val synonyms: Array<String>,

    @SerializedName("mal_id")
    val malId: String = "",

    @SerializedName("title_romaji")
    val titleRomaji: String = "",

    @SerializedName("title_english")
    val titleEnglish: String = "",

    @SerializedName("title_chinese")
    val titleChinese: String = "",

    @SerializedName("title_native")
    val titleNative: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("tokenthumb")
    val tokenthumb: String = "",

    @SerializedName("anime")
    val anime: String = "",

    @SerializedName("season")
    val season: String = "",

    @SerializedName("at")
    val at: Float = 0f,

    @SerializedName("anilist_id")
    val anilistId: Int = 0,

    @SerializedName("filename")
    val fileName: String = "",

    @SerializedName("episode")
    val episode: Int = 0,

    @SerializedName("from")
    val from: Float = 0f,

    @SerializedName("to")
    val to: Float = 0f,

    @SerializedName("similarity")
    val similarity: Float = 0f,

)
