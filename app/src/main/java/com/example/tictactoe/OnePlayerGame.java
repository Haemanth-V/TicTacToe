package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OnePlayerGame extends AppCompatActivity {
    private Board board;
    private TwoPlayers twoplyrs;
    private static TextView textView;
    private SoundActivity sound;
    private static String  playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player_game);
        Intent intent = getIntent();
        playerName = intent.getStringExtra(PlayerDetails.MSGP);

        twoplyrs = new TwoPlayers();
        sound = new SoundActivity(this);
        board = (Board) findViewById(R.id.boardView1);
        board.setTwoPlayers(twoplyrs);
        board.setOnePlayerGame(this);
        twoplyrs.newGame(1);
        board.invalidate();
    }


    public void gameEnded(int win) {
        String s;
        if (win == 1) {
            s = playerName+" Wins!";
            sound.xWin();
        }
        else if (win == 2) {
            s = playerName +" Lost!";
            //sound.lost();
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
                Intent intent = new Intent(OnePlayerGame.this,Menu.class);
                startActivity(intent);
                finish();
            }
        }).show();
    }
}
