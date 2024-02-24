package com.alix01z.todoappkotlinmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.room.Room
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.databinding.FragmentNewTaskBinding
import com.alix01z.todoappkotlinmvvm.room.AppDatabase
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewTaskFragment : Fragment() {
    @Inject lateinit var taskDB : AppDatabase
    private lateinit var binding : FragmentNewTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_new_task , container , false)
        binding.fragmentNewTask = this
        return binding.root
    }

    fun fabOnClickAddNote(view: View){
        val title = binding.edtxTitle.text.toString()
        val comment = binding.edtxComment.text.toString()
        val taskEntity = TaskEntity(0 , title , comment , "High")
        lifecycleScope.launch(Dispatchers.IO) {
            taskDB.roomDao().insertTask(taskEntity)
        }
        view.findNavController().navigate(R.id.action_newTodoFragment_to_homeFragment)
    }

}