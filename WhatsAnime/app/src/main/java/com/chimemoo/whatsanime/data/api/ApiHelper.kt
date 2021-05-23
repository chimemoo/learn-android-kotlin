package com.chimemoo.whatsanime.data.api

import com.chimemoo.whatsanime.data.model.Request

class ApiHelper(private val apiService: ApiService) {
    fun postImage(request: Request) = apiService.postImage(request)
}