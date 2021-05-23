package com.chimemoo.whatsanime.data.repository

import com.chimemoo.whatsanime.data.api.ApiHelper
import com.chimemoo.whatsanime.data.model.Request
import com.chimemoo.whatsanime.data.model.Response
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun postImage(image: String): Single<Response> {
        val request = Request(image)
        return apiHelper.postImage(request)
    }

}