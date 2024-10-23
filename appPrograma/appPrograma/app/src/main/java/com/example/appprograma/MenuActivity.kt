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

class MenuActivity : AppCompatActivity() {
    private lateinit var btnMedicamento:Button
    private lateinit var btnDocente:Button
    private lateinit var btnAlumno:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        btnMedicamento=findViewById(R.id.btnMedicamento)
        btnDocente=findViewById(R.id.btnDocente)
        btnAlumno=findViewById(R.id.btnAlumno)

        btnDocente.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnMedicamento.setOnClickListener {
            var intent=Intent(this,ListaMedicamentoActivity::class.java)
            startActivity(intent)
        }
        btnAlumno.setOnClickListener {
            var intent=Intent(this,ListaAlumnosActivity::class.java)
            startActivity(intent)
        }
    }
}




