package com.example.appprograma.adaptador
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.AlumnoActualizarActivity
import com.example.appprograma.R
import com.example.appprograma.clases.Alumno
import com.example.appprograma.utils.AppConfig
import com.example.appprograma.vistas.ViewAlumno


class AlumnoAdapter(var lista:List<Alumno>):RecyclerView.Adapter<ViewAlumno>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewAlumno {
        var vista=LayoutInflater.from(parent.context).
                inflate(R.layout.item_alumno,parent,false)
        return ViewAlumno(vista)
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: ViewAlumno, position: Int) {
        //mostrar datos del objeto actual según la posición del parámetro "position"
        holder.tvCodigo.setText(lista.get(position).dni)
        holder.tvNombre.setText(lista.get(position).nombre)


        //obtener contexto
        var CONTEXTO=holder.itemView.context

        //evento clic a cada CARDS
        holder.itemView.setOnClickListener{
            var intent=Intent(AppConfig.CONTEXTO,AlumnoActualizarActivity::class.java)
            intent.putExtra("dni",lista.get(position).dni)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(AppConfig.CONTEXTO,intent,null)
        }


    }
    fun showAlert(a:Context ,men:String){
        val builder=AlertDialog.Builder(a)
        builder.setTitle("Error")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }

}