package com.example.santepriceindex

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(

    onLoginSuccess: () -> Unit,

    onForgotPasswordClick: () -> Unit,

    onCreateProfileClick: () -> Unit,
    onCreateAccountClick: () -> Unit

) {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF014421),

                        Color.Black
                    )
                )
            )
            .padding(24.dp)
            .imePadding(),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            text = "Sante-Price Index",

            color = Color.White,

            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(

            text = "Vendor Smart Pricing System",

            color = Color.LightGray,

            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // EMAIL FIELD

        OutlinedTextField(

            value = email,

            onValueChange = {

                email = it
            },

            label = {

                Text("Email")
            },

            keyboardOptions = KeyboardOptions(

                keyboardType = KeyboardType.Email
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // PASSWORD FIELD

        OutlinedTextField(

            value = password,

            onValueChange = {

                password = it
            },

            label = {

                Text("Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(

                keyboardType = KeyboardType.Password
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            singleLine = true
        )

        Spacer(modifier = Modifier.height(30.dp))

        // LOGIN BUTTON

        Button(

            onClick = {

                if (

                    email.isEmpty() ||

                    password.isEmpty()

                ) {

                    Toast.makeText(

                        context,

                        "Enter Email and Password",

                        Toast.LENGTH_SHORT

                    ).show()

                } else {

                    isLoading = true

                    auth.signInWithEmailAndPassword(

                        email.trim(),

                        password.trim()

                    )

                        .addOnCompleteListener {

                            isLoading = false

                            if (it.isSuccessful) {

                                Toast.makeText(

                                    context,

                                    "Login Successful",

                                    Toast.LENGTH_SHORT

                                ).show()

                                onLoginSuccess()

                            } else {

                                Toast.makeText(

                                    context,

                                    "Account not found",

                                    Toast.LENGTH_LONG

                                ).show()

                                onCreateProfileClick()
                            }
                        }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(18.dp),

            enabled = !isLoading

        ) {

            if (isLoading) {

                CircularProgressIndicator(

                    color = Color.White,

                    modifier = Modifier.size(24.dp),

                    strokeWidth = 2.dp
                )

            } else {

                Text(

                    text = "LOGIN",

                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // FORGOT PASSWORD

        TextButton(

            onClick = {

                onForgotPasswordClick()
            }

        ) {

            Text(

                text = "Forgot Password?"
            )
        }

        // CREATE ACCOUNT

        TextButton(

            onClick = {

                onCreateProfileClick()
            }

        ) {

            Text(

                text = "Create New Account"
            )
        }
    }
}