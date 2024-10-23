package com.example.appprograma.controller

import android.content.ContentValues
import com.example.appprograma.clases.Docente
import com.example.appprograma.data.InitBD

class DocenteController {
    //función que retorne un arreglo de objeto de la clase Docente
    fun findAll():ArrayList<Docente>{
        var lista=ArrayList<Docente>()
        //acceder a la base de datos en modo lectura
        var cn=InitBD().readableDatabase
        //sentencia
        var sql="select *from tb_docente"
        var rs=cn.rawQuery(sql, null)
        //bucle
        while (rs.moveToNext()){
            var bean=Docente(rs.getInt(0),rs.getString(1),
                            rs.getString(2),rs.getString(3),
                            rs.getString(4),rs.getInt(5),
                            rs.getDouble(6),rs.getString(7))
            lista.add(bean)
        }
        return lista
    }
    //función para registrar Docente
    fun save(bean:Docente):Int{
        var salida=-1
        //acceder a la bd en modo escritura
        var cn=InitBD().writableDatabase
        //crear objeto de la clase ContentValues
        var valores=ContentValues()
        //crear claves dentro del objeto "valores"(los nombres deben coincider con los
        // campos de la tabla tb_docente)
        valores.put("nom",bean.nombre)
        valores.put("pat",bean.paterno)
        valores.put("mat",bean.materno)
        valores.put("sexo",bean.sexo)
        valores.put("hijos",bean.hijos)
        valores.put("sueldo",bean.sueldo)
        valores.put("foto",bean.foto)
        salida=cn.insert("tb_docente","cod",valores).toInt()
        return salida
    }
    fun findById(cod:Int):Docente{
        lateinit var lista:Docente
        var cn=InitBD().readableDatabase
        var sql="select *from tb_docente where cod=?"
        var rs=cn.rawQuery(sql, arrayOf(cod.toString()) )
        if(rs.moveToNext()){
            lista=Docente(rs.getInt(0),rs.getString(1),
                rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getInt(5),
                rs.getDouble(6),rs.getString(7))
        }
        return lista
    }
    fun update(bean:Docente):Int{
        var salida=-1
        var cn=InitBD().writableDatabase
        var valores=ContentValues()
        valores.put("nom",bean.nombre)
        valores.put("pat",bean.paterno)
        valores.put("mat",bean.materno)
        valores.put("sexo",bean.sexo)
        valores.put("hijos",bean.hijos)
        valores.put("sueldo",bean.sueldo)
        valores.put("foto",bean.foto)
        salida=cn.update("tb_docente",valores,"cod=?", arrayOf(bean.codigo.toString()))
        return salida
    }
    fun delete(cod:Int):Int{
        var salida=-1
        var cn=InitBD().writableDatabase
        salida=cn.delete("tb_docente","cod=?", arrayOf(cod.toString()))
        return salida
    }

}