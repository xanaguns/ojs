package com.company.android_java_background_lecture.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.android_java_background_lecture.MainViewModel;
import com.company.android_java_background_lecture.R;
import com.company.android_java_background_lecture.Result;
import com.company.android_java_background_lecture.databinding.FragmentExecutorBinding;
import com.company.android_java_background_lecture.repository.RepositoryCallback;

public class ExecutorFragment extends Fragment {
    private static final String TAG = ExecutorFragment.class.getSimpleName();
    private MainViewModel viewModel;
    private FragmentExecutorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_executor, container, false);
        binding = FragmentExecutorBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.longTask(result -> {
                    if (result instanceof Result.Success) {
                        binding.progress.setProgress(((Result.Success<Integer>) result).data);
                    } else {
                        Toast.makeText(getActivity(), ((Result.Error)result).exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}