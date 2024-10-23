package com.example.appprograma

import android.content.Intent
import android.os.Bundle

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


class MedicamentoActivity : AppCompatActivity() {
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtStock:TextInputEditText
    private lateinit var txtPrecio:TextInputEditText
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    //declaramos el objeto medicamento
    private lateinit var apiMed: ApiMedicamento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.medicamento_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtNombre=findViewById(R.id.txtNombreMedicamento)
        txtStock=findViewById(R.id.txtStock)
        txtPrecio=findViewById(R.id.txtPrecio)
        btnGrabar=findViewById(R.id.btnGrabarMedicamento)
        btnCerrar=findViewById(R.id.btnCerrarMedicamento)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        //lo instanciamos para que se sincronize con los endpoints
        apiMed =ApiUtils.getAPIServiceMedicamento();

    }
    fun grabar(){
        //leer controles
        var nom=txtNombre.text.toString()
        var stock=txtStock.text.toString().toInt()
        var pre=txtPrecio.text.toString().toDouble()
        //crear objeto de la clase Docente
        var m=Medicamento(0,nom,stock,pre)


apiMed.saveMedicamento(m).enqueue(object :Callback<Medicamento>{
    override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
        if(response.isSuccessful){
             var med =response.body()!!
            showAlert("medicamento agregado con id "+med.codigo)
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

}