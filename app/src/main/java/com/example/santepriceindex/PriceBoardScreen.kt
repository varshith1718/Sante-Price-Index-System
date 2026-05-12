package com.example.santepriceindex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceBoardScreen(

    userName: String,

    onBackClick: () -> Unit,

    onWatchClick: () -> Unit,

    onCalculatorClick: () -> Unit,

    onBoardClick: () -> Unit,

    onTrendClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(18.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(25.dp))

        // TOP TITLE

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically

        ) {

            Text(
                text = "📢",
                fontSize = 28.sp
            )

            Text(
                text = "SANTE PRICE BOARD",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "📢",
                fontSize = 28.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Today's Prices ( 12 May 2026 )",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // VEG ITEMS

        PriceItem("ONION", "₹28")
        WhiteDivider()

        PriceItem("TOMATO", "₹31")
        WhiteDivider()

        PriceItem("POTATO", "₹26")
        WhiteDivider()

        PriceItem("CABBAGE", "₹22")
        WhiteDivider()

        PriceItem("CARROT", "₹37")
        WhiteDivider()

        PriceItem("APPLE", "₹107")
        WhiteDivider()

        PriceItem("BANANA", "₹45")
        WhiteDivider()

        PriceItem("MANGO", "₹82")
        WhiteDivider()

        Spacer(modifier = Modifier.height(20.dp))

        // THANK YOU

        Text(
            text = "Thank you! Come Again! 😊",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BUTTONS

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            OutlinedButton(

                onClick = { },

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),

                border = ButtonDefaults.outlinedButtonBorder

            ) {

                Icon(
                    Icons.Default.Edit,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text("Edit Prices")
            }

            OutlinedButton(

                onClick = { },

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )

            ) {

                Icon(
                    Icons.Default.Share,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text("Share Board")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // STOP BUTTON

        Button(

            onClick = {
                onBackClick()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),

            shape = RoundedCornerShape(12.dp)

        ) {

            Text(
                text = "Stop Board",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun PriceItem(

    vegetable: String,

    price: String

) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)

    ) {

        Text(
            text = vegetable,
            color = Color.Yellow,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(

            verticalAlignment =
                Alignment.Bottom

        ) {

            Text(
                text = price,
                color = Color.Yellow,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "/kg",
                color = Color.White,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun WhiteDivider() {

    Divider(

        color = Color.White,

        thickness = 1.dp,

        modifier = Modifier.padding(vertical = 6.dp)
    )
}