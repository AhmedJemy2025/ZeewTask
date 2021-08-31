package com.app.feature.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.extensions.ObservableString
import com.app.core.networking.NetworkState
import com.app.core.networking.repositiories.AuthRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.Observables
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    val repository: AuthRepository, ) : ViewModel() {

    val networkState = MutableLiveData<NetworkState>()
    val errorField = MutableLiveData<String>()
    val userName = ObservableString("")
    val password = ObservableString("")


    fun login() {




        Observable.combineLatest(
            Observable.just(userName.get()),
            Observable.just(password.get()),
            { mail, pass ->
                return@combineLatest isValidEmail(mail.toString()) && pass.toString().length >5
            }).subscribe { isvalid ->
            if (isvalid) {

                networkState.postValue(NetworkState.Loading())
                viewModelScope.launch(
                    CoroutineExceptionHandler { _, t ->
                        networkState.postValue(NetworkState.Error(errorMessage = t.message.toString()))
                    }) {
                    val response =
                        repository.login(username = userName.get(), password = password.get())
                    if (response.result.success == 1) {
                        networkState.postValue(NetworkState.Success(userToken = response.result.token))
                    } else {
                        networkState.postValue(NetworkState.Error(errorMessage = response.result.message))
                    }

                }

            } else {
                errorField.postValue("please enter right values")
            }
        }


    }

     val EMAIL_PATTERN =
        "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"




    private fun isValidEmail(mail :String): Boolean {
        val pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN)
        return pattern.matcher(mail).matches()
    }


}
