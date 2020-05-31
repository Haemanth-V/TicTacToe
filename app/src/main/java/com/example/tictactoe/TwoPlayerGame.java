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
import android.widget.TextView;

public class TwoPlayerGame extends AppCompatActivity {
    private Board board;
    private TwoPlayers twoplyrs;
    private static TextView textView;
    private SoundActivity sound;
    private static String  player1Name,player2Name,header1,header2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_game);
        Intent intent = getIntent();
        player1Name = intent.getStringExtra(TwoPlayerDetails.MSGP1);
        player2Name = intent.getStringExtra(TwoPlayerDetails.MSGP2);

        if(player1Name.charAt(player1Name.length()-1)!='s'&&player1Name.charAt(player1Name.length()-1)!='S')
            header1 = player1Name + "'s";
        else
            header1 =player1Name + "'";
        if(player2Name.charAt(player2Name.length()-1)!='s'&&player2Name.charAt(player2Name.length()-1)!='S')
            header2 = player2Name + "'s";
        else
            header2 = player2Name + "'";

        textView = (TextView)findViewById(R.id.textViewTurn);
        turnDisplay(1);
        twoplyrs = new TwoPlayers();
        sound = new SoundActivity(this);
        board = (Board) findViewById(R.id.boardView);
        board.setTwoPlayers(twoplyrs);
        textView = (TextView)findViewById(R.id.textViewTurn);
        board.setTwoPlayerGame(this);
        twoplyrs.newGame(2);
        board.invalidate();
    }

    public void turnDisplay(int plyr){
        if(plyr==1)
            textView.setText(header1+" Turn");
        else
            textView.setText(header2+" Turn");
    }

    public void gameEnded(int win) {
        String s;
        if (win == 1) {
            s = player1Name+" Wins!";
           sound.xWin();
        }
        else if (win == 2) {
            s = player2Name +" Wins!";
            sound.oWin();
        }
        else {
            s = "Draw!";
            sound.draw();
        }
        new AlertDialog.Builder(this).setTitle("TIC TAC TOE").
                setMessage(s).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //Intent to Menu
                Intent intent = new Intent(TwoPlayerGame.this,Menu.class);
                startActivity(intent);
                finish();
            }
        }).show();
    }
}

