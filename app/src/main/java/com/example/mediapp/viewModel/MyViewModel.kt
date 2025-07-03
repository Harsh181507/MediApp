package com.example.mediapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mediapp.common.Results
import com.example.mediapp.models.CreateUserResponse
import com.example.mediapp.models.GetProductResponse
import com.example.mediapp.prefData.MyPreference
import com.example.mediapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo, private val prefs: MyPreference) : ViewModel() {
    private val _createUser = MutableStateFlow(CreateUserState())
    val createUser = _createUser.asStateFlow()


    private val _getSpecificUser = MutableStateFlow(CreateUserState())
    val getSpecificUser = _getSpecificUser.asStateFlow()

    private val _login = MutableStateFlow(CreateUserState())
    val login = _login.asStateFlow()


    fun createUser(
        name: String,
        password: String,
        email: String,
        phoneNumber: String,
        address: String,
        pinCode: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createUser(
                name,
                password,
                email,
                address,
                phoneNumber,
                pinCode

            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _createUser.value = CreateUserState(isLoading = true)
                    }

                    is Results.Error -> {
                        _createUser.value = CreateUserState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _createUser.value =
                            CreateUserState(data = it.data.body(), isLoading = false)
                        prefs.saveUserID(it.data.body()!!.message)
                    }
                }
            }
        }
    }


    val _userIdByPref = MutableStateFlow<String?>(null)
    val userIdByPref = _userIdByPref.asStateFlow()
    suspend fun getUserByPref(){
        prefs.GetUser.collect {
            _userIdByPref.value=it
        }
    }

    fun getSpecificUser(
        user_id: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSpecificUser(
                user_id
            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _getSpecificUser.value = CreateUserState(isLoading = true)
                    }

                    is Results.Error -> {
                        _getSpecificUser.value =
                            CreateUserState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getSpecificUser.value =
                            CreateUserState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.login(
                email,
                password
            ).collect {
                when (it) {
                    is Results.Loading -> {
                        _login.value = CreateUserState(isLoading = true)
                    }

                    is Results.Error -> {
                        _login.value = CreateUserState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        val response = it.data.body()
                        if (response?.status == 200) {
                            _login.value = CreateUserState(data = response, isLoading = false)
                        } else {
                            _login.value =
                                CreateUserState(error = response?.message, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private val _getAllProduct = MutableStateFlow(GetAllProductState())
    val getAllProduct = _getAllProduct.asStateFlow()
    fun getAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllProduct().collect {
                when (it) {
                    is Results.Loading -> {
                        _getAllProduct.value = GetAllProductState(isLoading = true)
                    }

                    is Results.Error -> {
                        _getAllProduct.value =
                            GetAllProductState(error = it.message, isLoading = false)
                    }

                    is Results.Success -> {
                        _getAllProduct.value =
                            GetAllProductState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }
    }

    private val _addOrder = MutableStateFlow(AddOrderState())
    val addOrder = _addOrder.asStateFlow()
    fun addOrder(
        product_id: String,
        user_id: String,
        product_name: String,
        user_name: String,
        total_amount: Float,
        quantity: Int,
        message: String,
        price: Float,
        category: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addOrderDetails(
                product_id,
                user_id,
                product_name,
                user_name,
                total_amount,
                quantity,
                message,
                price,
                category
            ).collect {
                when(it){
                    is Results.Loading -> {
                        _addOrder.value = AddOrderState(isLoading = true)
                    }
                    is Results.Error -> {
                        _addOrder.value = AddOrderState(error = it.message, isLoading = false)
                    }
                    is Results.Success -> {
                        _addOrder.value = AddOrderState(data = it.data.body(), isLoading = false)
                    }
                }
            }
        }

    }


}

data class CreateUserState(
    val isLoading: Boolean = false,
    val data: CreateUserResponse? = null,
    val error: String? = null
)

data class GetAllProductState(
    val isLoading: Boolean = false,
    val data: GetProductResponse? = null,
    val error: String? = null

)

data class AddOrderState(
    val isLoading: Boolean = false,
    val data: CreateUserResponse? = null,
    val error: String? = null

)