package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.AppViewModelFactory
import com.example.myapplication.ui.MySocialApp
import com.example.myapplication.ui.PostsViewModel
import com.example.myapplication.ui.ThemeViewModel
import com.example.myapplication.ui.theme.MySocialTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val factory = AppViewModelFactory(applicationContext)

            val postsVm: PostsViewModel = viewModel(factory = factory)
            val themeVm: ThemeViewModel = viewModel(factory = factory)

            val darkTheme by themeVm.isDarkTheme.collectAsStateWithLifecycle()

            MySocialTheme(
                darkTheme = darkTheme,
                dynamicColor = false
            ) {
                MySocialApp(
                    postsVm = postsVm,
                    themeVm = themeVm
                )
            }
        }
    }
}