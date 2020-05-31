package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    private int counter = 0;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // progress();
        //Splash Screen and then call menu
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
        /*
       Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();*/
    }

   public void progress(){
        pb = (ProgressBar)findViewById(R.id.progressBar);
        final Timer t = new Timer();
       TimerTask tt = new TimerTask() {
           @Override
           public void run() {
               counter++;
               pb.setProgress(counter);
               if(counter==100)
                   t.cancel();
           }
       };
       t.schedule(tt,0,100);
   }
}


