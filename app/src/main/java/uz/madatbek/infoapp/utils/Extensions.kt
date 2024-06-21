package com.example.uzummarketclient.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity


fun String.myLog() = Log.d("TTT", this)
fun String.onlyLetters() = all { it.isLetter() }
fun String.myShortToast(context: Context){
    Toast.makeText(context,this , Toast.LENGTH_SHORT).show()
}
fun FragmentActivity.statusBarTRANSPARENT()=this.apply{
    if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
    }
    if (Build.VERSION.SDK_INT >= 19) {
//            SYSTEM_UI_FLAG_LAYOUT_STABLE
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
    if (Build.VERSION.SDK_INT >= 21) {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        this.window.statusBarColor = Color.TRANSPARENT
    }
    this.window.statusBarColor = Color.TRANSPARENT
}

private fun FragmentActivity.setWindowFlag(bits: Int, on: Boolean) {
    val win = this.window
    val winParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}
