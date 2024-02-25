package com.alix01z.todoappkotlinmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.adapters.UpcomingRvAdapter
import com.alix01z.todoappkotlinmvvm.databinding.FragmentHomeBinding
import com.alix01z.todoappkotlinmvvm.room.AppDatabase
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import com.alix01z.todoappkotlinmvvm.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

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

            binding.rvAllTasks.adapter = UpcomingRvAdapter(data)
            binding.rvAllTasks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}