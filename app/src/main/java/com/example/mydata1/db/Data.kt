package com.example.mydata1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mydata_table")
data class Data (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int ,
    @ColumnInfo(name = "name")
    var name:String ,
    @ColumnInfo(name = "email")
    var email:String)