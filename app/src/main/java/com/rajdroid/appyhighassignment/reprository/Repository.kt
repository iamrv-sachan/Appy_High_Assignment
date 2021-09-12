package com.rajdroid.appyhighassignment.reprository

import androidx.lifecycle.MutableLiveData
import com.rajdroid.appyhighassignment.entites.Article
import com.rajdroid.appyhighassignment.performGetOperation
import com.rajdroid.appyhighassignment.retrofit.NewsRetro
import com.rajdroid.appyhighassignment.room.NewsDao
import java.util.function.BinaryOperator
import javax.inject.Inject

class Repository  @Inject constructor(val newsRetro: NewsRetro, val newsDao: NewsDao){

    fun getNews1() = performGetOperation(
        databaseQuery = { newsDao.getAllArticles() },
        networkCall = { newsRetro.getAllNews("india") },
        saveCallResult = { newsDao.insertAll(it.articles) }
    )

    fun getNews() = performGetOperation(
        databaseQuery = { newsDao.getAllArticles() },
        networkCall = { newsRetro.getAllNews("USA") },
        saveCallResult = { newsDao.insertAll(it.articles) }
    )

    suspend fun deleteAll()
    {
        newsDao.deleteAll()
    }
}