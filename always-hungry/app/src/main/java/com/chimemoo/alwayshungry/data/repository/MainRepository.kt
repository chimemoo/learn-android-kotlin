package com.chimemoo.alwayshungry.data.repository

import com.chimemoo.alwayshungry.data.api.ApiHelper
import com.chimemoo.alwayshungry.data.model.Image
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
    fun getImage(): Single<Image> {
        return apiHelper.getImage()
    }
}