package com.app.core.networking.api


import com.app.core.networking.responses.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthAPI {


    @FormUrlEncoded
    @POST("/v1/CustomerLogin")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("device_id") deviceId: String,
        @Field("device_type") deviceType: String
    ): UserResponse


    @FormUrlEncoded
    @POST("/v1/CustomerSignUp")
    suspend fun userSignUp(
        @Field("action") action: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("phone_number") phoneNumber: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("referral_code") referralCode: String
    ): UserResponse
}
