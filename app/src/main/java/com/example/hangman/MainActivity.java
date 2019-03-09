package com.example.hangman;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayer(View v){
        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }
}
