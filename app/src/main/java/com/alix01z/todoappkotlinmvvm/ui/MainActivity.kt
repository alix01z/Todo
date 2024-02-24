package com.alix01z.todoappkotlinmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBinderMapper
import androidx.databinding.DataBindingUtil
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
    }
}