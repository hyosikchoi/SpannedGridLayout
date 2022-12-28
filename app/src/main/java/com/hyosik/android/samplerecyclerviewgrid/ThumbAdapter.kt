package com.hyosik.android.samplerecyclerviewgrid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hyosik.android.samplerecyclerviewgrid.databinding.ItemImageBinding


class ThumbAdapter : ListAdapter<Thumb,ThumbAdapter.ViewHolder>(diffUtil) {

    private var deviceWidth : Int = 0
    private var deviceHeight : Int = 0

    fun setWidthHeight(deviceWidth : Int , deviceHeight :Int) {
        this.deviceWidth = deviceWidth
        this.deviceHeight = deviceHeight
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /** getItem(position) 을 톨해 특정 값이 있다면 특정 값에 대한 layout 영역 설정 가능 */
        if(position == 0) {
            val layoutParams = holder.itemView.layoutParams
            layoutParams.height = deviceWidth * 2
            layoutParams.width = deviceWidth * 2
            holder.itemView.requestLayout()
        } else {
            val layoutParams = holder.itemView.layoutParams
            layoutParams.height = deviceWidth
            layoutParams.width = deviceWidth
            holder.itemView.requestLayout()
        }
        holder.bind(getItem(position),position)
    }

    inner class ViewHolder(private val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(thumb: Thumb , position: Int) {
            binding.ivThumb.load(thumb.thumbUrl)

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "position : $position", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {

        val diffUtil : DiffUtil.ItemCallback<Thumb> = object : DiffUtil.ItemCallback<Thumb>() {
            override fun areItemsTheSame(oldItem: Thumb, newItem: Thumb): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Thumb, newItem: Thumb): Boolean {
                return oldItem == newItem
            }
        }
    }

}