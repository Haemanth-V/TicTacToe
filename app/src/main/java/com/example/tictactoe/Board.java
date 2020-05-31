package com.example.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Board extends View {
    private static final int LINE_THICK = 5;
    private static final int BOARD_MARGIN = 20;
    private static final int STROKE_WIDTH = 15;
    private int width,height,elementWidth,elementHeight;
    private Paint gridPaint,oPaint,xPaint;
    private SoundActivity sound;
    private TwoPlayers twoPlayers;
    private TwoPlayerGame activityTwoPlayer;
    private OnePlayerGame activityOnePlayer;
    private int currentPlyr = 1;
    private static int numPlyrs;

    public Board(Context context) {
        super(context);
        sound = new SoundActivity(context);

    }

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.RED);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(STROKE_WIDTH);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);
        sound = new SoundActivity(context);

    }

    public void setTwoPlayerGame(TwoPlayerGame act){
        activityTwoPlayer = act;
    }

    public void setOnePlayerGame(OnePlayerGame act){
        activityOnePlayer = act;
        numPlyrs = 1;
    }

    public void setTwoPlayers(TwoPlayers twoPlyrs){
        twoPlayers = twoPlyrs;
        numPlyrs = 2;
    }

    // For Dimensions of the board
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        elementHeight = (height-LINE_THICK)/3;
        elementWidth = (width-LINE_THICK)/3;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!twoPlayers.isGameOver() && event.getAction()==MotionEvent.ACTION_DOWN){
            int x =(int)(event.getX()/elementWidth);
            int y = (int)(event.getY()/elementHeight);
            sound.touch();
            if(currentPlyr==1)
                currentPlyr=2;
            else currentPlyr=1;
            if(numPlyrs==2)
              activityTwoPlayer.turnDisplay(currentPlyr);
            int win = twoPlayers.play(x,y);
            invalidate();
            if(win!=0){
                if(numPlyrs==2)
                    activityTwoPlayer.gameEnded(win);
                else activityOnePlayer.gameEnded(win);
            }
            else if(numPlyrs == 1){
                //Computer's turn
                win = twoPlayers.computer();
                invalidate();
                if(win != 0){
                   activityOnePlayer.gameEnded(win);
                }
                currentPlyr=1;
            }

        }
        return super.onTouchEvent(event);
    }
    private void drawBoard(Canvas canvas){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                drawCell(canvas,twoPlayers.positionOnBoard(i,j),i,j);
            }
        }
    }
    private void drawGrid(Canvas canvas){
        for(int i=0;i<2;i++){
            float left = elementWidth * (i+1);
            float right = left + LINE_THICK;
            float top = 0;
            float bottom = height;
            canvas.drawRect(left,top,right,bottom,gridPaint);

            left = 0;
            right = width;
            top = elementHeight*(i+1);
            bottom = top + LINE_THICK;
            canvas.drawRect(left,top,right,bottom,gridPaint);
        }
    }
    private void drawCell(Canvas canvas, int plyr, int x,int y){
        if(plyr==2){
            float cX = elementWidth*x+elementWidth/2;
            float cY = elementHeight*y+elementHeight/2;
            canvas.drawCircle(cX,cY,Math.min(elementWidth,elementHeight)/2-BOARD_MARGIN*2,oPaint);
        }
        else if(plyr==1){
            float startX = elementWidth*x+BOARD_MARGIN*2;
            float startY = elementHeight*y+BOARD_MARGIN*2;
            float endX = startX + elementWidth-BOARD_MARGIN*4;
            float endY = startY + elementHeight-BOARD_MARGIN*4;
            canvas.drawLine(startX,startY,endX,endY,xPaint);

            startX = elementWidth*(x+1)-BOARD_MARGIN*2;
            startY = elementHeight*y+BOARD_MARGIN*2;
            endX = startX - elementWidth+BOARD_MARGIN*4;
            endY = startY + elementHeight-BOARD_MARGIN*4;
            canvas.drawLine(startX,startY,endX,endY,xPaint);
        }

    }
}
