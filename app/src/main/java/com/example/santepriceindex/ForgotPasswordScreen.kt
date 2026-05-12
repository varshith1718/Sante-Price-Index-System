package com.example.santepriceindex

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPasswordScreen(

    onPasswordReset: () -> Unit,

    onBackClick: () -> Unit

) {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
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

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Forgot Password",
            color = Color.White,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Enter your registered email",
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

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // RESET PASSWORD BUTTON

        Button(

            onClick = {

                if (email.isEmpty()) {

                    Toast.makeText(
                        context,
                        "Enter Email",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    auth.sendPasswordResetEmail(
                        email.trim()
                    )

                        .addOnCompleteListener {

                            if (it.isSuccessful) {

                                Toast.makeText(
                                    context,
                                    "Reset Email Sent",
                                    Toast.LENGTH_LONG
                                ).show()

                                onPasswordReset()

                            } else {

                                Toast.makeText(
                                    context,
                                    it.exception?.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1B8D3D)
            ),

            shape = RoundedCornerShape(18.dp)

        ) {

            Text(
                text = "SEND RESET LINK",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // BACK BUTTON

        OutlinedButton(

            onClick = {
                onBackClick()
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)

        ) {

            Text("Back to Login")
        }
    }
}