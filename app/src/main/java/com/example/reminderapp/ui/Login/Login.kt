package com.example.reminderapp.ui.Login



import com.example.reminderapp.ui.theme.Shapes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Login(){


    Surface( modifier = Modifier.fillMaxSize()) {
        val username = rememberSaveable { (mutableStateOf("")) }
        val password = rememberSaveable { (mutableStateOf("")) }

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

            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it},
                shape = Shapes.small,
                label = {Text("username")}
                )
            
            Spacer(modifier = Modifier.height(15.dp))
            
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it},
                shape = Shapes.small,
                label= {Text("password")},
                visualTransformation = PasswordVisualTransformation()
                )
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Button(modifier = Modifier.size(width = 190.dp, height = 55.dp) , onClick = { /*TODO*/} ,  shape = Shapes.small) {
                Text(text = "Login")
            }


        }

    }
}

