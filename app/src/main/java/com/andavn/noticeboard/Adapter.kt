package com.andavn.noticeboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andavn.noticeboard.databinding.NoticeBoardItemBinding

class Adapter(private val model: MutableList<NoticeModel>) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        return CustomViewHolder(
            NoticeBoardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.binding.title.text = model[position].title
        holder.binding.department.text = model[position].department
        holder.binding.description.text = model[position].noticeDescription
        holder.binding.signature.text = model[position].profSignature

    }

    override fun getItemCount(): Int {

        return model.size
    }

}

class CustomViewHolder(val binding: NoticeBoardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}
