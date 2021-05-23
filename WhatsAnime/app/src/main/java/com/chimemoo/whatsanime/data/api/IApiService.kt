package com.chimemoo.whatsanime.data.api

import com.chimemoo.whatsanime.data.model.Request
import com.chimemoo.whatsanime.data.model.Response
import io.reactivex.Single

interface IApiService {
    fun postImage(request: Request) : Single<Response>
}