package com.aris.random

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import com.aris.random.ui.theme.RandomTheme
import com.aris.random.viewmodel.RandomNumberViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(DelicateCoroutinesApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ObserveRandomNumberViewModel()
                }
            }
        }
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun ObserveRandomNumberViewModel() {
        val viewModel by viewModels<RandomNumberViewModel>()
        // val viewModel = ViewModelProvider(this).get(RandomNumberViewModel::class.java)

        val number1 by viewModel.stateFlowNumber1.collectAsState()
        val number2 by viewModel.stateFlowNumber2.collectAsState()

        LaunchedEffect(true) {
            viewModel.sharedFlowNumber.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }
        }

        var isRotate by remember { mutableStateOf(true) }
        var isVisible by remember { mutableStateOf(true) }

        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = R.drawable.bg),
                    contentDescription = "bg",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds)
            }
            Column(Modifier.fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f), verticalArrangement = Arrangement.Bottom) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        /* Text(text = number1.toString(),
                             fontSize = 45.sp,
                             fontWeight = FontWeight.Bold,
                             color = Color.Red,
                             modifier = Modifier
                                 .padding(16.dp)
                                 .clip(RoundedCornerShape(12.dp))
                                 .border(6.dp, Color.Red, shape = RoundedCornerShape(12.dp))
                                 .padding(10.dp))

                         Text(text = number2.toString(),
                             fontSize = 45.sp,
                             fontWeight = FontWeight.Bold,
                             color = Color.Red,
                             modifier = Modifier
                                 .padding(16.dp)
                                 .clip(RoundedCornerShape(12.dp))
                                 .border(6.dp, Color.Red, shape = RoundedCornerShape(12.dp))
                                 .padding(10.dp))*/
                        ConvertNumber(number1, isRotate, isVisible)
                        Spacer(modifier = Modifier.width(32.dp))
                        ConvertNumber(number2, isRotate, isVisible)


                    }
                }
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {
                        viewModel.getRandomNumber()
                        GlobalScope.launch {
                            isRotate = !isRotate
                            isVisible = true
                            delay(500)
                            isVisible = false

                        }

                    },
                        modifier = Modifier.padding(top = 75.dp)) {
                        Text(text = "Random Number", fontSize = 28.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }


            }
        }

    }

    @Composable
    fun ConvertNumber(number: Int, isRotate: Boolean, isVisible: Boolean) {
        val rotate by animateFloatAsState(
            targetValue = if (isRotate) 360f else -360f,
            animationSpec = tween(2000))

        Box() {
            when (number) {
                1 -> {
                    Image(painter = painterResource(id = R.drawable.one),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                2 -> {
                    Image(painter = painterResource(id = R.drawable.two),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                3 -> {
                    Image(painter = painterResource(id = R.drawable.three),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                4 -> {
                    Image(painter = painterResource(id = R.drawable.four),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                5 -> {
                    Image(painter = painterResource(id = R.drawable.five),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                6 -> {
                    Image(painter = painterResource(id = R.drawable.six),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
                else -> {
                    Image(painter = painterResource(id = R.drawable.six),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
            }

            Box(modifier = Modifier.size(100.dp)) {
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(animationSpec = tween(0)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    Image(painter = painterResource(id = R.drawable.six),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(rotate))
                }
            }

        }

    }
}

@Composable
fun ObserveRandomNumberViewModel1() {


    val number by viewModel.stateFlowNumber.collectAsState()

    //val context = LocalContext.current == this@MainActivity
    LaunchedEffect(true) {
        viewModel.sharedFlowNumber.collectLatest {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        }
    }

    Column() {
        Text(text = number.toString())
        Button(onClick = { viewModel.getRandomNumber() }) {
            Text(text = "Random Number")
        }
    }
}
}
















