package com.example.bottomsheet2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomsheet2.ui.theme.BottomSheet2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


class SignUpScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheet2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    EmailTextField()

                }
            }
        }
    }
}

@Composable
fun SignUpPage() {



}



@Composable
fun EmailTextField(modifier: Modifier = Modifier) {
    var email by remember {mutableStateOf("")}
    var name by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var showErrorEmail by remember {mutableStateOf(false)}
    var showErrorName by remember { mutableStateOf(false) }
    var showErrorPassword by remember { mutableStateOf(false) }
    var isNamePasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _->}



    Column(modifier= Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        Row(modifier = Modifier.fillMaxWidth()) {


            TextField(
                value = email,
                onValueChange = {
                    email = it
                    isNamePasswordVisible = isValidEmail(email) && email.isNotEmpty()
                                },
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(Icons.Filled.Email,
                        contentDescription = null
                )} ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = androidx.compose.ui.text.input.ImeAction.Go),
                keyboardActions = KeyboardActions
                    (onGo = {
                    showErrorEmail = !isValidEmail(email)||email.isEmpty()
                    }),
                colors = TextFieldDefaults.outlinedTextFieldColors(

                    focusedLabelColor = colorResource(
                        id = R.color.colorPrimary
                    ),
                    unfocusedLabelColor = Color.Gray,
                    leadingIconColor = colorResource(R.color.colorPrimary),
                    disabledLeadingIconColor = Color.Gray,
                    placeholderColor = colorResource(R.color.colorPrimary),
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorLeadingIconColor = Color.Red,


                )
                ,modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }



        if(showErrorEmail){
            Text(text = "Please enter a valid email address",
                color=Color.Red,
                modifier = Modifier.padding(15.dp))
        }

        DisposableEffect(email) {
            onDispose {
                // This block of code runs whenever the email changes
                name = ""
                password = ""
                showErrorName = false
                showErrorPassword = false
            }
        }


            if(isValidEmail(email) && email.isNotEmpty() && !showErrorEmail){

                isNamePasswordVisible=true
                showErrorEmail=false



                Spacer(modifier = Modifier.padding(20.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    leadingIcon = {
                        Icon(Icons.Filled.AccountCircle,
                            contentDescription = null
                        )} ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = androidx.compose.ui.text.input.ImeAction.Go),
                    keyboardActions = KeyboardActions
                        (onGo = {
                    }),
                    colors = TextFieldDefaults.outlinedTextFieldColors(

                        focusedLabelColor = colorResource(
                            id = R.color.colorPrimary
                        ),
                        unfocusedLabelColor = Color.Gray,
                        leadingIconColor = colorResource(R.color.colorPrimary),
                        disabledLeadingIconColor = Color.Gray,
                        placeholderColor = colorResource(R.color.colorPrimary)

                    )
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                if(showErrorName){

                    if(name.isEmpty()){
                        Text(text = "Please enter your name",
                            color=Color.Red,
                            modifier = Modifier.padding(15.dp))}
                }


                Spacer(modifier = Modifier.padding(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock,
                            contentDescription = null
                        )} ,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = androidx.compose.ui.text.input.ImeAction.Go),
                    keyboardActions = KeyboardActions
                        (onGo = {
                    }),
                    colors = TextFieldDefaults.outlinedTextFieldColors(

                        focusedLabelColor = colorResource(
                            id = R.color.colorPrimary
                        ),
                        unfocusedLabelColor = Color.Gray,
                        leadingIconColor = colorResource(R.color.colorPrimary),
                        disabledLeadingIconColor = Color.Gray,
                        placeholderColor = colorResource(R.color.colorPrimary)

                    )
                    ,modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                if(showErrorPassword){
                    if(password.isEmpty()){
                        Text(text = "Please enter a password",
                            color=Color.Red,
                            modifier = Modifier.padding(15.dp))
                    }
                }
            }
            else{ isNamePasswordVisible=false }




        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Button(
                onClick = {

                    showErrorEmail = email.isEmpty() || !isValidEmail(email)
                    showErrorName = isNamePasswordVisible
                    showErrorPassword = isNamePasswordVisible
                    if(!showErrorEmail && name.isNotEmpty() && password.isNotEmpty()){
                        val intent= Intent(context,HomeScreen::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.colorPrimary)),
                modifier = Modifier
                    .padding(20.dp),
            )
            {
                Text(text = "NEXT",color=Color.White)
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BottomSheet2Theme {
        EmailTextField()
    }
}