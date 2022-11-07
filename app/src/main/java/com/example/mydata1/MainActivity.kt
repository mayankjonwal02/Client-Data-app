package com.example.mydata1

import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydata1.db.Data
import com.example.mydata1.db.ddatabase
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
     lateinit var name: EditText
     lateinit var email: EditText
     lateinit var save: Button
     lateinit var delete: Button
    private lateinit var viewmodel: clientviewmodel
    private lateinit var clientrecyclerView: RecyclerView
    private lateinit var adapter: clientrecyclerviewadapter
    private lateinit var selectedClient : Data
    private var islistitemclicked=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        save = findViewById(R.id.save)
        delete = findViewById(R.id.delete)
        clientrecyclerView=findViewById(R.id.rvdata)




        var dao = ddatabase.getinstance(application).datadao()
        var factory = clientviewmodelfactory(dao)
        viewmodel = ViewModelProvider(this, factory).get(clientviewmodel::class.java)

        save.setOnClickListener(){
            if(islistitemclicked)
            {
                updatedata()
                clearInput()
            }
            else {
                saveclientdata()
                clearInput()
            }
        }
        var animation=AnimationUtils.loadAnimation(this,R.anim.zoomout)
        delete.setOnClickListener()
        {
            if(islistitemclicked)
            {
                deletedata()

            }
            clearInput()
        }

        initrecyclerview()

    }
        private fun  saveclientdata()
        {
           // var clientname=name.text.toString()
           // var clientemail=email.text.toString()
            //var data= Data(0,clientname,clientemail)
            //viewmodel.insertdata(data)


            viewmodel.insertdata(
                Data(
                    0,
                    name.text.toString(),
                    email.text.toString()
                )
            )

        }
    private fun updatedata()
    {
        viewmodel.updatedata(
            Data(
                selectedClient.id,
                name.text.toString(),
                email.text.toString()
            )
        )
        //selectedClient=null
        save.text="SAVE"
        delete.text="CLEAR"
        islistitemclicked=false
    }

    private fun deletedata()
    {
        viewmodel.deletedata(
            Data(
                selectedClient.id,
                name.text.toString(),
                email.text.toString()
            )
        )
        //selectedClient=null
        save.text="SAVE"
        delete.text="CLEAR"
        islistitemclicked=false
    }
    private fun clearInput()
    {
        name.setText("")
        email.setText("")

    }

    private fun initrecyclerview()
    {
        clientrecyclerView.layoutManager=LinearLayoutManager(this)
        adapter=clientrecyclerviewadapter{
            selectedClient:Data -> listitemclicked(selectedClient)
    }
        clientrecyclerView.adapter=adapter

        displaylist()
    }

    private fun displaylist()
    {
        viewmodel.mydata.observe(
            this,{
                adapter.setlist(it)
                adapter.notifyDataSetChanged()
            }
        )
    }

    private fun listitemclicked(client: Data){
        selectedClient = client
        save.text="UPDATE"
        delete.text="DELETE"
        islistitemclicked=true
        name.setText(selectedClient.name)
        email.setText(selectedClient.email)

    }



}