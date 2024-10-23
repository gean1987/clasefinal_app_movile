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
import com.example.appprograma.clases.Alumno
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AlumnoActualizarActivity : AppCompatActivity() {
    private lateinit var txtCodigo:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btnEliminar:Button
    private lateinit var  bd: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.alumno_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCodigo=findViewById(R.id.txtCodigoAlumnoActualizar)
        txtNombre=findViewById(R.id.txtNombreAlumnoActualizar)
        txtPaterno=findViewById(R.id.txtPaternoAlumnoActualizar)
        txtMaterno=findViewById(R.id.txtMaternoAlumnoActualizar)
        spnSexo=findViewById(R.id.spnSexoAlumnoActualizar)
        btnGrabar=findViewById(R.id.btnActualizarAlumno)
        btnEliminar=findViewById(R.id.btnEliminarAlumno)
        btnCerrar=findViewById(R.id.btnVolverAlumnoActualizar)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        btnEliminar.setOnClickListener { eliminar() }
        conectar()
        datos()
    }
    fun eliminar(){
        var dni=txtCodigo.text.toString()
        showAlertConfirm("Seguro de eliminar Alumno",dni)


    }
    fun grabar(){
        //leer controles
        var dni=txtCodigo.text.toString()
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sexo=spnSexo.text.toString()
        //objeto de la clase Docente
        var a=Alumno(dni,nom,pat,mat,sexo)

        //metodo para actualizar
        bd.child("alumno").child(dni).setValue(a)
            .addOnCompleteListener {
                showAlert("alumno actualizado")
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
    fun showAlertConfirm(men:String,dni:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener {
                                    dialogInterface: DialogInterface, i: Int ->
//metodo para eliminar
            bd.child("alumno").child(dni).removeValue()
                .addOnCompleteListener {
                    showAlert("alumno eliminado")
                }
                .addOnFailureListener {
                    showAlert("error")
                }

        })
        builder.setNegativeButton  ("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    fun conectar(){
        FirebaseApp.initializeApp(this)
        bd= FirebaseDatabase.getInstance().reference
    }
    fun datos(){

        var bundle=intent.extras!!
        var dni=bundle.getString("dni")
        var DATA=FirebaseDatabase.getInstance().getReference("alumno")
        DATA.orderByChild("dni").equalTo(dni).
        addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var p=snapshot.children.first()
                    var alu=p.getValue(Alumno::class.java)!!
                    txtCodigo.setText(alu.dni)
                    txtNombre.setText(alu.nombre)
                    txtPaterno.setText(alu.paterno)
                    txtMaterno.setText(alu.materno)
                    spnSexo.setText(alu.sexo,false)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                showAlert(error.message)
            }
        })

    }


}