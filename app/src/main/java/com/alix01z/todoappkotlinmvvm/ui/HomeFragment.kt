package com.alix01z.todoappkotlinmvvm.ui

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.adapters.CardTaskClickListener
import com.alix01z.todoappkotlinmvvm.adapters.UpcomingRvAdapter
import com.alix01z.todoappkotlinmvvm.databinding.FragmentHomeBinding
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import com.alix01z.todoappkotlinmvvm.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() , CardTaskClickListener {

    private lateinit var binding : FragmentHomeBinding
    private val appViewModel: AppViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_home , container , false)
        binding.fragmentHome = this
        binding.viewModel = appViewModel
        initUpcomingRecyclerview()

        return binding.root
    }
    private fun initUpcomingRecyclerview(){
        appViewModel.dbLiveData.observe(viewLifecycleOwner){taskList->
            val data : ArrayList<TaskEntity> = ArrayList()
            taskList.forEach {
                data.add(it)
            }
            //Check List is Empty or not
            if (data.isEmpty()){
                binding.txEmptyTasklist.visibility = View.VISIBLE
                binding.lottieEmptyTasklist.visibility = View.VISIBLE
            }
            else{
                binding.txEmptyTasklist.visibility = View.GONE
                binding.lottieEmptyTasklist.visibility = View.GONE
            }

            binding.rvAllTasks.adapter = UpcomingRvAdapter(data , this)
            binding.rvAllTasks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onItemClickListener(taskEntity: TaskEntity) {
        val bundleSendData = bundleOf("selected_task" to taskEntity)
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_newTodoFragment  , bundleSendData)
    }

    override fun onItemCheckBoxListener(view: CheckBox, txTitle: TextView, txComment: TextView, taskEntity: TaskEntity) {
        if (view.isChecked){
            txTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            txComment.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            txTitle.paintFlags = txTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            txComment.paintFlags = txComment.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onOptionClickListener(view: View, taskEntity: TaskEntity) {
        val popupMenu = PopupMenu(requireActivity() , view)
        popupMenu.inflate(R.menu.menu_item_task)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_delete_item -> {
                    appViewModel.deleteTaskFromDB(taskEntity)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}