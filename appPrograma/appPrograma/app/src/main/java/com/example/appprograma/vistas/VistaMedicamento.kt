package com.example.appprograma.vistas

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.R

class VistaMedicamento(item:View):RecyclerView.ViewHolder(item) {
    var imgFoto:ImageView
    var tvCodigo:TextView
    var tvNombre:TextView

    init{
        imgFoto=item.findViewById(R.id.imgFotoMedicamento)
        tvCodigo=item.findViewById(R.id.tvCodigoMedicamento)
        tvNombre=item.findViewById(R.id.tvNombreMedicamento)

    }
}