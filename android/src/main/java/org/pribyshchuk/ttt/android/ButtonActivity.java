package org.pribyshchuk.ttt.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ButtonActivity extends AppCompatActivity {

    private static final String TAG = "myLog";

    private int getWinner(){
        for (i = 0; i < 3; i++){
            if (map[i][0] == map[i][1] && map[i][1] == map[i][2]){
                return map[i][0];
            }
        }
        for (j = 0; j < 3; j++){
            if (map[0][j] == map[1][j] && map[1][j] == map[2][j]){
                return map[0][j];
            }
        }
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]){
            return map[0][0];
        }else if (map[0][2] == map[1][1] && map[1][1] == map[2][0]){
            return map[2][0];
        }else {
            for (i = 0; i < 3; i++){
                for(j = 0; j < 3; j++){
                    if (map [i][j] == 0) {
                    return 0;
                }
                }
            }
        }
        return -1;
    }



    int [] arrayButton = {R.id.button00, R.id.button01, R.id.button02,
                          R.id.button10, R.id.button11, R.id.button12,
                          R.id.button20, R.id.button21, R.id.button22};

    Button[] quantityButton = new Button[arrayButton.length];

    String playerX = "X";
    String playerO = "O";
    int nextPlayer = 1;
    int i;
    int j;
    int[][]map = new int[3][3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);


        for (i = 0; i < arrayButton.length; i++) {
            quantityButton[i] = findViewById(arrayButton[i]);
            quantityButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = 0;
                    Button button = (Button) v;
                    for (i = 0; i < quantityButton.length; i++) {
                        if (button == quantityButton[i]) {
                            index = i;
                            break;
                        }
                    }
                    Log.d(TAG, "index=" + index + " text=" + button.getText());

                    int j = index % 3;
                    int i = index / 3;

                    Log.d(TAG, "i=" + i + " j=" + j);

                    if (map[i][j] == 0) {
                        map[i][j] = nextPlayer;
                        if (nextPlayer == 1) {
                            nextPlayer = 2;
                        } else if (nextPlayer == 2) {
                            nextPlayer = 1;
                        }
                        button.setText(nextPlayer == 1 ? playerO : playerX);
                    }

                    int winner = getWinner();
                    if (winner != 0){
                        if (winner == -1){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "No winner!", Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Winner " + winner, Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                }


            });

        }





    }

}