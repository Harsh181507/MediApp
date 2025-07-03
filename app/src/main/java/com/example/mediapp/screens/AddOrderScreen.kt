package com.example.mediapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mediapp.models.GetProductResponseItem
import com.example.mediapp.viewModel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun AddOrderScreen(
    viewModel: MyViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val productState = viewModel.getAllProduct.collectAsState()
    val orderState = viewModel.addOrder.collectAsState()

    val userId = remember { mutableStateOf("USER_ID") } // TODO: Replace with actual session
    val userName = remember { mutableStateOf("USER_NAME") } // TODO: Replace with actual session

    val selectedProducts = remember { mutableStateListOf<Pair<GetProductResponseItem, Int>>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
    }

    LaunchedEffect(orderState.value.data, orderState.value.error) {
        orderState.value.data?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            selectedProducts.clear()
            navController.popBackStack()
        }
        orderState.value.error?.let {
            Toast.makeText(context, "Order failed: $it", Toast.LENGTH_SHORT).show()
        }
    }

    when {
        productState.value.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }

        productState.value.error != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text(productState.value.error ?: "Error")
        }

        productState.value.data != null -> {
            val products = productState.value.data!!

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Available Products", style = MaterialTheme.typography.titleLarge)

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(products) { product ->
                        var quantity by remember { mutableStateOf(0) }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "Price: ₹${product.price}")
                                Text(text = "Stock: ${product.stock}")

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = if (quantity == 0) "" else quantity.toString(),
                                        onValueChange = {
                                            quantity = it.toIntOrNull() ?: 0
                                        },
                                        label = { Text("Quantity") },
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(onClick = {
                                        if (quantity > 0 && quantity <= product.stock) {
                                            selectedProducts.removeAll { it.first.products_id == product.products_id }
                                            selectedProducts.add(product to quantity)
                                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "Invalid quantity", Toast.LENGTH_SHORT).show()
                                        }
                                    }) {
                                        Text("Add")
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Selected Items: ${selectedProducts.size}", style = MaterialTheme.typography.bodyMedium)

                if (selectedProducts.isNotEmpty()) {
                    val total = selectedProducts.sumOf { it.first.price * it.second }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Cart Total: ₹$total", style = MaterialTheme.typography.titleMedium)

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                Toast.makeText(context, "Placing order...", Toast.LENGTH_SHORT).show()
                                scope.launch {
                                    selectedProducts.forEach { (product, quantity) ->
                                        Log.d("Order", "Ordering ${product.name} x $quantity")
                                        viewModel.addOrder(
                                            product_id = product.products_id,
                                            user_id = userId.value,
                                            product_name = product.name,
                                            user_name = userName.value,
                                            total_amount = (product.price * quantity).toFloat(),
                                            quantity = quantity,
                                            message = "Order Placed",
                                            price = product.price.toFloat(),
                                            category = product.category
                                        )
                                    }
                                }
                            }
                        ) {
                            Text("Place Order")
                        }
                    }
                } else {
                    Text("Cart is empty", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
