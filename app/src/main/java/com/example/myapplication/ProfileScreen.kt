package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme


// Maroon Color
val Maroon = Color(0xFF800000)



@Composable
fun ProfileApp() {

    val systemInDarkTheme = isSystemInDarkTheme()

    var isDarkMode by remember {
        mutableStateOf(systemInDarkTheme)
    }


    MyApplicationTheme(
        darkTheme = isDarkMode
    ) {

        ProfileScreen(
            isDarkMode = isDarkMode,
            onThemeChange = {
                isDarkMode = !isDarkMode
            }
        )

    }

}





@Composable
fun ProfileScreen(

    isDarkMode: Boolean,

    onThemeChange: () -> Unit

) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    colors = if (isDarkMode) {

                        listOf(

                            Color(0xFF1A0000),
                            Color(0xFF400000),
                            Color.Black

                        )

                    } else {

                        listOf(

                            Color(0xFFFFE6E6),
                            Color(0xFFF5C6C6),
                            Color.White

                        )

                    }

                )

            )
            .padding(16.dp),


        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {



        // Dark / Light Mode Button

        Button(

            onClick = {

                onThemeChange()

            },

            colors = ButtonDefaults.buttonColors(

                containerColor = Maroon

            )

        ) {


            Text(

                text = if (isDarkMode)

                    "Light Mode"

                else

                    "Dark Mode",

                color = Color.White

            )

        }





        Spacer(

            modifier = Modifier.height(20.dp)

        )







        Box(

            modifier = Modifier.size(120.dp),

            contentAlignment = Alignment.BottomEnd

        ) {



            Box(

                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Maroon)
                    .border(
                        2.dp,
                        Color.White,
                        CircleShape
                    ),

                contentAlignment = Alignment.Center

            ) {


                Text(

                    text = "WMS",

                    color = Color.White,

                    style = MaterialTheme.typography.headlineSmall

                )


            }




            // Active Status Circle

            Box(

                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Green)

            )


        }





        Spacer(

            modifier = Modifier.height(16.dp)

        )





        // Name

        Text(

            text = "Wendy Michelle Sy",

            style = MaterialTheme.typography.headlineSmall,

            color = if (isDarkMode)

                Color.White

            else

                Maroon

        )





        Spacer(

            modifier = Modifier.height(4.dp)

        )





        // Section

        Text(

            text = "BSIT 3-2",

            style = MaterialTheme.typography.titleMedium,


            color = if (isDarkMode)

                Color.LightGray

            else

                MaterialTheme.colorScheme.onSurfaceVariant

        )





        Spacer(

            modifier = Modifier.height(24.dp)

        )





        // Information Card

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),


            colors = CardDefaults.cardColors(

                containerColor = if (isDarkMode)

                    Color(0xFF2B2B2B)

                else

                    Color.White.copy(alpha = 0.9f)

            )

        ) {



            Column(

                modifier = Modifier.padding(16.dp)

            ) {



                InfoRow(

                    icon = Icons.Default.Person,

                    label = "Full Name",

                    value = "Wendy Michelle Sy"

                )




                InfoRow(

                    icon = Icons.Default.School,

                    label = "Course",

                    value = "Bachelor of Science in Information Technology"

                )




                InfoRow(

                    icon = Icons.Default.Class,

                    label = "Section",

                    value = "BSIT 3-2"

                )




                InfoRow(

                    icon = Icons.Default.Phone,

                    label = "Mobile Number",

                    value = "09763339493"

                )




                InfoRow(

                    icon = Icons.Default.Email,

                    label = "Email Address",

                    value = "wmsy98844@liceo.edu.ph"

                )


            }


        }


    }


}





@Composable
fun InfoRow(

    icon: ImageVector,

    label: String,

    value: String

) {


    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {



        Icon(

            imageVector = icon,

            contentDescription = label,

            tint = Maroon

        )




        Spacer(

            modifier = Modifier.width(16.dp)

        )




        Column(

            modifier = Modifier.weight(1f)

        ) {



            Text(

                text = label,

                style = MaterialTheme.typography.labelMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )




            Text(

                text = value,

                style = MaterialTheme.typography.bodyLarge,

                color = MaterialTheme.colorScheme.onSurface

            )


        }


    }


}





// Light Preview

@Preview(

    showBackground = true,

    name = "Profile Light"

)

@Composable
fun ProfileLightPreview() {

    ProfileApp()

}





// Dark Preview

@Preview(

    showBackground = true,

    name = "Profile Dark",

    uiMode = Configuration.UI_MODE_NIGHT_YES

)

@Composable
fun ProfileDarkPreview() {


    MyApplicationTheme(

        darkTheme = true

    ) {


        ProfileScreen(

            isDarkMode = true,

            onThemeChange = {}

        )


    }


}