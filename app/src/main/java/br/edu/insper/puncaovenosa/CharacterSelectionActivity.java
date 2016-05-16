package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CharacterSelectionActivity extends AppCompatActivity {
    private ImageButton[] imageButtons;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        imageButtons = new ImageButton[10];

        this.imageButtons[0] = (ImageButton) findViewById(R.id.image_btn_blonde);
        this.imageButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 0));
            }
        });

        this.imageButtons[1] = (ImageButton) findViewById(R.id.image_btn_brunette);
        this.imageButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 1));
            }
        });

        this.imageButtons[2] = (ImageButton) findViewById(R.id.image_btn_brunette1);
        this.imageButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 2));
            }
        });

        this.imageButtons[3] = (ImageButton) findViewById(R.id.image_btn_brunette2);
        this.imageButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 3));
            }
        });

        this.imageButtons[4] = (ImageButton) findViewById(R.id.image_btn_brunette3);
        this.imageButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 4));
            }
        });

        this.imageButtons[5] = (ImageButton) findViewById(R.id.image_btn_blonde_boy);
        this.imageButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 5));
            }
        });

        this.imageButtons[6] = (ImageButton) findViewById(R.id.image_btn_brunette_boy);
        this.imageButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 6));
            }
        });

        this.imageButtons[7] = (ImageButton) findViewById(R.id.image_btn_brunette1_boy);
        this.imageButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 7));
            }
        });

        this.imageButtons[8] = (ImageButton) findViewById(R.id.image_btn_brunette2_boy);
        this.imageButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 8));
            }
        });

        this.imageButtons[9] = (ImageButton) findViewById(R.id.image_btn_brunette3_boy);
        this.imageButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharacterSelectionActivity.this, GameActivity.class).putExtra("i", 9));
            }
        });

    }
}