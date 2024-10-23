package com.example.appprograma.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appprograma.utils.AppConfig

class InitBD:SQLiteOpenHelper(AppConfig.CONTEXTO,
                    "consorcio_2.bd",
                    null,1) {
    override fun onCreate(p0: SQLiteDatabase) {
        //crear tabla
        p0.execSQL("create table tb_docente" +
                       "(" +
                            "cod integer primary key autoincrement," +
                            "nom varchar(35)," +
                            "pat varchar(35)," +
                            "mat varchar(35)," +
                            "sexo varchar(15)," +
                            "hijos int," +
                            "sueldo double," +
                            "foto varchar(20)" +
                       ")"
                 )
        p0.execSQL("insert into tb_docente values(null,'Ana','Soto','Mora','Femenino',1,2500.0,'d1')")
        p0.execSQL("insert into tb_docente values(null,'Carol','Salas','Oca','Femenino',0,3500.0,'d2')")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}