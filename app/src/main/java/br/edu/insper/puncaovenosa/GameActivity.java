package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.game_activity);
        assert myLayout != null;

        int index = getIntent().getIntExtra("i", -1);

        if(index == -1) {
            System.out.println("Boop");
        } else if(index > 9) {
            System.out.println("Beep");
        } else if(index == 5) {
            myLayout.setBackgroundResource(R.drawable.room2);
        } else if(index == 7) {
            myLayout.setBackgroundResource(R.drawable.room4);
        } else if(index == 8) {
            myLayout.setBackgroundResource(R.drawable.room5);
        } else if(index == 9) {
            myLayout.setBackgroundResource(R.drawable.room3);
        } else if(index == 0) {
            myLayout.setBackgroundResource(R.drawable.room7);
        } else if(index == 1) {
            myLayout.setBackgroundResource(R.drawable.room6);
        } else if(index == 2) {
            myLayout.setBackgroundResource(R.drawable.room9);
        } else if(index == 3) {
            myLayout.setBackgroundResource(R.drawable.room10);
        } else if(index == 4) {
            myLayout.setBackgroundResource(R.drawable.room8);
        }

        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                startActivity(new Intent(GameActivity.this, GarroteActivity.class));
                return true;
            }
        });

    }
}
