package com.chimemoo.whatsanime.data.model

import com.google.gson.annotations.SerializedName

data class Response(
//    "RawDocsCount": 3555648,
//    "RawDocsSearchTime": 14056,
//    "ReRankSearchTime": 1182,
//    "CacheHit": false,
//    "trial": 1,
//    "limit": 9,
//    "limit_ttl": 60,
//    "quota": 150,
//    "quota_ttl": 86400,
    @SerializedName("RawDocsCount")
    val rawDocsCount: Int = 0,

    @SerializedName("RawDocsSearchTime")
    val rawDocsSearchTime: Int = 0,

    @SerializedName("ReRankSearchTime")
    val reRankSearchTime: Int = 0,

    @SerializedName("CacheHit")
    val cacheHit: Boolean = false,

    @SerializedName("trial")
    val trial: Int = 0,

    @SerializedName("limit")
    val limit: Int = 0,

    @SerializedName("quota")
    val quota: Int = 0,

    @SerializedName("quota_ttl")
    val quotaTtl: Int = 0,

    @SerializedName("docs")
    val docs: Array<Docs>,
)
