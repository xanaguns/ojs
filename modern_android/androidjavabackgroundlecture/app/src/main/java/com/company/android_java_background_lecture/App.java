package com.company.android_java_background_lecture;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App extends Application {
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    public ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CORES);
    public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
}
