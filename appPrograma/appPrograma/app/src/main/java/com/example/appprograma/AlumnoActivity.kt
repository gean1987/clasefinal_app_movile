package com.example.appprograma

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appprograma.clases.Alumno

import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AlumnoActivity : AppCompatActivity() {
    private lateinit var txtDni:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btn:Button

    private lateinit  var bd:DatabaseReference

    //atributo para la conexión con la base de datos de firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.alumno_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtDni=findViewById(R.id.txtDniAlumno)
        txtNombre=findViewById(R.id.txtNombreAlumno)
        txtPaterno=findViewById(R.id.txtPaternoAlumno)
        txtMaterno=findViewById(R.id.txtMaternoAlumno)
        spnSexo=findViewById(R.id.spnSexoAlumno)
        btnGrabar=findViewById(R.id.btnGrabarAlumno)
        btnCerrar=findViewById(R.id.btnCerrarAlumno)
        btnGrabar.setOnClickListener { grabar() }
        conectar()
        btnCerrar.setOnClickListener { cerrar()}
    }
    fun grabar(){
        //leer controles
        var dni=txtDni.text.toString()
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sexo=spnSexo.text.toString()
        var bean = Alumno(dni, nom, pat, mat, sexo)
        //crear la colección "alumno"  ----->  Colecciòn y documento
        //dni seria su id alser un valor unico
        bd.child("alumno").child(dni).setValue(bean)
            .addOnCompleteListener {
                showAlert("alumno registrado")
            }
            .addOnFailureListener {
                showAlert("error")
            }
    }
    fun cerrar(){
       var intent=Intent(this,ListaAlumnosActivity::class.java)
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
    //función para obtener la conexión de la base de datos
    fun conectar(){
        //iniciar Firebase en la clase actual
         FirebaseApp.initializeApp(this)
        //instanciar bd
        bd=FirebaseDatabase.getInstance().reference
    }

}