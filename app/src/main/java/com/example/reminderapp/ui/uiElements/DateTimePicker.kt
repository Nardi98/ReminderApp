package com.example.reminderapp.ui.uiElements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun datePicker(){

	var pickedDate by remember{
		mutableStateOf(LocalDate.now())
	}
	val formattedDate by remember {
		derivedStateOf {
			DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate)
		}
	}
	val dateDialogState = rememberMaterialDialogState()
}