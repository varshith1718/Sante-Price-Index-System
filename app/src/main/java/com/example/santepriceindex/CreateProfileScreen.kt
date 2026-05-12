package com.example.santepriceindex

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(

    onAccountCreated: (
        String,
        String,
        String,
        String
    ) -> Unit

) {

    val context = LocalContext.current

    val auth = FirebaseAuth.getInstance()

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("Female")
    }

    var dob by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val genderOptions = listOf(
        "Male",
        "Female",
        "Other"
    )

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

        Spacer(modifier = Modifier.height(40.dp))

        Box(

            modifier = Modifier
                .size(110.dp)
                .background(

                    Color.White.copy(alpha = 0.2f),

                    CircleShape
                ),

            contentAlignment = Alignment.Center

        ) {

            Text(

                text = when (gender) {

                    "Male" -> "👨"

                    "Female" -> "👩"

                    else -> "🙂"
                },

                fontSize = 50.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(

            text = "Create Profile",

            color = Color.White,

            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(

            value = name,

            onValueChange = {

                name = it
            },

            label = {

                Text("Full Name")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(

            value = phone,

            onValueChange = {

                phone = it
            },

            label = {

                Text("Phone Number")
            },

            keyboardOptions = KeyboardOptions(

                keyboardType = KeyboardType.Phone
            ),

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

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

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        ExposedDropdownMenuBox(

            expanded = expanded,

            onExpandedChange = {

                expanded = !expanded
            }

        ) {

            OutlinedTextField(

                value = gender,

                onValueChange = { },

                readOnly = true,

                label = {

                    Text("Gender")
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),

                shape = RoundedCornerShape(18.dp)
            )

            ExposedDropdownMenu(

                expanded = expanded,

                onDismissRequest = {

                    expanded = false
                }

            ) {

                genderOptions.forEach {

                    DropdownMenuItem(

                        text = {

                            Text(it)
                        },

                        onClick = {

                            gender = it

                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(

            value = dob,

            onValueChange = {

                dob = it
            },

            label = {

                Text("DOB (dd/mm/yyyy)")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

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

            shape = RoundedCornerShape(18.dp)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(

            onClick = {

                if (

                    name.isEmpty() ||

                    phone.isEmpty() ||

                    email.isEmpty() ||

                    dob.isEmpty() ||

                    password.isEmpty()

                ) {

                    Toast.makeText(

                        context,

                        "Fill all fields",

                        Toast.LENGTH_SHORT

                    ).show()

                } else if (password.length < 6) {

                    Toast.makeText(

                        context,

                        "Password must be minimum 6 characters",

                        Toast.LENGTH_SHORT

                    ).show()

                } else {

                    auth.createUserWithEmailAndPassword(

                        email.trim(),

                        password.trim()

                    )

                        .addOnCompleteListener {

                            if (it.isSuccessful) {

                                Toast.makeText(

                                    context,

                                    "Account Created Successfully",

                                    Toast.LENGTH_LONG

                                ).show()

                                onAccountCreated(

                                    name,

                                    phone,

                                    gender,

                                    dob
                                )

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

                text = "CREATE ACCOUNT",

                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}