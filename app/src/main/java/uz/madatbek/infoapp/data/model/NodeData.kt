package uz.madatbek.infoapp.data.model

import android.os.Parcelable
import androidx.annotation.IdRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class NodeData(
    val name:String,
    val imageIdRes: Int,
    val text: Int,
    var dominantColor:Int=0,
    val type:ArrayList<Int> = arrayListOf()
) : Parcelable