package com.udacity.jokeactivity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class JokeAsyncTaskTest {

    private Context context;
    private String result;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        final AsyncTask<Object, Void, String> asyncTask = new SubJokeAsyncTask(context, signal);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                try {
                    result = asyncTask.execute(context).get();
                } catch (InterruptedException | ExecutionException e ) {
                    e.printStackTrace();
                }
            }
        });
        signal.await(10, TimeUnit.SECONDS);
        assertTrue(!result.isEmpty());
    }

}