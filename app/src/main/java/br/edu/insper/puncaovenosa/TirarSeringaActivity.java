package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TirarSeringaActivity extends AppCompatActivity {

    private ImageView seringa;

    private int seringaX;
    private int seringaY;
    private int bandejaY;
    private int seringan  = 0;
    private int seringan2 = 1;
    private int index;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_seringa);

        myLayout = (RelativeLayout) findViewById(R.id.tirarseringa_activity);

        ImageView cateter = (ImageView) findViewById(R.id.cateter);
        ImageView adesivo = (ImageView) findViewById(R.id.adesivo);
        this.seringa = (ImageView) findViewById(R.id.seringa);

        assert cateter != null;
        assert adesivo != null;
        cateter.setVisibility(View.INVISIBLE);
        adesivo.setVisibility(View.INVISIBLE);
        seringa.setVisibility(View.INVISIBLE);

        index = getIntent().getIntExtra("i", -1);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 595 / 1440;
        double yMultiplier = (double) 1445 / 2560;
        double bandejaMultiplier = (double) 1940 / 2560;

        this.seringaX = (int) (width * xMultiplier);
        this.seringaY = (int) (height * yMultiplier);
        this.bandejaY = (int) (height * bandejaMultiplier);

        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_seringa_vazia);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_seringa_vazia);
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

            int seringaWidth = seringa.getWidth();
            int seringaHeight = seringa.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - seringaWidth / 2;
            int y = dy - seringaHeight / 2;

            if(seringan == 0) {
                if ((dy <= seringaY + 150) && ((dx <= seringaX + 200 && dx >= seringaX - 200))) {
                    this.seringa.setX(x);
                    this.seringa.setY(y);
                    seringan = 1;
                }
            }
            if (seringan == 1 && seringan2 == 1) {
                seringa.setVisibility(View.VISIBLE);
                if ((x - this.seringa.getX() <= 100 && x - this.seringa.getX() >= -100) && (y - this.seringa.getY() <= 100 && y - this.seringa.getY() >= -100)) {
                    if(dy>=bandejaY){
                        if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                            myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal_adesivo);
                        }
                        else if(index == 2 || index == 7) {
                            myLayout.setBackgroundResource(R.drawable.arm3_bandeja_sinal_adesivo);
                        }
                        else if(index == 3 || index ==8) {
                            myLayout.setBackgroundResource(R.drawable.arm2_bandeja_sinal_adesivo);
                        }
                        seringan2 = 2;
                    }
                    else{
                        if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                            myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal_adesivo);
                        }
                        else if(index == 2 || index == 7) {
                            myLayout.setBackgroundResource(R.drawable.arm3_bandeja_sinal_adesivo);
                        }
                        else if(index == 3 || index ==8) {
                            myLayout.setBackgroundResource(R.drawable.arm2_bandeja_sinal_adesivo);
                        }
                        this.seringa.setX(x);
                        this.seringa.setY(y);
                    }

                }
            }

            if(seringan2 == 2 && seringan == 1){
                startActivity(new Intent(TirarSeringaActivity.this, CertificateActivity.class));
            }
        }
    }
}
