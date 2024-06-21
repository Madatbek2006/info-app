package uz.madatbek.infoapp.presenter.screen.splash

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.uzummarketclient.utils.myLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.databinding.ScreenSplashBinding
import uz.madatbek.infoapp.domain.AppRepository
import uz.madatbek.infoapp.domain.AppRepositoryImpl

class SplashScreen:Fragment(R.layout.screen_splash) {
    private val repository=AppRepositoryImpl
    private val binding by viewBinding(ScreenSplashBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            setColor()
            delay(500)
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
        }
    }
    private fun setColor(){
        repository.data.forEach {
                val drawableResId: Int =it.imageIdRes
                val vectorDrawable = ContextCompat.getDrawable(binding.root.context, drawableResId)
                val bitmap = Bitmap.createBitmap(
                    vectorDrawable!!.intrinsicWidth,
                    vectorDrawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
                vectorDrawable.draw(canvas)
                val palette: Palette = Palette.from(bitmap).generate()
                val dominantColor = palette.getDominantColor(Color.BLACK)
                it.dominantColor= dominantColor
        }
    }
}