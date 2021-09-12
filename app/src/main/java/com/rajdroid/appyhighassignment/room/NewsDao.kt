package com.rajdroid.appyhighassignment.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rajdroid.appyhighassignment.entites.Article

@Dao
interface NewsDao {

    @Query ("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (article :  List<Article> ): List<Long>


}