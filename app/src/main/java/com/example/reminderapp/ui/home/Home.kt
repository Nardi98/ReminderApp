package com.example.reminderapp.ui.home


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.reminderapp.R
import com.example.reminderapp.data.Reminder




@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun Home(
        viewModel: HomeViewModel = viewModel(),
        navController: NavController
        )

{
    val viewState by viewModel.state.collectAsState()

    val state = rememberLazyListState()





    Scaffold(
            floatingActionButton = {
            FloatingActionButton(
                    modifier = Modifier.padding(bottom= 24.dp, end = 14.dp),
                    onClick = { navController.navigate(route = "newReminder") },
                    backgroundColor = MaterialTheme.colors.primary,
                                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="" )
            }
                               },
            topBar = {ReminderAppTopBar(page = "Home", navController = navController)}



        ) { padding ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(padding) ) {
            //Text(text = viewState.reminders[0].name)

            LazyColumn(
                    state =state,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 44.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                    ){


                items(
                        items = viewState.reminders,
                        key = {reminder -> reminder.id},
                        itemContent = {reminder ->

                            val currentReminder by rememberUpdatedState(reminder)
                            val dismissState = rememberDismissState(
                                    confirmStateChange = {
                                        viewModel.removeReminder(currentReminder)
                                        true
                                    }
                                                                   )
                            SwipeToDismiss(
                                    state = dismissState,
                                    modifier = Modifier.animateItemPlacement(),
                                    background = {
                                        DismissBackground()
                                    },
                                    dismissContent = {
                                        ReminderShower(reminder)
                                    }
                                        )





                        }
                     )
            }




            
        }
    }


}

@Composable
fun ReminderAppTopBar(page:String, navController:NavController){
    TopAppBar(
            modifier = Modifier.heightIn(min = 50.dp),
            title = {Text(text = page, modifier = Modifier
                .heightIn(min = 24.dp, max = 40.dp)
                .padding(start = 10.dp))},
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 16.dp,
            actions = {
                IconButton( onClick = {navController.navigate("account")}){
                    Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(R.string.Account))
                }

            }



             )

}

@Composable
fun ReminderShower(event: Reminder){
    Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 150.dp)
                .clickable(enabled = true) { /*TODO*/ },
            shape = MaterialTheme.shapes.large,
            elevation = 5.dp


        ) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
              )

        {
            Text(
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                    text = event.message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )

            Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Day: ".plus(DateTimeCombine(event.creationTime.toString(), event.reminderTime.toString())),
                    fontSize = MaterialTheme.typography.subtitle2.fontSize,
                    fontStyle = MaterialTheme.typography.caption.fontStyle
                )
            if(event.creationTime != null){
                Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Time ".plus(DateTimeCombine(event.creationTime.toString(), event.reminderTime.toString())),
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        fontStyle = MaterialTheme.typography.caption.fontStyle
                    )
            }


        }

    }
}

@Composable
fun DismissBackground(){
    Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 150.dp),
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.error


        ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically

           ) {
            Text(text = "Delete" , style =  MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete Icon")

        }

    }

    }


fun DateTimeCombine(firstOne: String, secondOne: String): String {


    if(secondOne == "null"){
        return firstOne
    }else{
        return firstOne.plus(" - ").plus(secondOne)
    }
}





