package com.company.android_java_background_lecture.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.android_java_background_lecture.R;
import com.company.android_java_background_lecture.databinding.FragmentForegroundServiceBinding;
import com.company.android_java_background_lecture.service.MyService;


public class ForegroundServiceFragment extends Fragment {
    private FragmentForegroundServiceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foreground_service, container, false);
        binding = FragmentForegroundServiceBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFgserviceStart.setOnClickListener(v -> {
            ContextCompat.startForegroundService(requireContext(), new Intent(requireContext(), MyService.class));
        });
    }
}