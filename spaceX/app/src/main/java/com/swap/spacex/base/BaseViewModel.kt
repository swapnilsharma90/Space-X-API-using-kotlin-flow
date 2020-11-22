package com.swap.spacex.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    lateinit var coordinator: BaseCoordinator

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}