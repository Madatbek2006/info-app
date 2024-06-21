package uz.madatbek.infoapp.domain

import android.annotation.SuppressLint
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.data.model.NodeData

@SuppressLint("ResourceType")
object AppRepositoryImpl:AppRepository {

    var data:ArrayList<NodeData>

    init {
        data= arrayListOf(
            NodeData("JavaScript", R.drawable.javascript_logo,R.string.javaScript, type = arrayListOf<Int>(2,4)),
            NodeData("Python",R.drawable.python,R.string.python,type = arrayListOf<Int>(2,4) ),
            NodeData("Go",R.drawable.go,R.string.Go,type = arrayListOf<Int>(2)),
            NodeData("Java",R.drawable.java,R.string.java,type = arrayListOf<Int>(1,4)),
            NodeData("C",R.drawable.c,R.string.c,type = arrayListOf<Int>(1)),
            NodeData("C++",R.drawable.c__,R.string.c__,type = arrayListOf<Int>(1)),
            NodeData("C#",R.drawable.c_,R.string.c_,type = arrayListOf<Int>(1,2)),
            NodeData("R",R.drawable.r,R.string.r),
            NodeData("Swift",R.drawable.swift2,R.string.swift,type = arrayListOf<Int>(3)),
            NodeData("Rust",R.drawable.rust,R.string.rust,type = arrayListOf<Int>(2)),
            NodeData("Ruby",R.drawable.ruby,R.string.ruby,type = arrayListOf<Int>(2)),
            NodeData("Dart",R.drawable.dart,R.string.dart, type = arrayListOf(1,2,3,4)),
            NodeData("Kotlin",R.drawable.kotlin,R.string.kotlin, type = arrayListOf(1,2,3,4)),
        )
        data.sortBy { it.name }
    }
    override fun getAllInfo(): List<NodeData> {
        return data
    }

    override fun getInfoForTxt(txt: String): List<NodeData> {
        val myData=ArrayList<NodeData>()
        for (i in 0 until data.size){
            if (data[i].name.trim().lowercase().contains(txt.trim().lowercase())){
                myData.add(data[i])
            }
        }
        myData.sortBy { txt }
        return myData
    }

    override fun getAndroid(): List<NodeData> {
        val myData=ArrayList<NodeData>()
        for (i in 0 until data.size){
            if (data[i].type.contains(1)){
                myData.add(data[i])
            }
        }
        return myData
    }
    override fun getIOS(): List<NodeData> {
        val myData=ArrayList<NodeData>()
        for (i in 0 until data.size){
            if (data[i].type.contains(3)){
                myData.add(data[i])
            }
        }
        return myData
    }

    override fun getWep(): List<NodeData> {
        val myData=ArrayList<NodeData>()
        for (i in 0 until data.size){
            if (data[i].type.contains(2)){
                myData.add(data[i])
            }
        }
        return myData
    }

    override fun getBackend(): List<NodeData> {
        val myData=ArrayList<NodeData>()
        for (i in 0 until data.size){
            if (data[i].type.contains(4)){
                myData.add(data[i])
            }
        }
        return myData
    }


}