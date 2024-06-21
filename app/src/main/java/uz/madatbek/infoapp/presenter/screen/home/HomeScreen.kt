package uz.madatbek.infoapp.presenter.screen.home

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.data.sourse.MyShar
import uz.madatbek.infoapp.databinding.ScreenHomeBinding
import uz.madatbek.infoapp.domain.AppRepository
import uz.madatbek.infoapp.domain.AppRepositoryImpl
import uz.madatbek.infoapp.presenter.adapter.HomeAdapter


class HomeScreen : Fragment(R.layout.screen_home) {
    private val myShar=MyShar
    private val adapter by lazy { HomeAdapter() }
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeVM by viewModels<HomeVMImpl>()
    private val repository:AppRepository=AppRepositoryImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        initViewModel()
        viewModel.loadInfo()
        initDrawerLayout()
        hideKeyboardFrom(requireActivity(),binding.root)
        binding.search.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToSearchScreen())
        }
    }


    private fun initAdapter() {
        binding.apply {
            recyclerView.adapter = adapter
//            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            adapter.onClickItem={
                findNavController().navigate(HomeScreenDirections.actionHomeScreenToInfoScreen(it))
            }
        }
    }

    private fun initViewModel() {
        viewModel.loadListener = {data->
            adapter.submitList(data)
            binding.recyclerView.scrollToPosition(0)
        }
    }
    fun initDrawerLayout(){
        val drawerLayout = binding.drawerLayout
        val drawerToggle = ActionBarDrawerToggle(requireActivity(), drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        drawerLayout.closeDrawers()

        binding.menu.setOnClickListener {
            // Toggle the drawer when the button is clicked
            drawerLayout.openDrawer(GravityCompat.START)
            hideKeyboardFrom(requireActivity(),binding.menu)
        }

        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.all->{
                    myShar.setPos(0)
                    viewModel.loadInfo()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.android -> {
                    myShar.setPos(1)
                    viewModel.loadInfo()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.wep->{
                    myShar.setPos(2)
                    viewModel.loadInfo()
                    adapter.submitList(repository.getWep())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.iOS->{
                    myShar.setPos(3)
                    viewModel.loadInfo()
                    adapter.submitList(repository.getIOS())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.backend->{
                    myShar.setPos(4)
                    viewModel.loadInfo()
                    adapter.submitList(repository.getBackend())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.admin->{
                    myShar.setPos(0)
                    viewModel.loadInfo()
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse("tg://resolve?domain=Madatbek01"))
                    intent.setPackage("org.telegram.messenger")
                    try {
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        // Telegram не установлен, открыть в браузере
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://t.me/Madatbek01")
                            )
                        )
                    }

                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}