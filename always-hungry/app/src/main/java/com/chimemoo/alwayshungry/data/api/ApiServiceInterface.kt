package com.chimemoo.alwayshungry.data.api

import com.chimemoo.alwayshungry.data.model.Image
import io.reactivex.Single

interface ApiServiceInterface {
    fun getImage(): Single<Image>
}