package uz.madatbek.infoapp.domain

import uz.madatbek.infoapp.data.model.NodeData

interface AppRepository {
    fun getAllInfo():List<NodeData>

    fun getInfoForTxt(txt:String):List<NodeData>
    fun getAndroid():List<NodeData>
    fun getIOS():List<NodeData>
    fun getWep():List<NodeData>
    fun getBackend():List<NodeData>
}