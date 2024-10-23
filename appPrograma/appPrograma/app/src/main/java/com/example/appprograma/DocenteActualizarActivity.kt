package com.example.appprograma

import android.content.DialogInterface
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

class DocenteActualizarActivity : AppCompatActivity() {
    private lateinit var txtCodigo:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var txtSueldo:TextInputEditText
    private lateinit var txtHijos:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btnEliminar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.docente_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCodigo=findViewById(R.id.txtCodigoActualizar)
        txtNombre=findViewById(R.id.txtNombreActualizar)
        txtPaterno=findViewById(R.id.txtPaternoActualizar)
        txtMaterno=findViewById(R.id.txtMaternoActualizar)
        txtSueldo=findViewById(R.id.txtSueldoActualizar)
        txtHijos=findViewById(R.id.txtHijosActualizar)
        spnSexo=findViewById(R.id.spnSexoActualizar)
        btnGrabar=findViewById(R.id.btnActualizarDocente)
        btnEliminar=findViewById(R.id.btnEliminarDocente)
        btnCerrar=findViewById(R.id.btnVolverDocenteActualizar)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        btnEliminar.setOnClickListener { eliminar() }
        datos()
    }
    fun eliminar(){
        var cod=txtCodigo.text.toString().toInt()
        showAlertConfirm("Seguro de eliminar Docente con ID : "+cod,cod)
    }
    fun grabar(){
        //leer controles
        var cod=txtCodigo.text.toString().toInt()
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sue=txtSueldo.text.toString().toDouble()
        var hijos=txtHijos.text.toString().toInt()
        var sexo=spnSexo.text.toString()
        //crear objeto
        var bean=Docente(cod,nom,pat,mat,sexo,hijos,sue,"")
        //invocar mètodo update
        var salida=DocenteController().update(bean)
        if(salida>0)
            showAlert("Docente Actualizado")
        else
            showAlert("Error en la actualizaciòn")
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
    fun showAlertConfirm(men:String,cod:Int){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener {
                                    dialogInterface: DialogInterface, i: Int ->
           var salida=DocenteController().delete(cod)
            if(salida>0)
                showAlert("Docente Eliminado")
            else
                showAlert("Error en la eliminación")
        })
        builder.setNegativeButton  ("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    fun datos(){
        //recuperar clave
        var info=intent.extras!!
        //invocar a la función findById
        var bean=DocenteController().findById(info.getInt("codigo"))
        txtCodigo.setText(bean.codigo.toString())
        txtNombre.setText(bean.nombre)
        txtPaterno.setText(bean.paterno)
        txtMaterno.setText(bean.materno)
        txtSueldo.setText(bean.sueldo.toString())
        txtHijos.setText(bean.hijos.toString())
        spnSexo.setText(bean.sexo,false)
    }

}