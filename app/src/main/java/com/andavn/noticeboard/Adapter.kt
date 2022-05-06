package com.andavn.noticeboard
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andavn.noticeboard.databinding.NoticeBoardItemBinding

class Adapter:RecyclerView.Adapter<CustomViewHolder>() {

    lateinit var list:MutableList<NoticeModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        return CustomViewHolder(NoticeBoardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.binding.title.text = list[position].title
        holder.binding.department.text = list[position].department
        holder.binding.description.text = list[position].noticeDescription
        holder.binding.signature.text = list[position].profSignature

    }

    override fun getItemCount() = list.size

    fun setData(nList:MutableList<NoticeModel>){

        this.list = nList

        notifyDataSetChanged()

    }
}

class CustomViewHolder(val binding: NoticeBoardItemBinding):RecyclerView.ViewHolder(binding.root) {

}
