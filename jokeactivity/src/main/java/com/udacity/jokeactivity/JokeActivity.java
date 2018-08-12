package com.udacity.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.udacity.jokeactivity.Keys.JOKE_INTENT_EXTRA;

public class JokeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextview =findViewById(R.id.joke_textview);

        Intent intent = getIntent();

        String jokeExtra = intent.hasExtra(JOKE_INTENT_EXTRA) ? intent.getStringExtra(JOKE_INTENT_EXTRA) : null;
        if(jokeExtra != null){
            jokeTextview.setText(jokeExtra);
        }
    }
}
