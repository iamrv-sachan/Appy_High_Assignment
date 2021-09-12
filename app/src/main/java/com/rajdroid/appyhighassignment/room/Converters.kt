package com.rajdroid.appyhighassignment.room

import androidx.room.TypeConverter
import com.rajdroid.appyhighassignment.entites.Source

class Converters {


    @TypeConverter
    fun fromSource(source: Source):String{

        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}