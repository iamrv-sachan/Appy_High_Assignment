package com.rajdroid.appyhighassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rajdroid.appyhighassignment.entites.Article
import retrofit2.Converter

@Database(entities = [Article::class],version = 2)
@TypeConverters(Converters::class)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile private var instance: NewsDB? = null

        fun getDatabase(context: Context): NewsDB =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = instance } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, NewsDB::class.java, "news")
                .fallbackToDestructiveMigration()
                .build()
    }

}