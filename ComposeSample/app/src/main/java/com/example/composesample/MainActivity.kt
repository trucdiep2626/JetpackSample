package com.example.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composesample.composables.LogInPage
import com.example.composesample.composables.RegisterPage
import com.example.composesample.composables.TodoPage
import com.example.composesample.composables.changeColorAndExpandAnimation

class MainActivity : ComponentActivity() {
    val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            changeColorAndExpandAnimation()
//            TodoPage(
//                items = todoViewModel.todos,
//                onAddItem = todoViewModel::addItem,
//                onRemoveItem = todoViewModel::removeItem,
//                onStartEdit = todoViewModel::onEditItemSelected,
//                currentlyEditing = todoViewModel.currentEditItem,
//                onEditItemChange = todoViewModel::onEditItemChange,
//                onEditDone = todoViewModel::onEditDone
//            )
            //Authentication()
        }
    }
}

@Composable
fun Authentication() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_page",
        builder = {
            composable("login_page", content = { LogInPage(navController = navController) })
            composable("register_page", content = { RegisterPage(navController = navController) })
        })
}

@Composable
fun Greeting(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }
//        Icon(painter = painterResource(id = R.drawable.ic_add),
//            contentDescription ="Add" ,
//        modifier = Modifier.size(23.dp))
    }
}

@Preview()
@Composable
fun DefaultPreview() {

}

@Composable
fun WeatherItem() {
    Box(modifier = Modifier.background(Color.White)) {
        Column() {
            Row() {
                Column {
                    Text(
                        text = "23°",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Austin",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "USA",
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                }


            }
        }
    }
}