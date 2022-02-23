package com.jsoh.room_exam;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.jsoh.room_exam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        // UI 갱신
        /*
        viewModel.getAll().observe(this, todos -> {
            binding.resultText.setText(todos.toString());
        });
        //*/

        /*
        // 버튼 클릭시 DB에 insert
        findViewById(R.id.add_button).setOnClickListener(v -> {
            viewModel.insert(binding.todoEdit.getText().toString());
        });
        //*/
    }
}