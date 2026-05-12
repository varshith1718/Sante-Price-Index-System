package com.example.santepriceindex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class ProductPrice(
    val name: String,
    val min: String,
    val max: String,
    val modal: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceWatchScreen(

    userName: String,

    onBackClick: () -> Unit,

    onWatchClick: () -> Unit,

    onBoardClick: () -> Unit,

    onProfileClick: () -> Unit
) {

    val listState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    var currentDateTime by remember {
        mutableStateOf("")
    }

    var randomIncrease by remember {
        mutableStateOf((0..5).random())
    }

    LaunchedEffect(Unit) {

        while (true) {

            currentDateTime =
                SimpleDateFormat(
                    "dd MMM yyyy | hh:mm a",
                    Locale.getDefault()
                ).format(Date())

            randomIncrease = (0..10).random()

            delay(5000)
        }
    }

    var selectedLocation by remember {
        mutableStateOf("Bangalore Mandi")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val mandiList = listOf(
        "Bangalore Mandi",
        "Mysore Mandi",
        "Pune Mandi",
        "Delhi Mandi",
        "Mumbai Mandi"
    )

    val productList = when (selectedLocation) {

        "Bangalore Mandi" -> listOf(

            ProductPrice("Onion", "18", "24", "${21 + randomIncrease}", R.drawable.onion),
            ProductPrice("Tomato", "20", "28", "${24 + randomIncrease}", R.drawable.tomato),
            ProductPrice("Potato", "16", "22", "${19 + randomIncrease}", R.drawable.potato),
            ProductPrice("Cabbage", "12", "18", "${15 + randomIncrease}", R.drawable.cabbage),
            ProductPrice("Carrot", "25", "35", "${30 + randomIncrease}", R.drawable.carrot),
            ProductPrice("Apple", "80", "120", "${100 + randomIncrease}", R.drawable.apple),
            ProductPrice("Banana", "30", "45", "${38 + randomIncrease}", R.drawable.banana),
            ProductPrice("Mango", "60", "90", "${75 + randomIncrease}", R.drawable.mango)
        )

        "Mysore Mandi" -> listOf(

            ProductPrice("Onion", "14", "20", "${17 + randomIncrease}", R.drawable.onion),
            ProductPrice("Tomato", "16", "24", "${20 + randomIncrease}", R.drawable.tomato),
            ProductPrice("Potato", "12", "18", "${15 + randomIncrease}", R.drawable.potato),
            ProductPrice("Cabbage", "10", "16", "${13 + randomIncrease}", R.drawable.cabbage),
            ProductPrice("Carrot", "18", "26", "${22 + randomIncrease}", R.drawable.carrot),
            ProductPrice("Apple", "70", "100", "${85 + randomIncrease}", R.drawable.apple),
            ProductPrice("Banana", "22", "35", "${28 + randomIncrease}", R.drawable.banana),
            ProductPrice("Mango", "15", "25", "${20 + randomIncrease}", R.drawable.mango)
        )

        else -> listOf(

            ProductPrice("Onion", "20", "28", "${25 + randomIncrease}", R.drawable.onion),
            ProductPrice("Tomato", "24", "34", "${29 + randomIncrease}", R.drawable.tomato),
            ProductPrice("Potato", "18", "24", "${21 + randomIncrease}", R.drawable.potato),
            ProductPrice("Cabbage", "15", "20", "${18 + randomIncrease}", R.drawable.cabbage),
            ProductPrice("Carrot", "30", "40", "${35 + randomIncrease}", R.drawable.carrot),
            ProductPrice("Apple", "90", "130", "${110 + randomIncrease}", R.drawable.apple),
            ProductPrice("Banana", "35", "50", "${42 + randomIncrease}", R.drawable.banana),
            ProductPrice("Mango", "70", "110", "${90 + randomIncrease}", R.drawable.mango)
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Price Watch",
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

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn(

                state = listState,

                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
                    .padding(horizontal = 12.dp)
                    .navigationBarsPadding()
                    .imePadding(),

                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 350.dp
                ),

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                item {

                    Text(
                        text = "Welcome $userName",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF014421)
                    )
                }

                item {

                    Row(

                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically

                    ) {

                        Text(
                            text = currentDateTime,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )

                        Box {

                            Row(
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Text(
                                    text = selectedLocation,
                                    fontWeight =
                                        FontWeight.SemiBold
                                )

                                IconButton(
                                    onClick = {
                                        expanded = true
                                    }
                                ) {

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

                                mandiList.forEach {

                                    DropdownMenuItem(

                                        text = {
                                            Text(it)
                                        },

                                        onClick = {

                                            selectedLocation = it
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                item {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            "Commodity",
                            modifier = Modifier.weight(2f),
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Min",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Max",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Modal",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                items(productList) { item ->

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(14.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF2EEF4)
                        )

                    ) {

                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            verticalAlignment =
                                Alignment.CenterVertically

                        ) {

                            Row(

                                modifier =
                                    Modifier.weight(2f),

                                verticalAlignment =
                                    Alignment.CenterVertically

                            ) {

                                Image(
                                    painter =
                                        painterResource(id = item.image),

                                    contentDescription = null,

                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(
                                            RoundedCornerShape(10.dp)
                                        ),

                                    contentScale =
                                        ContentScale.Crop
                                )

                                Spacer(
                                    modifier =
                                        Modifier.width(10.dp)
                                )

                                Text(
                                    text = item.name,
                                    fontWeight =
                                        FontWeight.Medium
                                )
                            }

                            Text(
                                text = item.min,
                                modifier =
                                    Modifier.weight(1f)
                            )

                            Text(
                                text = item.max,
                                modifier =
                                    Modifier.weight(1f)
                            )

                            Text(
                                text = item.modal,
                                modifier =
                                    Modifier.weight(1f),

                                color =
                                    Color(0xFF1B8D3D),

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}