package uz.madatbek.infoapp.presenter.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uzummarketclient.utils.myLog
import uz.madatbek.infoapp.R
import uz.madatbek.infoapp.data.model.NodeData
import uz.madatbek.infoapp.databinding.ItemInfoBinding

class SearchAdapter: ListAdapter<NodeData, SearchAdapter.Holder>(SearchAdapter.MyDiffUtils) {
    var onClickItem:((NodeData)->Unit)?=null
    private var txt:String=""
    object MyDiffUtils: DiffUtil.ItemCallback<NodeData>() {
        override fun areItemsTheSame(oldItem: NodeData, newItem: NodeData): Boolean=
            oldItem.name==newItem.name

        override fun areContentsTheSame(oldItem: NodeData, newItem: NodeData): Boolean =
         false


    }

    inner class Holder(private val binding: ItemInfoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(){
            binding.apply {
                val index=getItem(adapterPosition).name.trim().lowercase().indexOf(txt.trim().lowercase())
                val text = SpannableString(getItem(adapterPosition).name)
                "${getItem(adapterPosition).name}".myLog()
                "$index index".myLog()
                    text.setSpan(
                        ForegroundColorSpan(Color.RED),
                        index,
                        txt.length+index,
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                    )

                image.setBackgroundResource(getItem(adapterPosition).imageIdRes)
                name.text=text
                con.backgroundTintList= ColorStateList.valueOf(getItem(adapterPosition).dominantColor)
                val data=ArrayList<ImageView>()
                logoContainer.removeAllViews()
                getItem(adapterPosition).type.forEach {
                    when(it){
                        1->{
                            val view=ImageView(binding.root.context)
                            view.setHeWi()
                            view.setBackgroundResource(R.drawable.android_logo)
                            view.backgroundTintList= ColorStateList.valueOf(Color.WHITE)
                            data.add(view)
                        }
                        2->{
                            val view=ImageView(binding.root.context)
                            view.setHeWi()
                            view.setBackgroundResource(R.drawable.wep)
                            view.backgroundTintList= ColorStateList.valueOf(Color.WHITE)
                            data.add(view)
                        }
                        3->{
                            val view=ImageView(binding.root.context)
                            view.setHeWi()
                            view.setBackgroundResource(R.drawable.ios)
                            view.backgroundTintList= ColorStateList.valueOf(Color.WHITE)
                            data.add(view)
                        }
                        4->{
                            val view=ImageView(binding.root.context)
                            view.setHeWi()
                            view.setBackgroundResource(R.drawable.database)
                            view.backgroundTintList= ColorStateList.valueOf(Color.WHITE)
                            data.add(view)
                        }
                    }
                }

                data.forEach {
                    logoContainer.addView(it)
                }
                root.setOnClickListener {
                    onClickItem?.invoke(getItem(adapterPosition))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder=
        Holder(ItemInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }
    fun setText(txt:String){
        this.txt=txt
    }

}