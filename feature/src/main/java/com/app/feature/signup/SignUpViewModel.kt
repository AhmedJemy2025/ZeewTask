package com.app.feature.signup

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


class SignUpViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel() {


    val networkState = MutableLiveData<NetworkState>()
    val errorField = MutableLiveData<String>()
    val mail = ObservableString("")
    val password = ObservableString("")
    val confirmPassword = ObservableString("")
    val mobileNumber = ObservableString("")
    val countryCode = ObservableString("")
    val firstName = ObservableString("")
    val lastName = ObservableString("")
    val referralCode = ObservableString("")


    fun signUp() {

        // i didn't find how to combine more than 3 elements so I created an array
        Observables.combineLatest(
            Observable.just(
                arrayOf(
                    mail.get(),
                    password.get(),
                    confirmPassword.get(),
                    mobileNumber.get(),
                    firstName.get(),
                    lastName.get(),
                    referralCode.get(),
                )
            ), Observable.just("1")

        ) { a, _ ->
            return@combineLatest isValidEmail(a[0]) && a[1].length > 5 && a[2].length > 5 && a[1] == a[2] && isValidPhone(
                a[3]
            ) && a[4].length > 3 && a[5].length > 3
        }.subscribe { valid ->
            if (valid) {
                networkState.postValue(NetworkState.Loading())
                viewModelScope.launch(
                    CoroutineExceptionHandler { _, t ->
                        networkState.postValue(NetworkState.Error(errorMessage = t.message.toString()))
                    }) {
                    val response =
                        repository.signUp(
                            username = mail.get(),
                            password = password.get(),
                            firstName = firstName.get(),
                            lastName = lastName.get(),
                            phoneNumber = countryCode.get()+mobileNumber.get(),
                            referralCode = referralCode.get()
                        )
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


    fun isValidEmail(mail: String): Boolean {
        val pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN)
        return pattern.matcher(mail).matches()
    }

    val allowedLength = 10

    private fun isValidPhone(value: String): Boolean {
        return value.matches("^[0-9]*\$".toRegex()) && value.length == allowedLength && value.startsWith(
            "1"
        )
    }
}
