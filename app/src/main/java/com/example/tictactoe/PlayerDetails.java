package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerDetails extends AppCompatActivity {
    public static final String MSGP = "com.example.tictactoe.PLAYER_NAME";
    private String player;
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
    }

    public void proceedOnePlayer(View view) {
        int p = 1;
        editText = (EditText) findViewById(R.id.editText1Player);
        player = editText.getText().toString().trim();
        if (TextUtils.isEmpty(player)) {
            p = 0;
            String s = "Enter the Player's Name";
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
        if (p == 1) {
            Intent intent = new Intent(PlayerDetails.this, OnePlayerGame.class);
            intent.putExtra(MSGP, player);
            startActivity(intent);
            finish();
        }
    }

}
