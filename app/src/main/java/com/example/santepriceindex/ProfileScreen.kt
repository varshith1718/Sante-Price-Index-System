package com.example.santepriceindex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(

    userName: String,
    phoneNumber: String,
    gender: String,
    dob: String,

    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit

) {

    // USER DATA

    var name by remember {
        mutableStateOf(userName)
    }

    var phone by remember {
        mutableStateOf(phoneNumber)
    }

    var userGender by remember {
        mutableStateOf(gender)
    }

    var userDob by remember {
        mutableStateOf(dob)
    }

    // DIALOGS

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var showSettingsDialog by remember {
        mutableStateOf(false)
    }

    var showMarketDialog by remember {
        mutableStateOf(false)
    }

    var showHistoryDialog by remember {
        mutableStateOf(false)
    }

    var showHelpDialog by remember {
        mutableStateOf(false)
    }

    // DARK MODE

    var darkMode by remember {
        mutableStateOf(false)
    }

    val backgroundColor =
        if (darkMode)
            Color.Black
        else
            Color(0xFFF8F5FA)

    val textColor =
        if (darkMode)
            Color.White
        else
            Color.Black

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)

    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // TITLE

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "🛒",
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Menu",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF014421)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // PROFILE CARD

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F2E4)
            )

        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = name,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = phone,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Bangalore Weekly Market",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedButton(

                    onClick = {
                        showEditDialog = true
                    },

                    shape = RoundedCornerShape(30.dp)

                ) {

                    Text(
                        text = "Edit Profile",
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // SETTINGS

        MenuRow(

            icon = Icons.Default.Settings,
            title = "Settings",
            subtitle = "Manage app settings",
            textColor = textColor

        ) {

            showSettingsDialog = true
        }

        Spacer(modifier = Modifier.height(20.dp))

        // MARKET

        MenuRow(

            icon = Icons.Default.LocationOn,
            title = "Market Selection",
            subtitle = "Change your mandi",
            textColor = textColor

        ) {

            showMarketDialog = true
        }

        Spacer(modifier = Modifier.height(20.dp))

        // HISTORY

        MenuRow(

            icon = Icons.Default.Home,
            title = "History",
            subtitle = "View past calculations",
            textColor = textColor

        ) {

            showHistoryDialog = true
        }

        Spacer(modifier = Modifier.height(20.dp))

        // HELP

        MenuRow(

            icon = Icons.Default.Help,
            title = "Help",
            subtitle = "Learn how to use app",
            textColor = textColor

        ) {

            showHelpDialog = true
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ABOUT

        MenuRow(

            icon = Icons.Default.Info,
            title = "About",
            subtitle = "Version 1.0.0",
            textColor = textColor

        ) {

        }

        Spacer(modifier = Modifier.height(40.dp))

        // LOGOUT

        Button(

            onClick = {
                onLogoutClick()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),

            shape = RoundedCornerShape(30.dp)

        ) {

            Icon(
                Icons.Default.Logout,
                contentDescription = null,
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Logout",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    // EDIT PROFILE DIALOG

    if (showEditDialog) {

        AlertDialog(

            onDismissRequest = {
                showEditDialog = false
            },

            confirmButton = {

                Button(

                    onClick = {
                        showEditDialog = false
                    }

                ) {

                    Text("Save")
                }
            },

            dismissButton = {

                OutlinedButton(

                    onClick = {
                        showEditDialog = false
                    }

                ) {

                    Text("Cancel")
                }
            },

            title = {

                Text("Edit Profile")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Name")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        label = {
                            Text("Phone")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = userGender,
                        onValueChange = {
                            userGender = it
                        },
                        label = {
                            Text("Gender")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = userDob,
                        onValueChange = {
                            userDob = it
                        },
                        label = {
                            Text("DOB")
                        }
                    )
                }
            }
        )
    }

    // SETTINGS DIALOG

    if (showSettingsDialog) {

        AlertDialog(

            onDismissRequest = {
                showSettingsDialog = false
            },

            confirmButton = {

                Button(

                    onClick = {
                        showSettingsDialog = false
                    }

                ) {

                    Text("Done")
                }
            },

            title = {

                Text("Settings")
            },

            text = {

                Column {

                    Text(
                        text = "Choose Theme",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text("Dark Mode")

                        Spacer(modifier = Modifier.width(12.dp))

                        Switch(

                            checked = darkMode,

                            onCheckedChange = {
                                darkMode = it
                            }
                        )
                    }
                }
            }
        )
    }

    // MARKET DIALOG

    if (showMarketDialog) {

        AlertDialog(

            onDismissRequest = {
                showMarketDialog = false
            },

            confirmButton = {

                Button(

                    onClick = {
                        showMarketDialog = false
                    }

                ) {

                    Text("OK")
                }
            },

            title = {

                Text("Select Market")
            },

            text = {

                Column {

                    Text("• Bangalore Weekly Market")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Mysore Market")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Tumkur Market")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Kolar Market")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• KR Market")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Yeshwanthpur Market")
                }
            }
        )
    }

    // HISTORY DIALOG

    if (showHistoryDialog) {

        AlertDialog(

            onDismissRequest = {
                showHistoryDialog = false
            },

            confirmButton = {

                Button(

                    onClick = {
                        showHistoryDialog = false
                    }

                ) {

                    Text("Close")
                }
            },

            title = {

                Text("History")
            },

            text = {

                Column {

                    Text("• Onion Profit Calculated")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Tomato Price Checked")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Potato Board Shared")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("• Market Trend Viewed")
                }
            }
        )
    }

    // HELP DIALOG

    if (showHelpDialog) {

        AlertDialog(

            onDismissRequest = {
                showHelpDialog = false
            },

            confirmButton = {

                Button(

                    onClick = {
                        showHelpDialog = false
                    }

                ) {

                    Text("OK")
                }
            },

            title = {

                Text("Help & Instructions")
            },

            text = {

                Column {

                    Text("1. Check mandi prices daily.")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("2. Use calculator for selling price.")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("3. View price trends regularly.")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("4. Share price board with customers.")
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("5. Update profile anytime.")
                }
            }
        )
    }
}

@Composable
fun MenuRow(

    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    textColor: Color,
    onClick: () -> Unit

) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(horizontal = 10.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(35.dp),
            tint = textColor
        )

        Spacer(modifier = Modifier.width(18.dp))

        Column {

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}