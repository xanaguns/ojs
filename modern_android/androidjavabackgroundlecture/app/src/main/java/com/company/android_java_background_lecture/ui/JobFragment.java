package com.company.android_java_background_lecture.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.android_java_background_lecture.R;
import com.company.android_java_background_lecture.databinding.FragmentJobBinding;
import com.company.android_java_background_lecture.service.JobService;

public class JobFragment extends Fragment {
    private FragmentJobBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job, container, false);
        binding = FragmentJobBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonJob.setOnClickListener(v -> JobService.enqueueWork(requireContext(), new Intent()));
    }
}