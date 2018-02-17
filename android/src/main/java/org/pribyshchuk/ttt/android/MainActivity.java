package org.pribyshchuk.ttt.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_draw;
    Button button_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.pribyshchuk.ttt.android.R.layout.activity_main);

        button_draw = findViewById(R.id.button_draw);
        button_button = findViewById(R.id.button_button);

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_draw:
                        Intent intentDraw = new Intent(v.getContext(), DrawActivity.class);
                        startActivity(intentDraw);
                        break;
                    case R.id.button_button:
                        Intent intentButton = new Intent(v.getContext(), ButtonActivity.class);
                        startActivity(intentButton);
                        break;
                    default:
                        break;
                }
            }
        };

        button_draw.setOnClickListener(onClickListener);
        button_button.setOnClickListener(onClickListener);


    }
}
