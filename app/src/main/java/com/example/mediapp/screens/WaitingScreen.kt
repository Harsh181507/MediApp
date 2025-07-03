package com.example.mediapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mediapp.viewModel.MyViewModel
import java.nio.file.WatchEvent

@Composable

fun WaitingScreen(viewModel: MyViewModel = hiltViewModel(),navController: NavController){
   val state = viewModel.getSpecificUser.collectAsState()
    val user_id = remember {
        mutableStateOf("user_id")
    }
    LaunchedEffect(Unit) {
        viewModel.getSpecificUser(user_id.value)
    }
    when{
        state.value.isLoading->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        state.value.error != null ->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = state.value.error!!)
            }
        }
        state.value.data != null ->{

            if(state.value.data!!.isApproved != 1){
                navController.navigate("add_order_screen")
            }else{
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text="Please Wait while we Verify Your Account")
                }
            }

        }

    }
}