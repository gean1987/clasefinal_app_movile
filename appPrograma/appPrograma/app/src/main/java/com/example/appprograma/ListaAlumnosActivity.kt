package com.example.appprograma

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.adaptador.AlumnoAdapter
import com.example.appprograma.clases.Alumno
import com.example.appprograma.utils.AppConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ListaAlumnosActivity:AppCompatActivity() {
    //atributos
    private lateinit var rvAlumnos:RecyclerView
    private lateinit var btnNuevo:Button
    lateinit var lista:ArrayList<Alumno>
private lateinit var  bd:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista_alumno_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //referencia
        rvAlumnos=findViewById(R.id.rvAlumnos)
        btnNuevo=findViewById(R.id.btnNuevoALumno)

        //
        btnNuevo.setOnClickListener { nuevo() }

        lista=ArrayList<Alumno>()

        conectar()
        listado()

    }

    fun nuevo(){
        var intent=Intent(this,AlumnoActivity::class.java)
        startActivity(intent)
    }
    fun conectar(){
FirebaseApp.initializeApp(this)
        bd=FirebaseDatabase.getInstance().reference
    }
    fun listado(){


        bd.child("alumno").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(row in snapshot.children){
                    val alu=row.getValue(Alumno::class.java)
                    lista.add(alu!!)
                }
                val adapter=AlumnoAdapter(lista)
                rvAlumnos.adapter=adapter
                rvAlumnos.layoutManager=LinearLayoutManager(AppConfig.CONTEXTO)
            }
            override fun onCancelled(error: DatabaseError) {
                showAlert(error.message)
            }
        })


    }
    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }



}