package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CateterActivity extends AppCompatActivity {

    private ImageView cateter;
    private int circleX;
    private int circleY;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateter);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cateter_activity);

        ImageView garrote = (ImageView) findViewById(R.id.garrote);
        assert garrote != null;
        garrote.setVisibility(View.INVISIBLE);

        this.cateter = (ImageView) findViewById(R.id.cateter);

        index = getIntent().getIntExtra("i", -1);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 560 / 1440;
        double yMultiplier = (double) 765 / 2560;

        this.circleX = (int) (width * xMultiplier);
        this.circleY = (int) (height * yMultiplier);

        assert myLayout != null;
        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_dot);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_dot);
        }

        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }

    void handleTouch(MotionEvent m) {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {

            int cateterWidth = cateter.getWidth();
            int cateterHeight = cateter.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - cateterWidth / 2;
            int y = dy - cateterHeight / 2;



            if ((x - this.cateter.getX() <= 200 && x - this.cateter.getX() >= -200) && (y - this.cateter.getY() <= 200 && y - this.cateter.getY() >= -200)) {
                this.cateter.setX(x);
                this.cateter.setY(y);
                if(((dy - cateterHeight / 2 <= circleY + 200) && (dy - cateterHeight / 2 >= circleY - 200)) && ((dx<=circleX+200 && dx>=circleX-200)))
                {
                    startActivity(new Intent(CateterActivity.this, TirarGarroteActivity.class).putExtra("i", index));

                }
            }
        }
    }
}
