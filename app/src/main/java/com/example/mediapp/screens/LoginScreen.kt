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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mediapp.viewModel.MyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun LoginScreen (viewModel: MyViewModel = hiltViewModel(),navController: NavController){
    val state = viewModel.login.collectAsState()

    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    when{
        state.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        state.value.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = state.value.error!!)
            }
        }
        state.value.data != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Login Successful")
            }
        }
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = email.value, onValueChange = {
            email.value = it
        }, label = {Text(text = "E-mail")})

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password.value, onValueChange = {
            password.value = it
        }, label = {Text(text = "Password")})

        Spacer(modifier = Modifier.height(20.dp))

        val context = LocalContext.current
        Button(onClick = {
            if (email.value.isEmpty() || password.value.isEmpty()){
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email.value.trim(), password.value.trim())
            }
        }){
            Text(text = "Login")

        }
        state.value.data?.let{
            LaunchedEffect(it) {
                navController.navigate("waiting_screen"){
                    popUpTo("login_screen"){
                        inclusive = true
                    }
                }
            }
        }
        state.value.error?.let{error->
            LaunchedEffect(error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

}