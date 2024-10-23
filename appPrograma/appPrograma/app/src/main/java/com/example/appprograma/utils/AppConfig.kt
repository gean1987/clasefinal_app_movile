package com.example.appprograma.utils

import android.app.Application
import android.content.Context

class AppConfig:Application() {
    companion object{
        lateinit var CONTEXTO:Context
    }
    override fun onCreate() {
        CONTEXTO=applicationContext
        super.onCreate()
    }
}