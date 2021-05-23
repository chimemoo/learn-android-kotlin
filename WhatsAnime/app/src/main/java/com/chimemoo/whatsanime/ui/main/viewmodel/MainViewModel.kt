package com.chimemoo.whatsanime.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chimemoo.whatsanime.data.model.Response
import com.chimemoo.whatsanime.data.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable
import com.chimemoo.whatsanime.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository): ViewModel(){
    private val responses = MutableLiveData<Resource<Response>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchImage(image: String) {
        responses.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.postImage(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    responses.postValue(Resource.success(response))
                }, { throwable ->
                    responses.postValue(Resource.error("Something when wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun postImage(): LiveData<Resource<Response>> {
        return responses
    }
}