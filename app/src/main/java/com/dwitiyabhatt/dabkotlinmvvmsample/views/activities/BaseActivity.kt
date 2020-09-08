package com.cmexpertise.yogakotlin.views.activities

import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dwitiyabhatt.dabkotlinmvvmsample.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    fun showToast(message : String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(view : View, message : String){
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        snackBar.show()
    }

    fun getTrimmedText(editText: EditText): String? {
        return editText.text.toString().trim()

    }

    fun showHideProgress(
        isShowProgress: Boolean,
        progressBar: FrameLayout
    ) {
        if (isShowProgress) {
            progressBar.visibility = View.VISIBLE
            progressBar.isEnabled = false
        } else {
            progressBar.visibility = View.GONE
            progressBar.isEnabled = true
        }
    }




}