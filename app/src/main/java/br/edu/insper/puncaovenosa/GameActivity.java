package br.edu.insper.puncaovenosa;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    private int index;
    private ImageView images[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        images = new ImageView[10];

        this.images[0] = (ImageView) findViewById(R.id.blonde_girl_body);
        this.images[1] = (ImageView) findViewById(R.id.blonde_boy_body);
        this.images[2] = (ImageView) findViewById(R.id.brunette_girl_body);
        this.images[3] = (ImageView) findViewById(R.id.brunette_boy_body);
        this.images[4] = (ImageView) findViewById(R.id.brunette_girl1_body);
        this.images[5] = (ImageView) findViewById(R.id.brunette_boy1_body);
        this.images[6] = (ImageView) findViewById(R.id.brunette_girl2_body);
        this.images[7] = (ImageView) findViewById(R.id.brunette_boy2_body);
        this.images[8] = (ImageView) findViewById(R.id.brunette_girl3_body);
        // TODO: Corpo do menino, deixei este como provisório.
        this.images[9] = (ImageView) findViewById(R.id.brunette_girl3_body);

        for(ImageView image : this.images) {
            image.setVisibility(View.INVISIBLE);
        }

        index = getIntent().getIntExtra("i", -1);

        System.out.println(index);

        if(index == -1) {
            System.out.println("Boop");
        } else if(index > 9) {
            System.out.println("Beep");
        } else {
            this.images[index].setVisibility(View.VISIBLE);
        }
    }
}
