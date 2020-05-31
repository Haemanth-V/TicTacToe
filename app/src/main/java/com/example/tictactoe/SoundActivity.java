package com.example.tictactoe;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SoundActivity {
    private static SoundPool soundPool;
    private static int soundWinX, soundWinO, soundDraw, soundTouch,soundLost;
    public SoundActivity(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        soundWinX = soundPool.load(context,R.raw.win_x,1);
        soundWinO = soundPool.load(context,R.raw.win_o,1);
        soundDraw = soundPool.load(context,R.raw.draw,1);
        soundTouch = soundPool.load(context,R.raw.touch,2);
    }
    public void xWin(){
        soundPool.play(soundWinX,1,1,0,0,1);
    }
    public void oWin(){
        soundPool.play(soundWinO,1,1,0,0,1);
    }
    public void draw(){
        soundPool.play(soundDraw,1,1,0,0,1);
    }
    public void touch(){
        soundPool.play(soundTouch,1,1,1,0,1);
    }
  //  public void lost(){ soundLost=1; }
}
