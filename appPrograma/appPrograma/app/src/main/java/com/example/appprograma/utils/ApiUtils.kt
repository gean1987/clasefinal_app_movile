package com.example.appprograma.utils

import com.example.appprograma.services.ApiMedicamento

class ApiUtils {
    companion object {
        val BASE_URL="https://clinica-at3l.onrender.com"

        fun getAPIServiceMedicamento(): ApiMedicamento {
            return RetrofitClient.getClient(BASE_URL).create(ApiMedicamento::class.java)
        }



    }
}


