package com.company.android_java_background_lecture.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.company.android_java_background_lecture.R;

public class JobService extends JobIntentService {
    private static final String CHANNEL_ID = "Job Channel";

    NotificationManager notificationManager;

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, JobService.class, 1000, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel();
        try {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                num++;
                showNotification(num);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                .setContentTitle("job service")
                .setProgress(100, progress, false)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}
