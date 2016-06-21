package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TirarGarroteActivity extends AppCompatActivity {

    private ImageView garrote;
    private int garroteX;
    private int garroteY;
    private int bandejaY;
    private int garroten = 0;
    private int index;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_garrote);

        myLayout = (RelativeLayout) findViewById(R.id.tirargarrote_activity);

        ImageView cateter = (ImageView) findViewById(R.id.cateter);
        this.garrote = (ImageView) findViewById(R.id.garrote);
        assert garrote != null;
        assert cateter != null;
        garrote.setVisibility(View.INVISIBLE);
        cateter.setVisibility(View.INVISIBLE);
        index = getIntent().getIntExtra("i", -1);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 420 / 1440;
        double yMultiplier = (double) 660 / 2560;
        double bandejaMultiplier = (double) 1940 / 2560;

        this.garroteX = (int) (width * xMultiplier);
        this.garroteY = (int) (height * yMultiplier);
        this.bandejaY = (int) (height * bandejaMultiplier);

        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_garrote_sinalizado_cateter);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_garrote_sinalizado_cateter);
        }

        assert myLayout != null;
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

            int garroteWidth = garrote.getWidth();
            int garroteHeight = garrote.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - garroteWidth / 2;
            int y = dy - garroteHeight / 2;

            if(garroten != 1) {
                // Soltar o garrote
                if (((dy <= garroteY + 150) && (dy >= garroteY - 150)) && ((dx <= garroteX + 200 && dx >= garroteX - 200))) {
                    this.garrote.setX(x);
                    this.garrote.setY(y);
                    garroten = 1;
                }
            }

            if (garroten == 1) {
                garrote.setVisibility(View.VISIBLE);
                if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                    myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal);
                }
                else if(index == 2 || index == 7) {
                    myLayout.setBackgroundResource(R.drawable.arm3_bandeja_sinal);
                }
                else if(index == 3 || index ==8) {
                    myLayout.setBackgroundResource(R.drawable.arm2_bandeja_sinal);
                }

                if ((x - this.garrote.getX() <= 100 && x - this.garrote.getX() >= -100) && (y - this.garrote.getY() <= 100 && y - this.garrote.getY() >= -100)) {
                    if(dy>=bandejaY){
                        startActivity(new Intent(TirarGarroteActivity.this, AdesivoActivity.class).putExtra("i", index));
                    }
                    else{
                        // ®PC - Movendo o cateter
                        this.garrote.setX(x);
                        this.garrote.setY(y);
                    }

                }
            }
        }
    }
}
