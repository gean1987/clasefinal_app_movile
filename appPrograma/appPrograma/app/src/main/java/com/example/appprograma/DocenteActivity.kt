package com.example.appprograma

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appprograma.clases.Docente
import com.example.appprograma.controller.DocenteController
import com.google.android.material.textfield.TextInputEditText

class DocenteActivity : AppCompatActivity() {
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var txtSueldo:TextInputEditText
    private lateinit var txtHijos:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.docente_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtNombre=findViewById(R.id.txtNombre)
        txtPaterno=findViewById(R.id.txtPaterno)
        txtMaterno=findViewById(R.id.txtMaterno)
        txtSueldo=findViewById(R.id.txtSueldo)
        txtHijos=findViewById(R.id.txtHijos)
        spnSexo=findViewById(R.id.spnSexo)
        btnGrabar=findViewById(R.id.btnGrabarDocente)
        btnCerrar=findViewById(R.id.btnCerrarDocente)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
    }
    fun grabar(){
        //leer controles
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sue=txtSueldo.text.toString().toDouble()
        var hijos=txtHijos.text.toString().toInt()
        var sexo=spnSexo.text.toString()
        //crear objeto de la clase Docente
        var doc=Docente(0,nom,pat,mat,sexo,hijos,sue,"")
        //invocar al mètodo save
        var salida=DocenteController().save(doc)
        //validar salida
        if(salida>0)
            showAlert("Docente registrado")
        else
            showAlert("Error en el registro")
    }
    fun cerrar(){
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
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