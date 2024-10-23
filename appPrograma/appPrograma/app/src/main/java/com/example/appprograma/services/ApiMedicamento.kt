package com.example.appprograma.services

import com.example.appprograma.clases.Medicamento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiMedicamento {
    @GET("/medicamento/lista")
 fun findAll():Call<List<Medicamento>>

 @POST("/medicamento/registrar")
 fun saveMedicamento(@Body bean:Medicamento):Call<Medicamento>
 //en caso no devuevla nadaen registrar pon void encaso devuelva algo pon medicamento

 //buscar medicamento
 @GET("/medicamento/buscar/{codigo}")
 fun findById (@Path("codigo") cod:Int):Call<Medicamento>

 @PUT("/medicamento/actualizar")
 fun UpdateMedicamento(@Body bean:Medicamento):Call<Medicamento>
 //en caso no devuevla nadaen registrar pon void encaso devuelva algo pon medicamento

 @DELETE("/medicamento/eliminar/{codigo}")
 fun deleteById (@Path("codigo") cod:Int):Call<Void>


}