package org.pribyshchuk.ttt.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.ACTION_UP;

public class DrawActivity extends AppCompatActivity {

    private static final String TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint paint;
        float[] pointsGrid;
        int width;
        int height;


        public DrawView(Context context) {
            super(context);
            DisplayMetrics display = this.getResources().getDisplayMetrics();
            width = display.widthPixels;
            height = display.widthPixels;
            paint = new Paint();
            pointsGrid = new float[]{0, height / 3, width, height / 3,
                                     0, height * 2 / 3, width, height * 2 / 3,
                                     width / 3, 0, width / 3, height,
                                     width * 2 / 3, 0, width * 2 /3, height};

        }

        int nextPlayer = 1;
        int[][] map = new int[3][3];

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            float xClick = event.getX();
            float yClick = event.getY();

            switch (event.getAction()){
                case ACTION_UP: {
                    DrawView.this.performClick();

                    DrawView.this.invalidate();
                    int i = 0;
                    if (xClick < width / 3) {
                        i = 0;
                    } else if (xClick > width / 3 && xClick < width * 2 / 3) {
                        i = 1;
                    } else if (xClick > width * 2 / 3) {
                        i = 2;
                    }
                    int j = 0;
                    if (yClick < height / 3) {
                        j = 0;
                    } else if (yClick > height / 3 && yClick < height * 2 / 3) {
                        j = 1;
                    } else if (yClick > height * 2 / 3) {
                        j = 2;
                    }



                    if (map[i][j] == 0) {
                        map[i][j] = nextPlayer;
                        if (nextPlayer == 1) {
                            nextPlayer = 2;
                        } else if (nextPlayer == 2) {
                            nextPlayer = 1;
                        }
                        Log.d(TAG, "before map in method onTouchEvent");
                    }
                    Log.d(TAG, "i="+i+" j="+j+" nextPlayer="+nextPlayer);
                }
                    break;
            }

            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawARGB(180, 170, 150, 130);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(10);
            canvas.drawLines(pointsGrid, paint);

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    switch (map [i][j]){
                        case 1: {
                            paint.setColor(Color.RED);
                            paint.setStrokeWidth(15);
                            paint.setStyle(Paint.Style.STROKE);
                            int xc = i * width / 3 + width / 6;
                            int yc = j * height / 3 + height / 6;
                            canvas.drawCircle(xc, yc, 100, paint);
                            Log.d(TAG, "draw O");
                        }
                        break;
                        case 2: {
                            paint.setColor(Color.BLACK);
                            paint.setStrokeWidth(15);
                            int xStart = i * width / 3;
                            int yStart = j * height / 3;
                            int xStop = xStart + width / 3;
                            int yStop = yStart + height / 3;
                            canvas.drawLine(xStart, yStart, xStop, yStop, paint);
                            canvas.drawLine(xStop, yStart, xStop - width / 3, yStop, paint);
                            Log.d(TAG, String.format("(%d, %d) - (%d, %d)", xStart, yStart, xStop, yStop));
                        }
                        break;
                    }
                }
            }

        }

    }
}
