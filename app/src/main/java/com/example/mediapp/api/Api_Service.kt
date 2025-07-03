package com.example.mediapp.api

import com.example.mediapp.models.AddOrderResponse
import com.example.mediapp.models.CreateUserResponse
import com.example.mediapp.models.GetProductResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api_Service {
    @FormUrlEncoded
    @POST("createUser")
    suspend fun createUser(
        @Field("name") name :String,
        @Field("password") password :String,
        @Field("email") email :String,
        @Field ("address") address : String,
        @Field ("phoneNumber") phoneNumber: String,
        @Field ("pinCode") pinCode :String

    ) : Response <CreateUserResponse>

    @FormUrlEncoded
    @POST("getSpecificUser")
    suspend fun getSpecificUser(

        @Field("user_id") user_id :String

    ) : Response<CreateUserResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email :String,
        @Field("password") password :String,
    ) : Response<CreateUserResponse>

    @GET("getAllProduct")
    suspend fun getAllProduct() : Response<GetProductResponse>

    @FormUrlEncoded
    @POST("addOrderDetails")
    suspend fun addOrderDetails(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id:  String,
        @Field("product_name") product_name: String,
        @Field("user_name") user_name : String,
        @Field("total_amount") total_amount : Float,
        @Field("quantity") quantity : Int,
        @Field("message") message : String,
        @Field("price") price : Float,
        @Field("category") category : String
    ) : Response<AddOrderResponse>




}