package com.cmexpertise.yogakotlin.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dwitiyabhatt.dabkotlinmvvmsample.R


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT : Long = 3000
    private var mDelayHandler : Handler? = null

    val runnable : Runnable = Runnable {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

          mDelayHandler = Handler()

          mDelayHandler!!.postDelayed(runnable,SPLASH_TIME_OUT)

    }


}