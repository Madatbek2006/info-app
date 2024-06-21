package uz.madatbek.infoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uzummarketclient.utils.statusBarTRANSPARENT
import uz.madatbek.infoapp.data.sourse.MyShar

class MainActivity : AppCompatActivity() {
    var oldPos=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusBarTRANSPARENT()
        MyShar.init(this)
    }

    override fun onResume() {
        super.onResume()
        MyShar.setPos(oldPos)
    }

    override fun onStop() {
        super.onStop()
        oldPos=MyShar.getPos()
        MyShar.setPos(0)
    }
}