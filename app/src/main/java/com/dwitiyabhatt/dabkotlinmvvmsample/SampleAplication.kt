package com.dwitiyabhatt.dabkotlinmvvmsample

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SampleAplication : Application() {
    //private NetComponent mNetComponent;
    var activity: Activity? = null
    var appCompactActivity: AppCompatActivity? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    }



    fun savePreferenceDataString(key: String?, value: String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun savePreferenceDataInteger(key: String?, value: Int?) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value!!)
        editor.commit()
    }

    fun savePreferenceDataBoolean(key: String?, value: Boolean?) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }
    override fun onTerminate() {
        super.onTerminate()
        if (mInstance != null) {
            mInstance = null
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    fun setAppCompatActivity(appCompatActivity: AppCompatActivity?) {
        appCompactActivity = appCompatActivity
    }

    companion object {
        private var mInstance: SampleAplication? = null
        private var sharedPreferences: SharedPreferences? = null
        fun getmInstance(): SampleAplication? {
            return mInstance
        }

        fun getmInstanceApplication(): SampleAplication? {
            return mInstance
        }

        fun clearePreferenceData() {
            val editor = sharedPreferences!!.edit()
            editor.clear()
            editor.commit()
        }
    }
}