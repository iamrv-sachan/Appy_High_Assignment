package com.rajdroid.appyhighassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rajdroid.appyhighassignment.entites.Article

@Dao
interface NewsDao {

    @Query ("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (article :  List<Article> ): List<Long>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()

}