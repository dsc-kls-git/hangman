package com.example.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points = getIntent().getIntExtra("POINTS_IDENTIFIER", 0);

        TextView textViewPoints = (TextView) findViewById(R.id.textView4);
        textViewPoints.setText(String.valueOf(points));
    }
}
