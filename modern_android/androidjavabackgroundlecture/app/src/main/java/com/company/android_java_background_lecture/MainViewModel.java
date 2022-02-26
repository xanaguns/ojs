package com.company.android_java_background_lecture;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.company.android_java_background_lecture.repository.NumberRepository;

public class MainViewModel extends AndroidViewModel {
    private final NumberRepository repository;

    public MutableLiveData<Integer> progressLiveData = new MutableLiveData<>(0);

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new NumberRepository(
                ((App) application).mainThreadHandler,
                ((App) application).executorService
        );
    }

    public void longTask() {
        repository.longTask(result -> {
            if (result instanceof Result.Success) {
                progressLiveData.postValue(((Result.Success<Integer>) result).data);
            } else {
                Toast.makeText(getApplication(), ((Result.Error)result).exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
