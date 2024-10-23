package com.example.appprograma.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.DocenteActualizarActivity
import com.example.appprograma.MedicamentoActualizarActivity
import com.example.appprograma.R
import com.example.appprograma.clases.Medicamento
import com.example.appprograma.utils.AppConfig
import com.example.appprograma.vistas.VistaDocente
import com.example.appprograma.vistas.VistaMedicamento

class MedicamentoAdapter(var data:List<Medicamento>):RecyclerView.Adapter<VistaMedicamento>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaMedicamento {
        var item=LayoutInflater.from(parent.context).inflate(R.layout.item_medicamento,parent,false)
        return VistaMedicamento(item)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: VistaMedicamento, position: Int) {
        //mostrar datos
        holder.tvCodigo.setText(data.get(position).codigo.toString())
        holder.tvNombre.setText(data.get(position).nombre)
        //
        holder.itemView.setOnClickListener {
            var intent=Intent(AppConfig.CONTEXTO,MedicamentoActualizarActivity::class.java)
            //
            intent.putExtra("codigo",data.get(position).codigo)
            ///
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(AppConfig.CONTEXTO,intent,null)
        }


    }
}