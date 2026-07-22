package com.example.myapplication

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ActiveGreen
import com.example.myapplication.ui.theme.LocalBackgroundGradient
import com.example.myapplication.ui.theme.ProfileTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(
        containerColor = Color.Transparent,

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },


        floatingActionButton = {

            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary
            ) {

                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }


    ) { padding ->


        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    LocalBackgroundGradient.current
                )

        ) {


            // ☁️ SKY EMOJIS

            Text(
                text = "☁️",
                fontSize = 70.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        top = 30.dp,
                        start = 10.dp
                    )
            )


            Text(
                text = "☁️",
                fontSize = 55.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 70.dp,
                        end = 20.dp
                    )
            )


            Text(
                text = "✨",
                fontSize = 35.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp)
            )


            // 🌊 OCEAN EMOJIS

            Text(
                text = "🌊🌊🌊",
                fontSize = 55.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            )


            Text(
                text = "🐚  🫧  ⭐  🐠",
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 90.dp)
            )



            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {



                // PROFILE IMAGE

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                ) {


                    Image(

                        painter = painterResource(
                            id = R.drawable.syprofile
                        ),

                        contentDescription = "Profile Picture",

                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(
                                3.dp,
                                MaterialTheme.colorScheme.primary,
                                CircleShape
                            ),

                        contentScale = ContentScale.Crop

                    )



                    // 🟢 ACTIVE STATUS

                    Box(

                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(ActiveGreen)
                            .border(
                                2.dp,
                                Color.White,
                                CircleShape
                            )

                    )

                }




                Text(

                    text = "Wendy Michelle Sy",

                    style = MaterialTheme.typography.headlineSmall,

                    fontWeight = FontWeight.Bold

                )



                Text(

                    text = "UI/UX Design",

                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )





                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ) {


                    Button(

                        onClick = {},

                        modifier = Modifier.weight(1f)

                    ) {

                        Text("Message")

                    }




                    OutlinedButton(

                        onClick = {},

                        modifier = Modifier.weight(1f)

                    ) {

                        Text("Follow")

                    }

                }





                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(

                        containerColor =
                            Color.White.copy(alpha = 0.55f)

                    ),

                    border = BorderStroke(
                        1.dp,
                        Color.White.copy(alpha = 0.6f)
                    )

                ) {


                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalArrangement =
                            Arrangement.SpaceEvenly

                    ) {


                        StatItem(
                            "332",
                            "Posts"
                        )

                        StatItem(
                            "2.6k",
                            "Followers"
                        )

                        StatItem(
                            "67",
                            "Following"
                        )

                    }

                }





                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(

                        containerColor =
                            Color.White.copy(alpha = 0.55f)

                    )

                ) {


                    Column(

                        modifier = Modifier.padding(16.dp),

                        verticalArrangement =
                            Arrangement.spacedBy(12.dp)

                    ) {


                        ContactRow(
                            Icons.Default.Email,
                            "wmsy98844@liceo.edu.ph"
                        )


                        ContactRow(
                            Icons.Default.LocationOn,
                            "Cagayan de Oro City, Philippines"
                        )

                    }

                }


            }

        }

    }

}




@Composable
fun StatItem(
    count: String,
    label: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = count,
            fontWeight = FontWeight.Bold
        )


        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }

}




@Composable
fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    detail: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier = Modifier.width(8.dp)
        )


        Text(
            text = detail
        )

    }

}




@Preview(showBackground = true)
@Composable
fun ProfilePreview() {

    ProfileTheme {

        ProfileScreen()

    }

}




@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProfileDarkPreview() {

    ProfileTheme(
        darkTheme = true
    ) {

        ProfileScreen()

    }

}