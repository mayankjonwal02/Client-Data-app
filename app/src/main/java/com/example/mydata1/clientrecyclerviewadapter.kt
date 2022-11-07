package com.example.mydata1

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydata1.db.Data


class clientrecyclerviewadapter(
    private var clickListener: (Data)->Unit
):RecyclerView.Adapter<viewholder>() {

    private var clientlist=ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var layoutinflator=LayoutInflater.from(parent.context)
        var listitem=layoutinflator.inflate(R.layout.list_item,parent,false)
        return viewholder(listitem)
    }



    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(clientlist[position],clickListener)


    }

    override fun getItemCount(): Int {
         return clientlist.size
    }

    fun setlist(client:List<Data>)
    {
        clientlist.clear()
        clientlist.addAll(client)
    }

}

class viewholder(private var view:View):RecyclerView.ViewHolder(view){
    fun bind(client: Data, clickListener: (Data)->Unit){
        var name=view.findViewById<TextView>(R.id.tvname)
        var email=view.findViewById<TextView>(R.id.tvemail)
        name.text=client.name
        email.text=client.email

        view.setOnClickListener(){
            var animation=AnimationUtils.loadAnimation(view.context, R.anim.pop1)
            view.startAnimation(animation)
            clickListener(client)
            //MainActivity().save.text="UPDATE"
            //MainActivity().delete.text="DELETE"

        }


        }





}