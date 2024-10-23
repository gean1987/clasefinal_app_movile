package com.example.appprograma.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.DocenteActualizarActivity
import com.example.appprograma.R
import com.example.appprograma.clases.Docente
import com.example.appprograma.utils.AppConfig
import com.example.appprograma.vistas.VistaDocente

class DocenteAdapter(var data:ArrayList<Docente>):RecyclerView.Adapter<VistaDocente>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDocente {
        //convertir en View el archivo item_docente.xml
        var item=LayoutInflater.from(parent.context).inflate(R.layout.item_docente,parent,false)
        return VistaDocente(item)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: VistaDocente, position: Int) {
        //mostrar datos
        holder.tvCodigo.setText(data.get(position).codigo.toString())
        holder.tvNombre.setText(data.get(position).nombre)
        holder.tvPaterno.setText(data.get(position).paterno)
        holder.tvMaterno.setText(data.get(position).materno)
        //
        holder.itemView.setOnClickListener {
            var intent=Intent(AppConfig.CONTEXTO,DocenteActualizarActivity::class.java)
            //
            intent.putExtra("codigo",data.get(position).codigo)
            ///
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(AppConfig.CONTEXTO,intent,null)
        }


    }
}