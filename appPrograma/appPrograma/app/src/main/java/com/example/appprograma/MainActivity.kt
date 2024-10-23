package com.example.appprograma

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.adaptador.DocenteAdapter
import com.example.appprograma.controller.DocenteController

class MainActivity : AppCompatActivity() {
    private lateinit var btnNuevo:Button
    private lateinit var rvDocentes:RecyclerView
    private lateinit var btnMenu:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        btnNuevo=findViewById(R.id.btnNuevoDocente)
        btnMenu=findViewById(R.id.btnMenuDocente)
        rvDocentes=findViewById(R.id.rvDocentes)
        //crear objeto de la clase DocenteAdapter
        var adaptador=DocenteAdapter(DocenteController().findAll())
        //estilo del RecyclerView
        rvDocentes.layoutManager=LinearLayoutManager(this)//,LinearLayoutManager.HORIZONTAL,false)
        //enviar el objeto adaptador dentro de rvDocentes
        rvDocentes.adapter=adaptador
        //
        btnNuevo.setOnClickListener {
            var intent=Intent(this,DocenteActivity::class.java)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            var intent=Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

    }
}




