package com.chimemoo.alwayshungry.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimemoo.alwayshungry.data.model.Image
import com.chimemoo.alwayshungry.data.repository.MainRepository
import com.chimemoo.alwayshungry.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val image = MutableLiveData<Resource<Image>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchImage()
    }

    private fun fetchImage() {
        image.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ imageData ->
                    image.postValue(Resource.success(imageData))
                }, { t: Throwable? ->
                    image.postValue(Resource.error("Something when wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getImage() : LiveData<Resource<Image>> {
        return image
    }

    fun getRefreshedImage() {
        return fetchImage()
    }

}