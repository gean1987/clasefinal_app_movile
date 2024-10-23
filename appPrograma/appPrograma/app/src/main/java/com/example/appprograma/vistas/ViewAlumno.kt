package com.example.appprograma.vistas

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.R


class ViewAlumno(item:View): RecyclerView.ViewHolder(item) {
    //declarar atributos
    var tvCodigo:TextView
    var tvNombre:TextView
    var imgFoto:ImageView

    /*bloque init (referenciar)*/
    init{
        tvCodigo=item.findViewById(R.id.tvCodigoAlumno)
        tvNombre=item.findViewById(R.id.tvNombreAlumno)
        imgFoto=item.findViewById(R.id.imgFotoAlumno)
    }
}