package uz.madatbek.infoapp.presenter.screen.info

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.databinding.ScreenInfoBinding

class InfoScreen:Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val navArgs by navArgs<InfoScreenArgs>()
    private val info by lazy { navArgs.info }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideKeyboardFrom(requireActivity(),binding.root)
        binding.apply {
            image.setBackgroundResource(info.imageIdRes)
            name.text=info.name
            text.setText(info.text)
            back.setOnClickListener {
                findNavController().navigateUp()
            }
            binding.root.backgroundTintList= ColorStateList.valueOf(info.dominantColor)
        }
    }
    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}