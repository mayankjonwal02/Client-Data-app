package com.example.mydata1.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Datadao {
    @Insert
    suspend fun insertdata(data: Data)

    @Update
    suspend fun updatedata(data: Data)

    @Delete
    suspend fun deletedata(data: Data)

    @Query("Select * from mydata_table")
    fun getalldata():LiveData<List<Data>>
}