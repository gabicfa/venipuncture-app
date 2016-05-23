package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CharacterSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        ImageButton[] imageButtons = new ImageButton[10];

        imageButtons[0] = (ImageButton) findViewById(R.id.image_btn_blonde);
        imageButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 0));
            }
        });

        imageButtons[1] = (ImageButton) findViewById(R.id.image_btn_brunette);
        imageButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 1));
            }
        });

        imageButtons[2] = (ImageButton) findViewById(R.id.image_btn_ginger);
        imageButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 2));
            }
        });

        imageButtons[3] = (ImageButton) findViewById(R.id.image_btn_brunette2);
        imageButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 3));
            }
        });

        imageButtons[4] = (ImageButton) findViewById(R.id.image_btn_bold);
        imageButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 4));
            }
        });

        imageButtons[5] = (ImageButton) findViewById(R.id.image_btn_blonde_boy);
        imageButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 5));
            }
        });

        imageButtons[6] = (ImageButton) findViewById(R.id.image_btn_brunette_boy);
        imageButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 6));
            }
        });

        imageButtons[7] = (ImageButton) findViewById(R.id.image_btn_ginger_boy);
        imageButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 7));
            }
        });

        imageButtons[8] = (ImageButton) findViewById(R.id.image_btn_brunette2_boy);
        imageButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 8));
            }
        });

        imageButtons[9] = (ImageButton) findViewById(R.id.image_btn_bold_boy);
        imageButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 9));
            }
        });

    }
}