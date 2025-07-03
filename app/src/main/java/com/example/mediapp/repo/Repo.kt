package com.example.mediapp.repo

import com.example.mediapp.api.Api_Builder
import com.example.mediapp.common.Results
import com.example.mediapp.models.CreateUserResponse
import com.example.mediapp.models.GetProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repo @Inject constructor(private val apiBuilder:Api_Builder){
    suspend fun createUser(
        name: String,
        password: String,
        email: String,
        address: String,
        phoneNumber: String,
        pinCode: String

    ):Flow<Results<Response<CreateUserResponse>>> = flow{
        emit(Results.Loading)
        try{
            val response = apiBuilder.api.createUser(
                name,
                password,
                email,
                address,
                phoneNumber,
                pinCode
            )
            emit(Results.Success(response))

        } catch (e:Exception){
            emit(Results.Error(message = e.message.toString()))
        }
    }
    suspend fun getSpecificUser(
        user_id: String

    ) :Flow<Results<Response<CreateUserResponse>>> = flow{
        emit(Results.Loading)
        try{
            val response = apiBuilder.api.getSpecificUser(
                user_id
            )
            emit(Results.Success(response))
        } catch (e: Exception){
            emit(Results.Error(message = e.message.toString()))
        }
    }
    suspend fun login(
        email: String,
        password: String

    ): Flow<Results<Response<CreateUserResponse>>> = flow{
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.login(
                email,
                password
            )
            emit(Results.Success(response))
        } catch (e: Exception){
            emit(Results.Error(message = e.message.toString()))
        }
    }
    suspend fun getAllProduct(): Flow<Results<Response<GetProductResponse>>> = flow {
        emit(Results.Loading)
        try {
            val response = apiBuilder.api.getAllProduct()
            emit(Results.Success(response))
        } catch (e: Exception) {
            emit(Results.Error(message = e.message.toString()))
        }
    }

    suspend fun addOrderDetails(
        product_id: String,
        user_id: String,
        product_name: String,
        user_name: String,
        total_amount: Float,
        quantity: Int,
        message: String,
        price: Float,
        category: String
    ): Flow<Results<Response<CreateUserResponse>>> = flow{
        emit(Results.Loading)

        try {
            val response = apiBuilder.api.addOrderDetails(
                product_id,
                user_id,
                product_name,
                user_name,
                total_amount,
                quantity,
                message,
                price,
                category)
        }catch (e: Exception){
            emit(Results.Error(message = e.message.toString()))
        }
    }

}
