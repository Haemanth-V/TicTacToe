package com.example.tictactoe;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TwoPlayers extends AppCompatActivity {
    private static final Random rand = new Random();
    int[] board;
    private int cur_plyr;
    private static int numPlyrs;
    private boolean gameOver;
    public TwoPlayers(){
        board = new int[9];
        newGame(0);
    }
    public boolean isGameOver(){
        return gameOver;
    }

    public int positionOnBoard(int x,int y){
        return board[3*x+y];
    }

    public int play(int x,int y){
        if(!gameOver && board[3*x+y]==0){
            board[3*x+y] = cur_plyr;
            if(cur_plyr==1)
                cur_plyr=2;
            else cur_plyr=1;
        }
        return checkGameOver();
    }
    public int computer(){
        if(!gameOver) {
            /*int pos=-1;
            do{
              pos = rand.nextInt(9);
            }while(board[pos]!=0);*/
            int bestScore=-10000,score,bestMove=-1;
            for(int i=0;i<9;i++) {
                if (board[i] == 0) {
                    board[i] = 2;
                    score = minimax( 0, 0);
                    board[i] = 0;
                    if(score>bestScore){
                        bestScore = score;
                        bestMove = i;
                        if(score==1)
                            break;
                    }
                }
            }
            board[bestMove]=2;
            cur_plyr=1;
        }
        return checkGameOver();
    }
    private int minimax( int depth,int isMaximising) {
        int result = checkGameOver();
        if (result == 1)
            return -1;
        else if (result == 2)
            return 1;
        else if (result == 3)
            return 0;
        else {
            if (isMaximising == 1) {

                int score, bestScore = -100000;
                for (int i = 0; i < 9; i++) {
                    if (board[i] == 0) {
                        board[i] = 2;
                        score = minimax(depth + 1, 0);
                        board[i] = 0;
                        bestScore = Math.max(score, bestScore);
                    }
                }

                return bestScore;
            }
            else{
                int score, bestScore = 100000;
                for (int i = 0; i < 9; i++) {
                    if (board[i] == 0) {
                        board[i] = 1;
                        score = minimax(depth + 1, 1);
                        board[i] = 0;
                        bestScore = Math.min(score, bestScore);
                    }
                }

                return bestScore;
            }
        }
    }
    public void newGame(int n){
        cur_plyr=1;
        for(int i=0;i<9;i++)
            board[i]=0;
        gameOver=false;
        numPlyrs = n;
    }
    public int checkGameOver(){

        for(int i=0;i<3;i++){
            if(board[3*i]!=0 && board[3*i]==board[3*i+1] && board[3*i]==board[3*i+2]){
                gameOver = true;
                return board[3*i];
            }
            else if(board[i]!=0 && board[i]==board[i+3] && board[i]==board[i+6]){
                gameOver = true;
                return board[i];
            }
        }
        if(board[0]!=0 && board[0]==board[4] && board[0]==board[8]){
            gameOver=true;
            return board[0];
        }
        else if(board[2]!=0 && board[2]==board[4] && board[2]==board[6]){
            gameOver=true;
            return board[2];
        }
        for(int i=0;i<9;i++){
            if(board[i]==0)
                return 0;
        }
        return 3;
    }
}
