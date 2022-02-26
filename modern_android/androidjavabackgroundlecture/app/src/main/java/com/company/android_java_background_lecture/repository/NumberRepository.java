package com.company.android_java_background_lecture.repository;

import android.os.Handler;

import com.company.android_java_background_lecture.Result;

import java.util.concurrent.Executor;

public class NumberRepository {
    private final Handler resultHandler;
    private final Executor executor;

    public NumberRepository(Handler resultHandler, Executor executor) {
        this.resultHandler = resultHandler;
        this.executor = executor;
    }

    public void longTask(RepositoryCallback<Integer> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // background
                    int num = 0;
                    for (int i = 0; i < 100; i++) {
                        num++;
                        // UI 갱신을 위해서 콜백
                        Result<Integer> result = new Result.Success<>(num);
                        notifyResult(result, callback);
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    Result<Integer> result = new Result.Error<>(e);
                    notifyResult(result, callback);
                }
            }
        });
    }

    private void notifyResult(
            final Result<Integer> result,
            final RepositoryCallback<Integer> callback
    ) {
        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onComplete(result);
            }
        });
    }
}
