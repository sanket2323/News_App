package com.example.news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.news_app.ui.compo.NewsArticlePage
import com.example.news_app.ui.screen.HomeScreen
import com.example.news_app.ui.theme.News_AppTheme
import com.example.news_app.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            News_AppTheme {
                val NewsViewModelProvider = ViewModelProvider(this)[MainViewModel::class.java]
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "NEWS TODAY",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.DarkGray,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif
                        )

                        NavHost(navController = navController, startDestination = HomePageScreen){
                            composable<HomePageScreen> {
                                HomeScreen(NewsViewModelProvider,navController)
                            }

                            composable<NewsArticleScreen> {
                                val args = it.toRoute<NewsArticleScreen>()
                                NewsArticlePage(args.url)
                            }
                        }

                    }
                }
            }
        }
    }
}

