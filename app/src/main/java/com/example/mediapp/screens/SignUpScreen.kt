package com.example.mediapp.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mediapp.screens.nav.Routs
import com.example.mediapp.viewModel.MyViewModel

@Composable
fun SignUpScreen(viewModel: MyViewModel = hiltViewModel(),navController: NavController) {
    val state = viewModel.createUser.collectAsState()
    val userName = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val address = remember {
        mutableStateOf("")
    }
    val pinCode = remember {
        mutableStateOf("")
    }


    when {
        state.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.value.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.error!!)
            }
        }

        state.value.data != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up")

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = userName.value, onValueChange = {
            userName.value = it
        }, label = { Text(text = "Name") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email.value, onValueChange = {
            email.value = it
        }, label = { Text(text = "E-mail") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = password.value, onValueChange = {
            password.value = it
        }, label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = phoneNumber.value, onValueChange = {
            phoneNumber.value = it
        }, label = { Text(text = "Phone Number") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = address.value, onValueChange = {
            address.value = it
        }, label = { Text(text = "Address") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = pinCode.value, onValueChange = {
            pinCode.value = it
        }, label = { Text(text = "Pin Code") })
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Button(onClick = {
            if (userName.value.isEmpty() || password.value.isEmpty() || email.value.isEmpty() || phoneNumber.value.isEmpty() || address.value.isEmpty() || pinCode.value.isEmpty()) {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.createUser(
                    userName.value,
                    password.value,
                    email.value,
                    phoneNumber.value,
                    address.value,
                    pinCode.value
                )
                navController.navigate(Routs.WaitingScreenRoutes.route)
            }
        }) {
            Text(text = "Sign Up")
        }

    }
}