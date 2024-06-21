package uz.madatbek.infoapp.presenter.screen.home

import uz.madatbek.infoapp.data.model.NodeData

interface HomeVM {
    var loadListener:((List<NodeData>)->Unit)?
    fun loadInfo()
}