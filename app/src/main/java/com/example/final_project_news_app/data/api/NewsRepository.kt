package com.example.final_project_news_app.data.api

import com.example.final_project_news_app.data.api.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getHeadlines(countryCode = countryCode, page = pageNumber)

    suspend fun getSearchNews(query: String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)
}