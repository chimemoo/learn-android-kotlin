package com.chimemoo.alwayshungry.data.api

import com.chimemoo.alwayshungry.data.model.Image
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService : ApiServiceInterface {
    override fun getImage(): Single<Image> {
        return Rx2AndroidNetworking.get("https://foodish-api.herokuapp.com/api/")
            .build()
            .getObjectSingle(Image::class.java)
    }
}