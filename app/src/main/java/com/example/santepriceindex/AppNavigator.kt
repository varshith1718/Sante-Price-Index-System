package com.example.santepriceindex

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun AppNavigator() {

    var currentScreen by rememberSaveable {
        mutableStateOf("login")
    }

    // USER DATA

    var savedName by rememberSaveable {
        mutableStateOf("Vendor")
    }

    var savedPhone by rememberSaveable {
        mutableStateOf("+91 9876543210")
    }

    var savedGender by rememberSaveable {
        mutableStateOf("Female")
    }

    var savedDob by rememberSaveable {
        mutableStateOf("01/01/2000")
    }

    when (currentScreen) {

        // LOGIN SCREEN

        "login" -> {

            LoginScreen(

                onLoginSuccess = {

                    currentScreen = "home"
                },

                onForgotPasswordClick = {

                    currentScreen = "forgotPassword"
                },

                onCreateProfileClick = {

                    currentScreen = "createProfile"
                },

                onCreateAccountClick = {

                    currentScreen = "createProfile"
                }
            )
        }

        // CREATE PROFILE SCREEN

        "createProfile" -> {

            CreateProfileScreen(

                onAccountCreated = { name, phone, gender, dob ->

                    savedName = name
                    savedPhone = phone
                    savedGender = gender
                    savedDob = dob

                    currentScreen = "home"
                }
            )
        }

        // HOME SCREEN

        "home" -> {

            HomeScreen(

                userName = savedName,

                onCalculatorClick = {

                    currentScreen = "calculator"
                },

                onPriceBoardClick = {

                    currentScreen = "priceBoard"
                },

                onTrendClick = {

                    currentScreen = "trend"
                },

                onPriceWatchClick = {

                    currentScreen = "priceWatch"
                },

                onProfileClick = {

                    currentScreen = "profile"
                }
            )
        }

        // PRICE WATCH SCREEN

        "priceWatch" -> {

            PriceWatchScreen(

                userName = savedName,

                onBackClick = {

                    currentScreen = "home"
                },

                onWatchClick = {

                },

                onBoardClick = {

                    currentScreen = "priceBoard"
                },

                onProfileClick = {

                    currentScreen = "profile"
                }
            )
        }

        // PRICE BOARD SCREEN

        "priceBoard" -> {

            PriceBoardScreen(

                userName = savedName,

                onBackClick = {

                    currentScreen = "home"
                },

                onWatchClick = {

                    currentScreen = "priceWatch"
                },

                onCalculatorClick = {

                    currentScreen = "calculator"
                },

                onBoardClick = {

                    currentScreen = "priceBoard"
                },

                onTrendClick = {

                    currentScreen = "trend"
                }
            )
        }

        // TREND SCREEN

        "trend" -> {

            TrendScreen(

                onBackClick = {

                    currentScreen = "home"
                }
            )
        }

        // CALCULATOR SCREEN

        "calculator" -> {

            CalculatorScreen(

                onBackClick = {

                    currentScreen = "home"
                }
            )
        }

        // PROFILE SCREEN

        "profile" -> {

            ProfileScreen(

                userName = savedName,

                phoneNumber = savedPhone,

                gender = savedGender,

                dob = savedDob,

                onBackClick = {

                    currentScreen = "home"
                },

                onLogoutClick = {

                    currentScreen = "login"
                }
            )
        }

        // FORGOT PASSWORD SCREEN

        "forgotPassword" -> {

            ForgotPasswordScreen(

                onPasswordReset = {

                    currentScreen = "login"
                },

                onBackClick = {

                    currentScreen = "login"
                }
            )
        }
    }
}