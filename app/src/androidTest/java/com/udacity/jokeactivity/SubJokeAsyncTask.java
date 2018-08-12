package com.udacity.jokeactivity;

import android.content.Context;
import android.content.Intent;

import com.udacity.gradle.builditbigger.JokeAsyncTask;

import java.util.concurrent.CountDownLatch;

public class SubJokeAsyncTask extends JokeAsyncTask {

    CountDownLatch signal;

    public SubJokeAsyncTask(Context context, CountDownLatch signal) {
        super(context);
        this.signal = signal;
    }

    @Override
    protected void onPostExecute(String joke) {
        signal.countDown();
    }

}
