package com.example.appprograma

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appprograma.clases.Medicamento
import com.example.appprograma.services.ApiMedicamento
import com.example.appprograma.utils.ApiUtils
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MedicamentoActualizarActivity : AppCompatActivity() {
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtStock:TextInputEditText
    private lateinit var txtPrecio:TextInputEditText
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btnEliminar:Button
    //declaramos el objeto medicamento
    private lateinit var apiMed: ApiMedicamento
    var codigo=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.medicamento_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtNombre=findViewById(R.id.txtEditarNombreMedicamento)
        txtStock=findViewById(R.id.txtEditarStock)
        txtPrecio=findViewById(R.id.txtEditarPrecio)
        btnGrabar=findViewById(R.id.btnActualizarMedicamento)
        btnCerrar=findViewById(R.id.btnCerrarEditarMedicamento)
        btnEliminar=findViewById(R.id.btnEliminarMedicamento)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        btnEliminar.setOnClickListener { eliminar() }

        //lo instanciamos para que se sincronize con los endpoints
        apiMed =ApiUtils.getAPIServiceMedicamento();

        datos()
    }
    fun grabar(){
        //leer controles
        var nom=txtNombre.text.toString()
        var stock=txtStock.text.toString().toInt()
        var pre=txtPrecio.text.toString().toDouble()
        //crear objeto de la clase Docente
        var m=Medicamento(codigo,nom,stock,pre)
        showAlert("medicamento actualizado id "+codigo)

        apiMed.UpdateMedicamento(m).enqueue(object :Callback<Medicamento>{
            override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
                if(response.isSuccessful){
                    var med =response.body()!!
                    showAlert("medicamento actualizado con id "+med.codigo)

                }
            }
            override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                showAlert(t.localizedMessage)

            }

        })
    }
    fun cerrar(){
        var intent=Intent(this,ListaMedicamentoActivity::class.java)
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

    fun datos(){
        //recuperar clave
        var bundle=intent.extras!!
        var cod=bundle.getInt("codigo")
//inicio metodo
        apiMed.findById(cod).enqueue(object :Callback<Medicamento>{
            override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
                if(response.isSuccessful){

                    var bean =response.body()!!
                    codigo=bean.codigo
                    txtNombre.setText(bean.nombre)
                    txtStock.setText(bean.stock.toString())
                    txtPrecio.setText(bean.precio.toString())

                }
            }

            override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                showAlert(t.localizedMessage)
            }

        })

//fin metodo








    }
    fun eliminar(){

        apiMed.deleteById(codigo).enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    showAlert("medicamento eliminado con id "+codigo)
                }            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showAlert(t.localizedMessage)
            }

        })



    }


}