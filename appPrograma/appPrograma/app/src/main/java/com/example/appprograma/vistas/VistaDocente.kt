package com.example.appprograma.vistas

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.R

class VistaDocente(item:View):RecyclerView.ViewHolder(item) {
    var imgFoto:ImageView
    var tvCodigo:TextView
    var tvNombre:TextView
    var tvPaterno:TextView
    var tvMaterno:TextView

    init{
        imgFoto=item.findViewById(R.id.imgFoto)
        tvCodigo=item.findViewById(R.id.tvCodigo)
        tvNombre=item.findViewById(R.id.tvNombre)
        tvPaterno=item.findViewById(R.id.tvPaterno)
        tvMaterno=item.findViewById(R.id.tvMaterno)
    }
}