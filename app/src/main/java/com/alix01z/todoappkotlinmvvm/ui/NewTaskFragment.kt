package com.alix01z.todoappkotlinmvvm.ui

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.databinding.FragmentNewTaskBinding
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import com.alix01z.todoappkotlinmvvm.viewModel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewTaskFragment : Fragment() {
    private val appViewModel: AppViewModel by viewModels()
    private lateinit var binding : FragmentNewTaskBinding

    private lateinit var taskEntity: TaskEntity
    var isUpdating = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_new_task , container , false)
        binding.fragmentNewTask = this
        getDataFromBundle()
        return binding.root
    }


    private fun getDataFromBundle() {
        if (arguments != null){
            taskEntity = arguments?.parcelable("selected_task")!!
//            noteEntity = arguments?.getParcelable("selected_note")!!
            isUpdating = true
            binding.edtxTitle.setText(taskEntity.title)
            binding.edtxComment.setText(taskEntity.comment)

        }
    }
    //This fun is made because .getParcelable is deprecated
    inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }


    fun fabOnClickAddNote(view: View){
        if (isUpdating){
            if (!checkTitleIsEmpty()){
                taskEntity.title = binding.edtxTitle.text.toString()
                taskEntity.comment = binding.edtxComment.text.toString()

                appViewModel.updateTask(taskEntity)
                view.findNavController().navigate(R.id.action_newTodoFragment_to_homeFragment)
            }

        }
        else{
            if (!checkTitleIsEmpty()){
                val title = binding.edtxTitle.text.toString()
                val comment = binding.edtxComment.text.toString()
                val taskEntity = TaskEntity(0 , title , comment , "High")

                lifecycleScope.launch(Dispatchers.IO) {
                    appViewModel.insertTaskToDB(taskEntity)
                }
                view.findNavController().navigate(R.id.action_newTodoFragment_to_homeFragment)
            }
        }

    }
    fun checkTitleIsEmpty():Boolean{
        if (binding.edtxTitle.text.isNullOrBlank()){
            Snackbar.make(binding.consLayMain , "Please enter task title!" , Snackbar.LENGTH_SHORT).show()
            return true
        }
        return false
    }

}