package com.example.santepriceindex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(

    userName: String,

    onCalculatorClick: () -> Unit,

    onPriceBoardClick: () -> Unit,

    onTrendClick: () -> Unit,

    onPriceWatchClick: () -> Unit,

    onProfileClick: () -> Unit

) {

    val currentDate = SimpleDateFormat(
        "dd MMM yyyy",
        Locale.getDefault()
    ).format(Date())

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {

        // TOP BAR

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF014421))
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                ),

            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween

        ) {

            // MENU BUTTON

            IconButton(

                onClick = {
                    onProfileClick()
                }

            ) {

                Icon(
                    Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            // TITLE

            Text(
                text = "Sante-Price Index",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // NOTIFICATION ICON

            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {

            // USER NAME

            Text(
                text = "Namaste, $userName! 👋",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Sante: Weekly Market",
                fontSize = 20.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // MARKET SUMMARY

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F2E4)
                )

            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically

                ) {

                    Column {

                        Text(
                            text = "Today's Market Summary",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = currentDate,
                            color = Color.Gray,
                            fontSize = 18.sp
                        )
                    }

                    Text(
                        text = "🗓️",
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // FIRST ROW

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween

            ) {

                HomeCard(

                    title = "Price Watch",

                    subtitle = "View Mandi Prices",

                    color = Color(0xFF138A36),

                    icon = Icons.Default.Visibility,

                    modifier = Modifier.weight(1f),

                    onClick = onPriceWatchClick
                )

                Spacer(modifier = Modifier.width(12.dp))

                HomeCard(

                    title = "Profit Calculator",

                    subtitle = "Calculate Selling Price",

                    color = Color(0xFF1565C0),

                    icon = Icons.Default.Calculate,

                    modifier = Modifier.weight(1f),

                    onClick = onCalculatorClick
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECOND ROW

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween

            ) {

                HomeCard(

                    title = "Price Board",

                    subtitle = "Show to Customers",

                    color = Color(0xFF7B3FC6),

                    icon = Icons.Default.Home,

                    modifier = Modifier.weight(1f),

                    onClick = onPriceBoardClick
                )

                Spacer(modifier = Modifier.width(12.dp))

                HomeCard(

                    title = "Trends",

                    subtitle = "Price Trend & Insights",

                    color = Color(0xFFFF8C00),

                    icon = Icons.Default.ShowChart,

                    modifier = Modifier.weight(1f),

                    onClick = onTrendClick
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // QUICK TIP

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )

            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "💡 Quick Tip",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Check daily mandi prices",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Set right price, earn right profit!",
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // BOTTOM MENU

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF014421))
                    .padding(vertical = 16.dp),

                horizontalArrangement =
                    Arrangement.SpaceAround

            ) {

                BottomItem("Home")
                BottomItem("Watch")
                BottomItem("Calculator")
                BottomItem("Board")
                BottomItem("Trends")
            }
        }
    }
}

@Composable
fun HomeCard(

    title: String,

    subtitle: String,

    color: Color,

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    modifier: Modifier,

    onClick: () -> Unit

) {

    Card(

        modifier = modifier
            .height(170.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = color
        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement =
                Arrangement.SpaceBetween,

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Icon(

                imageVector = icon,

                contentDescription = null,

                tint = Color.White,

                modifier = Modifier.size(50.dp)
            )

            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun BottomItem(
    title: String
) {

    Text(
        text = title,
        color = Color.White,
        fontSize = 15.sp
    )
}