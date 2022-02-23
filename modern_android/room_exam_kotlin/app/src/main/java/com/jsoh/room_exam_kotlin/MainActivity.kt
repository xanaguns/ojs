package com.jsoh.room_exam_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jsoh.room_exam_kotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // LiveData 때문에 추가되는 코드임
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        /*
        viewModel.getAll().observe(this, Observer {
            result_text.text = it.toString()
        })
        */

        /*
        add_button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(todo_edit.text.toString())
            }
        }
        */
    }
}