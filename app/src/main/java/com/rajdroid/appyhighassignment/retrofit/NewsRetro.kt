package com.rajdroid.appyhighassignment.retrofit

import javax.inject.Inject

class NewsRetro @Inject constructor(val newsService: NewsService) : BaseDataSource(){

    suspend fun getAllNews(s:String) = getResult{newsService.getDataFromAPI(s)}


}