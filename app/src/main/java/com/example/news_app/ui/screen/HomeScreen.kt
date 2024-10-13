package com.example.news_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.news_app.ui.compo.NewsItem
import com.example.news_app.viewModel.MainViewModel

@Composable
fun HomeScreen(newsViewModel: MainViewModel, navController: NavHostController) {

    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CategoryRow(newsViewModel)
        LazyColumn {
            items(articles) {
                NewsItem(it, navController)
            }
        }
    }

}


@Composable
fun CategoryRow(newsViewModel: MainViewModel) {

    val listOfCategory = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )

    LazyRow {
        items(listOfCategory) {

            Button(
                onClick = {
                    newsViewModel.fetchNewsTopHeadlines(it)
                },
                Modifier.padding(8.dp)
            ) {
                Text(it)
            }
        }
    }
}