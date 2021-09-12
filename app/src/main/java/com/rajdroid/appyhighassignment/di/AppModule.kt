package com.rajdroid.appyhighassignment.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rajdroid.appyhighassignment.reprository.Repository
import com.rajdroid.appyhighassignment.retrofit.NewsRetro
import com.rajdroid.appyhighassignment.retrofit.NewsService
import com.rajdroid.appyhighassignment.room.NewsDB
import com.rajdroid.appyhighassignment.room.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRetro(newsService: NewsService) = NewsRetro(newsService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = NewsDB.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDB) = db.newsDao()

    @Singleton
    @Provides
    fun provideRepository(newsRetro: NewsRetro,
                          newsDao: NewsDao
    ) =
        Repository(newsRetro, newsDao)

}