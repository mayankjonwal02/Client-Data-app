package com.example.mydata1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydata1.db.Data
import com.example.mydata1.db.Datadao

class clientviewmodelfactory(
    private val dao:Datadao
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(clientviewmodel::class.java)){
            return clientviewmodel(dao) as T
        }
        else
        {
            throw IllegalArgumentException("unknown view model class")
        }

    }
}