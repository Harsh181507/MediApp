package com.example.mediapp.screens

import android.widget.Toast
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mediapp.viewModel.MyViewModel

@Composable
fun MainScreen(viewModel: MyViewModel = hiltViewModel(),navController: NavController){
    val state = viewModel.getSpecificUser.collectAsState()
    val context = LocalContext.current

    Toast.makeText(context, "Hello this is after approval screen", Toast.LENGTH_SHORT).show()

}