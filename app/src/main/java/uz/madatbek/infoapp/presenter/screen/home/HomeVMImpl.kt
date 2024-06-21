package uz.madatbek.infoapp.presenter.screen.home

import androidx.lifecycle.ViewModel
import uz.madatbek.infoapp.data.model.NodeData
import uz.madatbek.infoapp.data.sourse.MyShar
import uz.madatbek.infoapp.domain.AppRepository
import uz.madatbek.infoapp.domain.AppRepositoryImpl

class HomeVMImpl:HomeVM,ViewModel() {
    private val repository:AppRepository=AppRepositoryImpl
    override var loadListener: ((List<NodeData>) -> Unit)?=null
    private val myShar=MyShar

    override fun loadInfo() {
        when(myShar.getPos()){
            0->  loadListener?.invoke(repository.getAllInfo())
            1->  loadListener?.invoke(repository.getAndroid())
            2->  loadListener?.invoke(repository.getWep())
            3->  loadListener?.invoke(repository.getIOS())
            4->  loadListener?.invoke(repository.getBackend())
        }
    }
}