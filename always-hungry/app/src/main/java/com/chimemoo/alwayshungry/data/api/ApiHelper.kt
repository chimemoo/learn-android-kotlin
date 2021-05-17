package com.chimemoo.alwayshungry.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getImage() = apiService.getImage()
}