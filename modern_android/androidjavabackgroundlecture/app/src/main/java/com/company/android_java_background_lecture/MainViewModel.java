package com.company.android_java_background_lecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.company.android_java_background_lecture.repository.NumberRepository;
import com.company.android_java_background_lecture.repository.RepositoryCallback;

public class MainViewModel extends AndroidViewModel {
    private final NumberRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new NumberRepository(
                ((App) application).mainThreadHandler,
                ((App) application).executorService
        );
    }

    public void longTask(RepositoryCallback<Integer> callback) {
        repository.longTask(callback);
    }
}
