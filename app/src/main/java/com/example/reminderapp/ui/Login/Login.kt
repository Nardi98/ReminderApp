package com.example.reminderapp.ui.Login



import com.example.reminderapp.ui.theme.Shapes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Login(navController: NavController ){


    Surface( modifier = Modifier.fillMaxSize()) {
        val username = rememberSaveable { (mutableStateOf("alessandro")) }
        val password = rememberSaveable { (mutableStateOf("ciao")) }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(60.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            
            Icon(imageVector = Icons.Default.AccountCircle,
                 contentDescription = "Default account image",
                 modifier = Modifier
                     .size(150.dp)
                     .alpha(0.9f)
            )

            Spacer(modifier = Modifier.height(15.dp))
            print("login"
                 )
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it},
                leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription ="" )},
                shape = Shapes.small,
                label = {Text("Username")},
                singleLine = true
                )
            
            Spacer(modifier = Modifier.height(15.dp))
            
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it},
                leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription ="" )},
                shape = Shapes.small,
                label= {Text("Password")},
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
                )
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Button(
                    modifier = Modifier.size(width = 190.dp, height = 55.dp),
                    onClick = { checkData(username.value, password.value, navController)} ,
                    shape = Shapes.small,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                    elevation = ButtonDefaults.elevation(defaultElevation = 16.dp, pressedElevation = 2.dp)
                  )

            {
                Text(text = "Login")
            }


        }

    }
}

fun checkData(username:String, password:String, navController: NavController){

    if(username == "alessandro" && password=="ciao") {
        navController.navigate("home")
    }else{
        return
    }
}

