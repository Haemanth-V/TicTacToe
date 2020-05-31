package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwoPlayerDetails extends AppCompatActivity {
    public static final String MSGP1 = "com.example.tictactoe.PLAYER1";
    public static final String MSGP2 = "com.example.tictactoe.PLAYER2";
    private String player1, player2;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_details);
    }

    public void proceedTwoPlayer(View view) {
        int p1=1,p2=1,equal=0;
        editText=(EditText)findViewById(R.id.editTextPlayer1);
        player1 = editText.getText().toString().trim();
        if (TextUtils.isEmpty(player1)) {
            p1=0;
            String s = "Enter a name for Player 1!";
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
        editText=(EditText)findViewById(R.id.editTextPlayer2);
        player2 = editText.getText().toString().trim();
        if (TextUtils.isEmpty(player2)) {
            p2=0;
            String s = "Enter a name for Player 2!";
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
        if(player1.equals(player2)){
            equal=1;
            String s = "Enter different names for the 2 Players";
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }

        if(p1==1 && p2==1 &&  equal==0){
            Intent intent = new Intent(TwoPlayerDetails.this, TwoPlayerGame.class);
            intent.putExtra(MSGP1, player1);
            intent.putExtra(MSGP2,player2);
            startActivity(intent);
            finish();
        }
    }
}
