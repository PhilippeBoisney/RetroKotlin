package io.github.philippeboisney.retrokotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.philippeboisney.retrokotlin.di.OBSERVER_ON
import io.github.philippeboisney.retrokotlin.di.SUBCRIBER_ON
import io.github.philippeboisney.retrokotlin.model.User
import io.github.philippeboisney.retrokotlin.repository.UserRepository
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

private const val FANTASTIC_USER_TO_FETCH = "JakeWharton"

class UserViewModel @Inject constructor(private val userRepository: UserRepository,
                                        @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
                                        @param:Named(OBSERVER_ON) private val observerOn: Scheduler): BaseViewModel() {

    // FOR DATA
    val user: MutableLiveData<User?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    // INIT
    init { this.isLoading.value = false }

    // STREAM

    /**
     * Fetch user's information on Github API
     */
    fun getUser(){
        this.disposable.addAll(this.userRepository.getUser(FANTASTIC_USER_TO_FETCH)
                .subscribeOn(subscriberOn)
                .observeOn(observerOn)
                .doOnSubscribe { this.isLoading.value = true }
                .doOnComplete { this.isLoading.value = false }
                .doOnError { this.isLoading.value = false }
                .subscribe ({ this.user.value = it }, { this.errorMessage.value = it.message }))
    }
}