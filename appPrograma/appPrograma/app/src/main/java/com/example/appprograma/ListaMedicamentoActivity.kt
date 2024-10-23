package com.example.appprograma

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprograma.adaptador.MedicamentoAdapter
import com.example.appprograma.clases.Medicamento
import com.example.appprograma.services.ApiMedicamento
import com.example.appprograma.utils.ApiUtils
import com.example.appprograma.utils.AppConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaMedicamentoActivity : AppCompatActivity() {
    private lateinit var btnNuevo:Button
    private lateinit var rvMedicamentos:RecyclerView
    private lateinit var btnMenu:Button
    //declarar un atributo de la interfaz ApiServicesMedicamento
    private lateinit var apiMed:ApiMedicamento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista_medicamento_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        btnNuevo=findViewById(R.id.btnNuevoMedicamento)
        btnMenu=findViewById(R.id.btnMenuMedicamento)
        rvMedicamentos=findViewById(R.id.rvMedicamentos)
        //instanciar atributo apiMed
        apiMed =ApiUtils.getAPIServiceMedicamento();

        listado()
        //
        btnNuevo.setOnClickListener {
            var intent=Intent(this,MedicamentoActivity::class.java)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            var intent=Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

    }
    fun listado(){
//invocar al metodo  fidall
        apiMed.findAll().enqueue(object :Callback<List<Medicamento>>{
            override fun onResponse(
                call: Call<List<Medicamento>>,
                response: Response<List<Medicamento>>
            ) {
                if(response.isSuccessful){
                    //obtner lista de medicamentos
                    var data =response.body()
                    var adaptador=MedicamentoAdapter(data !!)
                    rvMedicamentos.layoutManager=LinearLayoutManager(AppConfig.CONTEXTO)
                    rvMedicamentos.adapter=adaptador
                }
            }

            override fun onFailure(call: Call<List<Medicamento>>, t: Throwable) {
Toast.makeText(AppConfig.CONTEXTO,t.localizedMessage,Toast.LENGTH_LONG).show()            }

        })


    }

}




