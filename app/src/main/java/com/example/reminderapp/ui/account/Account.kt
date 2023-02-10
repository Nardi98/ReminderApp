package com.example.reminderapp.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun AccountPage(navController: NavController) {
	Scaffold(
			topBar = { BackButtonTopBar(page = "Account", navController = navController) }



			) {
		Column(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()
					.padding(top = 50.dp),

				horizontalAlignment = Alignment.CenterHorizontally
			  ) {
			Image(
					imageVector = Icons.Default.AccountCircle,
					contentDescription ="AccountImage",
					modifier = Modifier.sizeIn(minWidth = 180.dp, maxWidth = 240.dp, minHeight = 180.dp, maxHeight = 240.dp)
				)
			Spacer(modifier = Modifier.height(30.dp))
			AccountSlot(name = "Change picture or name", icon = Icons.Default.Person, contentDescription = "Change account or password" )
			Divider()
			AccountSlot(name = "Change email", icon = Icons.Default.MailOutline, contentDescription = "Email changer")
			Divider()
			AccountSlot(name = "Change password", icon = Icons.Default.Lock, contentDescription = "Change password")
			Divider()
			AccountSlot(name = "Log out", icon = Icons.Default.ExitToApp, contentDescription = "Change password", navController = navController, route = "login")
			Divider()

		}
	}
}
@Composable
fun BackButtonTopBar(page:String, navController: NavController? = null){
	TopAppBar(
			modifier = Modifier.heightIn(min = 50.dp),
			title = {Text(text = page, modifier = Modifier
				.heightIn(min = 24.dp, max = 40.dp)
				.padding(start = 10.dp))},
			backgroundColor = MaterialTheme.colors.primary,
			elevation = 16.dp,
			navigationIcon = { IconButton(onClick = { navController?.popBackStack() }) {
				Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "goingBack")
			}}
			 )

}

@Composable
fun AccountSlot(name:String, icon:ImageVector, contentDescription: String, navController: NavController? = null, route:String? = null ){

	Row(
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(min = 100.dp, max = 120.dp)

				.clickable { checkNavController( navController, route) },
			verticalAlignment = Alignment.CenterVertically
	   ) {

		Icon(imageVector = icon, contentDescription = contentDescription, modifier = Modifier
			.alpha(0.7f)
			.size(30.dp))
		Spacer(modifier = Modifier.width(15.dp))
		Text(text = name, fontSize = MaterialTheme.typography.subtitle1.fontSize)


	}
}

fun checkNavController(navController: NavController? = null, route:String? = null){
	if(navController != null){
		if (route != null) {
			navController.navigate(route)
		}
	}else{
		return
	}

}
