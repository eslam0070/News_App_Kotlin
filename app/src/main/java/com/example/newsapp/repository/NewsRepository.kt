package com.example.newsapp.repository

import androidx.room.Query
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.dp.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepository (val db:ArticleDatabase){
    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int)=RetrofitInstance.api.searchNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSaveNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)
}