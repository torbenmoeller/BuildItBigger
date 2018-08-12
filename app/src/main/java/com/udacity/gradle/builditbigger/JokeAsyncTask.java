package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.jokeactivity.JokeActivity;
import com.udacity.jokeactivity.Keys;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Object, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

//    private static final String rootUrl = "http://localhost:8080/_ah/api/";
    private static final String rootUrl = "http://10.0.2.2:8080/_ah/api/";

    public JokeAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(rootUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(Keys.JOKE_INTENT_EXTRA, joke);
        context.startActivity(intent);
    }

}