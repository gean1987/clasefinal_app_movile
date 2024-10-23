package com.example.appprograma.clases

import java.io.Serializable

class Alumno {
    var dni:String=""
    var nombre:String=""
    var paterno:String=""
    var materno:String=""
    var sexo:String=""
    constructor(dni:String,nombre:String, paterno:String, materno:String, sexo:String){
        this.dni=dni
        this.nombre=nombre
        this.paterno=paterno
        this.materno=materno
        this.sexo=sexo
    }
    constructor(){

    }
}