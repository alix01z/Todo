package com.alix01z.todoappkotlinmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.databinding.ItemTaskBinding
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity

class UpcomingRvAdapter(private var taskList: List<TaskEntity>) : RecyclerView.Adapter<UpcomingRvAdapter.UpcomingRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRvViewHolder {
        val binding:ItemTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context) ,
            R.layout.item_task , parent , false)
        return UpcomingRvViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UpcomingRvViewHolder, position: Int) {
        holder.bindData(taskList[position])
    }
    override fun getItemCount(): Int {
        return taskList.size
    }

    class UpcomingRvViewHolder(private var binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(taskEntity: TaskEntity){
            binding.txItemTitle.setTextFuture(
                taskEntity.title.let {
                    PrecomputedTextCompat.getTextFuture(
                        it,
                        binding.txItemTitle.textMetricsParamsCompat,
                        null
                    )
                }
            )
            binding.txItemComment.setTextFuture(
                taskEntity.title.let {
                    PrecomputedTextCompat.getTextFuture(
                        it,
                        binding.txItemComment.textMetricsParamsCompat,
                        null
                    )
                }
            )
            binding.executePendingBindings()
        }
    }
}