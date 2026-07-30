package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Theme Colors
val LightBlue = Color(0xFFE3F2FD)
val SkyBlue = Color(0xFF64B5F6)
val DarkBlue = Color(0xFF1565C0)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightBlue
                ) {
                    ReactiveScreen()
                }
            }
        }
    }
}


@Composable
fun ReactiveScreen() {

    var count by remember { mutableStateOf(0) }

    var name by rememberSaveable {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )

        ) {


            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Reactive Counter",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DarkBlue
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Text(
                    text = if (name.isBlank())
                        "Hello, stranger! 👋"
                    else
                        "Hello, $name! 👋",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )


                Spacer(
                    modifier = Modifier.height(24.dp)
                )


                OutlinedTextField(

                    modifier = Modifier.fillMaxWidth(),

                    value = name,

                    onValueChange = {
                        name = it
                    },

                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 17.sp
                    ),

                    label = {
                        Text(
                            text = "Enter your name",
                            color = Color.Black
                        )
                    },

                    shape = RoundedCornerShape(16.dp),

                    colors = OutlinedTextFieldDefaults.colors(

                        focusedBorderColor = SkyBlue,

                        unfocusedBorderColor = DarkBlue,

                        focusedLabelColor = DarkBlue,

                        unfocusedLabelColor = Color.Black,

                        focusedTextColor = Color.Black,

                        unfocusedTextColor = Color.Black,

                        cursorColor = DarkBlue
                    )
                )


                Spacer(
                    modifier = Modifier.height(32.dp)
                )


                CounterControls(
                    count = count,

                    onIncrement = {
                        count++
                    },

                    onDecrement = {
                        count--
                    },

                    onReset = {
                        count = 0
                    }
                )
            }
        }
    }
}



@Composable
fun CounterControls(

    count: Int,

    onIncrement: () -> Unit,

    onDecrement: () -> Unit,

    onReset: () -> Unit

) {


    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Text(
            text = "Current Count",
            fontSize = 18.sp,
            color = Color.Gray
        )


        Spacer(
            modifier = Modifier.height(4.dp)
        )


        Text(
            text = "$count",
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkBlue
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Row(

            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {


            Button(

                modifier = Modifier.width(80.dp),

                onClick = onDecrement,

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = SkyBlue,

                    contentColor = Color.White
                )

            ) {

                Text(
                    text = "-",
                    fontSize = 24.sp
                )
            }



            Button(

                modifier = Modifier.width(100.dp),

                onClick = onReset,

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = DarkBlue,

                    contentColor = Color.White
                )

            ) {

                Text(
                    text = "Reset",
                    fontSize = 16.sp
                )
            }



            Button(

                modifier = Modifier.width(80.dp),

                onClick = onIncrement,

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = SkyBlue,

                    contentColor = Color.White
                )

            ) {

                Text(
                    text = "+",
                    fontSize = 24.sp
                )
            }
        }
    }
}