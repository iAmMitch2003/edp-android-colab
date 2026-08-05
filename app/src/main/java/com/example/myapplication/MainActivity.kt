package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                GroceryListApp()
            }
        }
    }
}


@Composable
fun GroceryListApp() {

    var newItem by remember {
        mutableStateOf("")
    }

    val groceries = remember {
        mutableStateListOf<String>()
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF4F8FF)
            )

    ) {


        // TOP BLUE HEADER
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1565C0),
                            Color(0xFF42A5F5)
                        )
                    )
                )
                .padding(24.dp),

            contentAlignment = Alignment.CenterStart

        ) {


            Column {

                Text(
                    text = "🛒 Grocery List",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = "Manage your shopping items easily",
                    color = Color.White.copy(
                        alpha = 0.9f
                    ),
                    fontSize = 16.sp
                )

            }

        }



        Column(

            modifier = Modifier
                .padding(20.dp)

        ) {


            // INPUT CARD
            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    6.dp
                )

            ) {


                Row(

                    modifier = Modifier
                        .padding(16.dp),

                    verticalAlignment = Alignment.CenterVertically

                ) {


                    OutlinedTextField(

                        value = newItem,

                        onValueChange = {
                            newItem = it
                        },

                        label = {
                            Text("Add grocery item")
                        },

                        shape = RoundedCornerShape(16.dp),

                        modifier = Modifier.weight(1f)

                    )



                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )



                    Button(

                        onClick = {

                            if (newItem.isNotBlank()) {

                                groceries.add(
                                    newItem.trim()
                                )

                                newItem = ""

                            }

                        },

                        shape = RoundedCornerShape(16.dp),

                        colors = ButtonDefaults.buttonColors(

                            containerColor = Color(0xFF1976D2)

                        )

                    ) {

                        Text("Add")

                    }

                }

            }



            Spacer(
                modifier = Modifier.height(18.dp)
            )



            // COUNTER CARD
            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(

                    containerColor = Color(0xFFD9ECFF)

                )

            ) {


                Row(

                    modifier = Modifier.padding(18.dp),

                    verticalAlignment = Alignment.CenterVertically

                ) {


                    Text(

                        text = "📦",

                        fontSize = 25.sp

                    )


                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )


                    Text(

                        text = "Total Items: ${groceries.size}",

                        fontSize = 18.sp,

                        fontWeight = FontWeight.Bold,

                        color = Color(0xFF0D47A1)

                    )

                }

            }



            Spacer(
                modifier = Modifier.height(15.dp)
            )



            // CLEAR BUTTON
            Button(

                onClick = {

                    groceries.clear()

                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = Color(0xFFEF5350)

                )

            ) {

                Text(
                    text = "Clear All"
                )

            }



            Spacer(
                modifier = Modifier.height(20.dp)
            )




            // LIST
            if (groceries.isEmpty()) {


                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),

                    contentAlignment = Alignment.Center

                ) {


                    Column(

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {


                        Text(

                            text = "🛒",

                            fontSize = 55.sp

                        )


                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )


                        Text(

                            text = "No grocery items yet",

                            color = Color.Gray,

                            fontSize = 18.sp

                        )

                    }

                }


            } else {



                LazyColumn(

                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {



                    items(groceries) { item ->



                        Card(

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(18.dp),

                            colors = CardDefaults.cardColors(

                                containerColor = Color.White

                            ),

                            elevation = CardDefaults.cardElevation(

                                5.dp

                            )

                        ) {



                            Row(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),

                                horizontalArrangement = Arrangement.SpaceBetween,

                                verticalAlignment = Alignment.CenterVertically

                            ) {



                                Row(

                                    verticalAlignment = Alignment.CenterVertically

                                ) {


                                    Text(

                                        text = "🛍️",

                                        fontSize = 24.sp

                                    )


                                    Spacer(
                                        modifier = Modifier.width(12.dp)
                                    )


                                    Text(

                                        text = item,

                                        fontSize = 18.sp,

                                        fontWeight = FontWeight.Medium

                                    )

                                }





                                Button(

                                    onClick = {

                                        groceries.remove(item)

                                    },

                                    shape = RoundedCornerShape(14.dp),

                                    colors = ButtonDefaults.buttonColors(

                                        containerColor = Color(0xFF1565C0)

                                    )

                                ) {

                                    Text("Delete")

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}