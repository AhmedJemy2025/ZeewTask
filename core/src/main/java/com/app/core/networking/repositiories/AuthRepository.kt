package com.app.core.networking.repositiories

import android.util.Log
import com.app.core.networking.api.AuthAPI
import com.app.core.networking.responses.UserResponse

class AuthRepository(
    private val service: AuthAPI
) {

    suspend fun login(username: String, password: String): UserResponse {




            return service.userLogin(
                username = username,
                password = password,
                deviceId = "FCM_TOKEN",
                deviceType = "ANDROID"
            )

    }

    suspend fun signUp(
        username: String,
        password: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        referralCode: String
    ): UserResponse {

        try {
            return service.userSignUp(
                username = username,
                password = password,
                action = "CustomerSignUp",
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                referralCode = referralCode
            );

        } catch (e: Exception) {
            Log.e("ERROR", e.toString());
        }
        return service.userSignUp(
            username = username,
            password = password,
            action = "CustomerSignUp",
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber, referralCode = referralCode
        );
    }


}
