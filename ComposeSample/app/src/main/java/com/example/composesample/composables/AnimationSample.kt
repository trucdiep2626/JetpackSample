package com.example.composesample.composables

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composesample.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun changeColorAndExpandAnimation() {
    var randomValue by remember { mutableStateOf(true) }

    val startColor = Color.Blue
    val endColor = Color.Magenta
    val color by animateColorAsState(
        if (randomValue) endColor else startColor,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 200),
//            repeatMode = RepeatMode.Reverse
//        )

//        animationSpec = repeatable(
//            iterations = 3,
//            animation = tween(durationMillis = 200),
//            repeatMode = RepeatMode.Reverse
//        )

//        animationSpec = tween(
//            durationMillis = 2000,
//            delayMillis = 40,
//            easing = LinearOutSlowInEasing
//        )

//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioHighBouncy,
//            stiffness = Spring.StiffnessMedium
//        )
        animationSpec = snap(delayMillis = 20)

    )
    Column() {
        AnimatedVisibility(
            visible = randomValue,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            )
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.secondary,
                elevation = 4.dp
            ) {
                Text(
                    text = " color = MaterialTheme.colors.secondary,",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize()
        ) {
            Text(
                text = " color = MaterialTheme.colors.secondary,",
                modifier = Modifier.padding(16.dp)
            )
         if(randomValue)
         {
             Text(
                 text = " color = MaterialTheme.colors.secondary, ".repeat(5),
                 modifier = Modifier.padding(16.dp)
             )
         }
        }

        Row(modifier = Modifier
            .background(color)
            .clickable { randomValue = !randomValue }
            .padding(10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility),
                contentDescription = "Visibility"
            )
            AnimatedVisibility(randomValue) {
                Text(
                    text = "Visibility" ,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 3.dp)
                )
            }


        }



    }

}