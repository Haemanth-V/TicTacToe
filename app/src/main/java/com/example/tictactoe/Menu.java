package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void twoPlayer(View view){
        Intent intent = new Intent(this, TwoPlayerDetails.class);
        startActivity(intent);
        finish();
    }

    public void onePlayer(View view){
        Intent intent = new Intent(this, PlayerDetails.class);
        startActivity(intent);
        finish();
    }
}
