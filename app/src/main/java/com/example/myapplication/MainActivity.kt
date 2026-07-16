package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Brand {
    // Cloudy Ocean Blue Theme
    val GradientTop = Color(0xFF0B3C5D)
    val GradientMiddle = Color(0xFF328CC1)
    val GradientBottom = Color(0xFFD9F1FF)

    val SubtitleColor = Color(0xFFF2FAFF)
    val CardBackground = Color.White.copy(alpha = 0.18f)

    val ProfileImageSize = 120.dp
    val ProfileBorderWidth = 3.dp
    val CardCornerRadius = 20.dp
    val MainGapHeight = 48.dp
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Brand.GradientTop,
            Brand.GradientMiddle,
            Brand.GradientBottom
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {

        // Background Emojis
        Text(
            text = """
                ☁️     🌊      ☁️
           🩵      ☁️      🌊
                🌊      💙
          ☁️      🩵      ☁️
               🌊      ☁️
           🩵     ☁️     🌊
                🌊     💙
          ☁️       🩵     ☁️
               🌊      ☁️
        """.trimIndent(),
            color = Color.White.copy(alpha = 0.18f),
            fontSize = 36.sp,
            lineHeight = 55.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(24.dp)
                    ),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.12f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Image(
                        painter = painterResource(id = R.drawable.syprofile),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(Brand.ProfileImageSize)
                            .clip(CircleShape)
                            .border(
                                Brand.ProfileBorderWidth,
                                Color.White,
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Wendy Michelle Sy",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "UI/UX Designer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Brand.SubtitleColor
                    )

                    Spacer(modifier = Modifier.height(Brand.MainGapHeight))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        ContactButton(
                            icon = Icons.Default.Call,
                            text = "976 333 9493",
                            onClickLabel = "Call Michelle"
                        )

                        ContactButton(
                            icon = Icons.Default.Share,
                            text = "Michelle Sy",
                            onClickLabel = "Social Media"
                        )

                        ContactButton(
                            icon = Icons.Default.Email,
                            text = "wmsy98844@liceo.edu.ph",
                            onClickLabel = "Email Michelle"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactButton(
    icon: ImageVector,
    text: String,
    onClickLabel: String
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = onClickLabel,
                onClick = { }
            ),
        shape = RoundedCornerShape(20.dp),
        color = Brand.CardBackground
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun BusinessCardDarkPreview() {
    MaterialTheme {
        BusinessCard()
    }
}

@Preview(
    showBackground = true,
    fontScale = 1.5f,
    name = "Large Font"
)
@Composable
fun BusinessCardFontPreview() {
    MaterialTheme {
        BusinessCard()
    }
}