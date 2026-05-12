package com.example.santepriceindex

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TrendVegetable(
    val name: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendScreen(
    onBackClick: () -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedVeg by remember {
        mutableStateOf("Onion")
    }

    var is30Days by remember {
        mutableStateOf(false)
    }

    val vegList = listOf(

        TrendVegetable("Onion", R.drawable.onion),
        TrendVegetable("Tomato", R.drawable.tomato),
        TrendVegetable("Potato", R.drawable.potato),
        TrendVegetable("Cabbage", R.drawable.cabbage),
        TrendVegetable("Carrot", R.drawable.carrot),
        TrendVegetable("Apple", R.drawable.apple),
        TrendVegetable("Banana", R.drawable.banana),
        TrendVegetable("Mango", R.drawable.mango)
    )

    val selectedItem =
        vegList.find {
            it.name == selectedVeg
        }

    // AUTO RANDOM 7 DAYS DATA

    val trendData = mapOf(

        "Onion" to List(7) {
            (20..45).random()
        },

        "Tomato" to List(7) {
            (10..40).random()
        },

        "Potato" to List(7) {
            (15..30).random()
        },

        "Cabbage" to List(7) {
            (8..25).random()
        },

        "Carrot" to List(7) {
            (25..55).random()
        },

        "Apple" to List(7) {
            (70..120).random()
        },

        "Banana" to List(7) {
            (30..70).random()
        },

        "Mango" to List(7) {
            (60..140).random()
        }
    )

    // AUTO RANDOM 30 DAYS DATA

    val trend30Data = mapOf(

        "Onion" to List(30) {
            (20..45).random()
        },

        "Tomato" to List(30) {
            (10..40).random()
        },

        "Potato" to List(30) {
            (15..30).random()
        },

        "Cabbage" to List(30) {
            (8..25).random()
        },

        "Carrot" to List(30) {
            (25..55).random()
        },

        "Apple" to List(30) {
            (70..120).random()
        },

        "Banana" to List(30) {
            (30..70).random()
        },

        "Mango" to List(30) {
            (60..140).random()
        }
    )

    val currentData =
        if (is30Days)
            trend30Data[selectedVeg] ?: emptyList()
        else
            trendData[selectedVeg] ?: emptyList()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())

    ) {

        TopAppBar(

            title = {

                Text(
                    text = "Trends",
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

        Spacer(modifier = Modifier.height(20.dp))

        // 7 DAYS / 30 DAYS BUTTONS

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            Button(

                onClick = {
                    is30Days = false
                },

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (!is30Days)
                            Color(0xFF1B8D3D)
                        else
                            Color.Gray
                )

            ) {

                Text("7 Days")
            }

            Button(

                onClick = {
                    is30Days = true
                },

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (is30Days)
                            Color(0xFF1B8D3D)
                        else
                            Color.Gray
                )

            ) {

                Text("30 Days")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // DROPDOWN

        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            OutlinedButton(
                onClick = {
                    expanded = true
                }
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    selectedItem?.let {

                        Image(
                            painter = painterResource(it.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(selectedVeg)

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            DropdownMenu(

                expanded = expanded,

                onDismissRequest = {
                    expanded = false
                }

            ) {

                vegList.forEach {

                    DropdownMenuItem(

                        text = {
                            Text(it.name)
                        },

                        onClick = {

                            selectedVeg = it.name
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // TITLE

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically

        ) {

            Text(
                text = "Price Trend (Modal Price)",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Card(

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                )

            ) {

                Text(

                    text = "Likely to Rise ↑",

                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),

                    color = Color(0xFF1B8D3D),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // GRAPH CARD

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            shape = RoundedCornerShape(16.dp)

        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Canvas(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)

                ) {

                    val graphColor = Color(0xFF1B8D3D)

                    val maxValue = currentData.maxOrNull() ?: 100

                    val minValue = currentData.minOrNull() ?: 0

                    val spaceX =
                        size.width / (currentData.size - 1)

                    val points = currentData.mapIndexed { index, value ->

                        val x = index * spaceX

                        val y =
                            size.height -
                                    ((value - minValue).toFloat() /
                                            (maxValue - minValue + 1)) *
                                    size.height

                        Offset(x, y)
                    }

                    // GRID LINES

                    for (i in 1..4) {

                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, size.height / 5 * i),
                            end = Offset(size.width, size.height / 5 * i)
                        )
                    }

                    // GRAPH LINES

                    for (i in 0 until points.size - 1) {

                        drawLine(
                            color = graphColor,
                            start = points[i],
                            end = points[i + 1],
                            strokeWidth = 6f,
                            cap = StrokeCap.Round
                        )
                    }

                    // GRAPH POINTS

                    points.forEach {

                        drawCircle(
                            color = graphColor,
                            radius = 8f,
                            center = it
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // DATES

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween

                ) {

                    if (is30Days) {

                        Text("1")
                        Text("5")
                        Text("10")
                        Text("15")
                        Text("20")
                        Text("25")
                        Text("30")

                    } else {

                        Text("18")
                        Text("19")
                        Text("20")
                        Text("21")
                        Text("22")
                        Text("23")
                        Text("24")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // INSIGHT CARD

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            shape = RoundedCornerShape(16.dp)

        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "💡 Insight",
                    color = Color(0xFF0D47A1),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =
                        when (selectedVeg) {

                            "Onion" ->
                                "Onion prices are rising due to transportation costs."

                            "Tomato" ->
                                "Tomato prices fluctuate heavily because of weather."

                            "Potato" ->
                                "Potato prices are stable with minor increase."

                            "Cabbage" ->
                                "Cabbage demand is moderate this week."

                            "Carrot" ->
                                "Carrot prices increasing due to low supply."

                            "Apple" ->
                                "Apple market remains strong this month."

                            "Banana" ->
                                "Banana prices expected to rise in coming days."

                            else ->
                                "Mango prices rising rapidly due to seasonal demand."
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}