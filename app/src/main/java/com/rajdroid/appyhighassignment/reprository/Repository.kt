package com.rajdroid.appyhighassignment.reprository

import com.rajdroid.appyhighassignment.performGetOperation
import com.rajdroid.appyhighassignment.retrofit.NewsRetro
import com.rajdroid.appyhighassignment.room.NewsDao
import javax.inject.Inject

class Repository  @Inject constructor(val newsRetro: NewsRetro, val newsDao: NewsDao){

    fun getNews() = performGetOperation(
        databaseQuery = { newsDao.getAllArticles() },
        networkCall = { newsRetro.getAllNews() },
        saveCallResult = { newsDao.insertAll(it.articles) }
    )

}