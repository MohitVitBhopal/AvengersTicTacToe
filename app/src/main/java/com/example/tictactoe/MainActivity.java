package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int active = 0;

    boolean activeGame = true;

    public void click (View view ) {

        ImageView iv = (ImageView) view;

        int tagPosition = Integer.parseInt(iv.getTag().toString());

        if (position[tagPosition] == 2 && activeGame){

             position[tagPosition] = active;

            iv.animate().alpha(1).setDuration(400);

             if (active == 0){
            iv.setImageResource(R.drawable.captainamerica);
            active = 1;
         }else {
            iv.setImageResource(R.drawable.ironman);
            active = 0;
        }



        for (int[] winningPosition: winningPositions) {

            if (position[winningPosition[0]] == position[winningPosition[1]]
                    && position[winningPosition[1]] == position[winningPosition[2]]
                    && position[winningPosition[0]] != 2) {

                activeGame = false;
                String winner = "";

                if (active == 1) {
                    winner = "Winner is Captain America";
                    gameFinish(winner);
                } else if (active == 0) {
                    winner = "Winner is Iron Man";
                    gameFinish(winner);
                }
            } else if (isTied()) {
                String winner = "Draw match";
                    gameFinish(winner);
            }

          }

        }

    }

    public void PlayAgain (View view){
        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        tvResult.setVisibility(View.INVISIBLE);

        btnPlayAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

         for (int i=0; i<gridLayout.getChildCount(); i++){

             ImageView imageView = (ImageView) gridLayout.getChildAt(i);

             imageView.animate().alpha(0).setDuration(500);
             imageView.setImageDrawable(null);

         }

         for (int i=0; i<position.length ; i++){
             position[i] = 2;
         }

         active = 0;

        activeGame = true;
    }

    public void gameFinish(String winner){

        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        tvResult.setVisibility(View.VISIBLE);

        btnPlayAgain.setVisibility(View.VISIBLE);

        tvResult.setText( winner);
    }

    public boolean isTied(){
        for (int i=0; i<position.length; i++){

            if (position[i] == 2){
                return false;
            }

        }
        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}