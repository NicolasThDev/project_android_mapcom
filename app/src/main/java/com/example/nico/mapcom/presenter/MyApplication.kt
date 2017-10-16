package com.example.nico.mapcom.presenter

import android.app.Application
import android.content.Context
import com.example.nico.mapcom.model.DaoMaster
import com.example.nico.mapcom.model.DaoSession
import com.facebook.stetho.Stetho
import com.google.gson.Gson
import org.greenrobot.greendao.database.Database

class MyApplication : Application() {

    override fun onCreate() {
        instance = this
        gson = Gson()
        Stetho.initializeWithDefaults(this)
        setupDatabase()
        super.onCreate()
    }

    fun setupDatabase(){
        var helper : DaoMaster.DevOpenHelper = DaoMaster.DevOpenHelper(this, "mapcom-bd")
        var db : Database = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }

    companion object {
        var gson :Gson? = null
        var daoSession : DaoSession? = null
        var instance: MyApplication? = null
            private set

        val context : Context
            get() = instance!!.applicationContext
    }
}
