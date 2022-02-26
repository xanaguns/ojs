package com.company.android_java_background_lecture.repository;

import com.company.android_java_background_lecture.Result;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}