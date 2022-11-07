package com.example.mydata1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class ddatabase: RoomDatabase() {
    abstract fun datadao():Datadao
    companion object{
        @Volatile
        var INSTANCE :ddatabase ? = null
        fun getinstance(context: Context) : ddatabase{
            synchronized(this){
                var instance: ddatabase ? = null
                if (instance==null)
                {
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        ddatabase::class.java,
                        "people_database"
                    ).build()
                }
                return instance
            }
        }

    }

}