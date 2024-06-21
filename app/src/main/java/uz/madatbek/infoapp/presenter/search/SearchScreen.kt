package uz.madatbek.infoapp.presenter.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.databinding.ScreenSearchBinding
import uz.madatbek.infoapp.domain.AppRepository
import uz.madatbek.infoapp.domain.AppRepositoryImpl
import uz.madatbek.infoapp.presenter.adapter.SearchAdapter

class SearchScreen:Fragment(R.layout.screen_search) {
    private val binding by viewBinding(ScreenSearchBinding::bind)
    private val adapter by lazy { SearchAdapter() }
    private val repository:AppRepository=AppRepositoryImpl


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        search()
        adapter.submitList(repository.getAllInfo())
        binding.placeHolder.isVisible=false
    }

    private fun initAdapter() {
        binding.apply {
            recyclerView.adapter=adapter
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
            adapter.onClickItem={
                findNavController().navigate(SearchScreenDirections.actionSearchScreenToInfoScreen(it))
            }
        }
    }


    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun search(){
        binding.apply {
            search.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if (search.text.trim().isEmpty()){
                        adapter.submitList(repository.getAllInfo())
                        adapter.setText("")
                        binding.placeHolder.isVisible=false
                    }else{
                        val text=search.text.toString()
                        val data=repository.getInfoForTxt(text)
                        adapter.submitList(data)
                        adapter.setText(text.trim())
                        if (data.size==0){
                            binding.placeHolder.isVisible=true
                        }else{
                            binding.placeHolder.isVisible=false
                        }
                    }

                }
            })
            searchButton.setOnClickListener {
                hideKeyboardFrom(requireActivity(),searchButton)
            }
        }
    }
}