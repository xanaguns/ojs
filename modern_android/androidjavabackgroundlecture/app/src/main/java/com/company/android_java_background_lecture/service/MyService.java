package com.company.android_java_background_lecture.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;

import com.company.android_java_background_lecture.App;
import com.company.android_java_background_lecture.R;
import com.company.android_java_background_lecture.Result;
import com.company.android_java_background_lecture.repository.NumberRepository;

public class MyService extends Service {
    private static final String CHANNEL_ID = "Job Channel";

    NotificationManager notificationManager;

    private NumberRepository repository;

    public MutableLiveData<Integer> progressLiveData = new MutableLiveData<>(0);

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        repository = new NumberRepository(
                ((App) getApplication()).mainThreadHandler,
                ((App) getApplication()).executorService
        );

        createNotificationChannel();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Foreground Service")
                .setProgress(100, 0, false)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        startForeground(2000, notification);

        repository.longTask(result -> {
            if (result instanceof Result.Success) {
                if (((Result.Success<Integer>) result).isFinished) {
                    stopForeground(true);
                    stopSelf();
                }
                progressLiveData.postValue(((Result.Success<Integer>) result).data);
                showNotification(((Result.Success<Integer>) result).data);
            } else {
                Toast.makeText(getApplication(), ((Result.Error)result).exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "job channel";
            String description = "job channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(int progress) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Foreground Service")
                .setProgress(100, progress, false)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2000, notification);
    }
}