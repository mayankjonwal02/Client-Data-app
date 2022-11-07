package com.example.mydata1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydata1.db.Data
import com.example.mydata1.db.Datadao
import kotlinx.coroutines.launch

class clientviewmodel(private val dao : Datadao):ViewModel() {

    var mydata = dao.getalldata()

    fun insertdata(client:Data)=viewModelScope.launch {
        dao.insertdata(client)
    }

    fun deletedata(client:Data)=viewModelScope.launch {
        dao.deletedata(client)
    }

    fun updatedata(client:Data)=viewModelScope.launch {
        dao.updatedata(client)
    }

}