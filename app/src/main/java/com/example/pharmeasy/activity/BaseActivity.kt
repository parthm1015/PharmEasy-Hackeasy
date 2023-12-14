package com.example.pharmeasy.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


abstract class BaseActivity : AppCompatActivity() {

    protected var progressIndicator: ProgressBar? = null

    private var dialog: Dialog? = null

    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
        mp?.release()
    }

    protected fun hideKeyboard() {
        val view = currentFocus ?: return
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    protected fun message(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM ,0,200)
        toast.show()
    }


    private fun openLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("sessionexpired", "true")
        startActivity(intent)
        finish()
    }

}
