package uz.madatbek.infoapp.data.sourse

import android.content.Context
import android.content.SharedPreferences

object MyShar {
    private lateinit var sharedPreferences:SharedPreferences

    fun init(context: Context){
        sharedPreferences=context.getSharedPreferences("salom",Context.MODE_PRIVATE)
    }


    fun setPos(pos:Int){
        sharedPreferences.edit().putInt("pos",pos).apply()
    }

    fun getPos():Int{
        return sharedPreferences.getInt("pos",0)
    }

}