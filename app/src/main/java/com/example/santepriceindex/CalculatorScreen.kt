package com.example.santepriceindex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

data class ProductItem(
    val name: String,
    val price: Double,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    onBackClick: () -> Unit
) {

    val currentDateTime = remember {
        SimpleDateFormat(
            "dd MMM yyyy | hh:mm a",
            Locale.getDefault()
        ).format(Date())
    }

    var selectedLocation by remember {
        mutableStateOf("Bangalore Mandi")
    }

    var locationExpanded by remember {
        mutableStateOf(false)
    }

    val locationList = listOf(
        "Bangalore Mandi",
        "Mysore Mandi",
        "Pune Mandi",
        "Delhi Mandi"
    )

    val products = when (selectedLocation) {

        "Bangalore Mandi" -> listOf(
            ProductItem("Onion", 21.0, R.drawable.onion),
            ProductItem("Tomato", 24.0, R.drawable.tomato),
            ProductItem("Potato", 19.0, R.drawable.potato),
            ProductItem("Cabbage", 15.0, R.drawable.cabbage),
            ProductItem("Carrot", 30.0, R.drawable.carrot),
            ProductItem("Apple", 100.0, R.drawable.apple),
            ProductItem("Banana", 38.0, R.drawable.banana),
            ProductItem("Mango", 75.0, R.drawable.mango)
        )

        "Mysore Mandi" -> listOf(
            ProductItem("Onion", 18.0, R.drawable.onion),
            ProductItem("Tomato", 20.0, R.drawable.tomato),
            ProductItem("Potato", 15.0, R.drawable.potato),
            ProductItem("Cabbage", 12.0, R.drawable.cabbage),
            ProductItem("Carrot", 24.0, R.drawable.carrot),
            ProductItem("Apple", 90.0, R.drawable.apple),
            ProductItem("Banana", 28.0, R.drawable.banana),
            ProductItem("Mango", 20.0, R.drawable.mango)
        )

        else -> listOf(
            ProductItem("Onion", 25.0, R.drawable.onion),
            ProductItem("Tomato", 29.0, R.drawable.tomato),
            ProductItem("Potato", 22.0, R.drawable.potato),
            ProductItem("Cabbage", 18.0, R.drawable.cabbage),
            ProductItem("Carrot", 34.0, R.drawable.carrot),
            ProductItem("Apple", 120.0, R.drawable.apple),
            ProductItem("Banana", 45.0, R.drawable.banana),
            ProductItem("Mango", 90.0, R.drawable.mango)
        )
    }

    var selectedProduct by remember {
        mutableStateOf(products[0])
    }

    var productExpanded by remember {
        mutableStateOf(false)
    }

    var transportCost by remember {
        mutableStateOf("2.50")
    }

    var loadingCost by remember {
        mutableStateOf("0.50")
    }

    var wasteLoss by remember {
        mutableStateOf("5")
    }

    var otherCost by remember {
        mutableStateOf("0.50")
    }

    var profitMargin by remember {
        mutableStateOf("20")
    }

    val mandiPrice = selectedProduct.price

    val totalCost =
        mandiPrice +
                (transportCost.toDoubleOrNull() ?: 0.0) +
                (loadingCost.toDoubleOrNull() ?: 0.0) +
                (otherCost.toDoubleOrNull() ?: 0.0)

    val wasteAmount =
        totalCost * ((wasteLoss.toDoubleOrNull() ?: 0.0) / 100)

    val finalCost = totalCost + wasteAmount

    val profit =
        finalCost * ((profitMargin.toDoubleOrNull() ?: 0.0) / 100)

    val recommendedPrice = finalCost + profit

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Profit Calculator",
                        color = Color.White
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF014421)
                )
            )
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)

        ) {

            Box {

                OutlinedButton(
                    onClick = {
                        locationExpanded = true
                    }
                ) {

                    Text(selectedLocation)

                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = locationExpanded,
                    onDismissRequest = {
                        locationExpanded = false
                    }
                ) {

                    locationList.forEach {

                        DropdownMenuItem(

                            text = {
                                Text(it)
                            },

                            onClick = {

                                selectedLocation = it
                                selectedProduct = products[0]
                                locationExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(
                        id = selectedProduct.image
                    ),

                    contentDescription = null,

                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(12.dp)),

                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column {

                    Box {

                        OutlinedButton(
                            onClick = {
                                productExpanded = true
                            }
                        ) {

                            Text(selectedProduct.name)

                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }

                        DropdownMenu(
                            expanded = productExpanded,
                            onDismissRequest = {
                                productExpanded = false
                            }
                        ) {

                            products.forEach {

                                DropdownMenuItem(

                                    text = {
                                        Text(it.name)
                                    },

                                    onClick = {

                                        selectedProduct = it
                                        productExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Text(
                        text = "Mandi Price (Modal)",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "Prices updated at $currentDateTime",
                        fontSize = 12.sp,
                        color = Color(0xFF1B8D3D),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "₹${selectedProduct.price} /kg",
                    color = Color(0xFF1B8D3D),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Costs (per kg)",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            CostField(
                label = "Transport Cost",
                value = transportCost,
                onValueChange = {
                    transportCost = it
                }
            )

            CostField(
                label = "Loading / Unloading",
                value = loadingCost,
                onValueChange = {
                    loadingCost = it
                }
            )

            CostField(
                label = "Waste / Loss (%)",
                value = wasteLoss,
                onValueChange = {
                    wasteLoss = it
                }
            )

            CostField(
                label = "Other Costs",
                value = otherCost,
                onValueChange = {
                    otherCost = it
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Profit Margin",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            CostField(
                label = "Desired Profit (%)",
                value = profitMargin,
                onValueChange = {
                    profitMargin = it
                }
            )

            Spacer(modifier = Modifier.height(26.dp))

            Card(

                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF118C35)
                ),

                shape = RoundedCornerShape(16.dp)

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)

                ) {

                    Text(
                        text = "Recommended Selling Price (RRP)",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "₹${String.format("%.2f", recommendedPrice)} /kg",
                        color = Color.White,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Total Cost Price",
                            color = Color.White
                        )

                        Text(
                            text = "₹${String.format("%.2f", finalCost)} /kg",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Expected Profit",
                            color = Color.White
                        )

                        Text(
                            text = "₹${String.format("%.2f", profit)} /kg",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun CostField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
    ) {

        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(

            value = value,

            onValueChange = {
                onValueChange(it)
            },

            modifier = Modifier.fillMaxWidth(),

            singleLine = true,

            shape = RoundedCornerShape(12.dp)
        )
    }
}