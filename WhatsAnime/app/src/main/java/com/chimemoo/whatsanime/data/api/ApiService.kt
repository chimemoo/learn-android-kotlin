package com.chimemoo.whatsanime.data.api

import com.chimemoo.whatsanime.data.model.Request
import com.chimemoo.whatsanime.data.model.Response
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService : IApiService {
    override fun postImage(request: Request): Single<Response> {
        return Rx2AndroidNetworking.post("https://trace.moe/api/search")
            .addBodyParameter(request)
            .build()
            .getObjectSingle(Response::class.java)
    }
}