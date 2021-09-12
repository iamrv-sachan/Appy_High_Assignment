package com.rajdroid.appyhighassignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajdroid.appyhighassignment.entites.Article
import com.rajdroid.appyhighassignment.reprository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val news = repository.getNews()
    val news1 = repository.getNews1()



}