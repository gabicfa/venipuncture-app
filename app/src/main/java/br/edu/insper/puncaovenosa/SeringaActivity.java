package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SeringaActivity extends AppCompatActivity {

    private ImageView seringa;

    private int height;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seringa);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.seringa_activity);

        this.seringa = (ImageView) findViewById(R.id.seringa);

        // PC - Pegamos as informações da tela (largura e altura) para encontrar o seu centro
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        this.height = displaymetrics.heightPixels;
        this.width  = displaymetrics.widthPixels;

        System.out.println("Height: " + height + " Width: " + width);


        assert myLayout != null;
        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }

    void handleTouch(MotionEvent m)
    {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            int seringaWidth  = seringa.getWidth();
            int seringaHeight = seringa.getHeight();


            // PC - A posição que reconhecemos é a superior esquerda da
            //      imagem, no entanto, queremos que o nosso usuário a mova
            //      com base no seu centro, por isso somamos metade da sua
            //      altura e largura
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            x -= seringaWidth / 2;
            y -= seringaHeight / 2;


            System.out.println("Cotton X: " + seringa.getX() + ", finger X: " + x);
            System.out.println("Cotton Y: " + seringa.getY() + ", finger Y: " + y);


            // PC - Precisamos checar se a pessoa está apertando em um lugar relativamente proximo
            //      a onde esta a seringa (ou estava), se estiver, podemos movê-la, caso contrário,
            //      nada acontece.
            if((x - this.seringa.getX() <= 100 && x - this.seringa.getX() >= -100) && (y - this.seringa.getY() <= 100 && y -this.seringa.getY() >= -100)) {
                // PC - Movendo a seringa
                this.seringa.setX(x);
                this.seringa.setY(y);

                // PC - Se a pessoa está movendo a seringa, checamos se ela está no local que ela
                //      deve ser inserida, se estiver, acaba esta activity
                if(((x >= (this.width - 50)) && (x <= (this.width + 50))) && (((y >= (this.height - 50))) && (y <= (this.height + 50)))) {
                    // PC - Se ela estiver, reduzimos o contador de "sujeiras" em um
                    startActivity(new Intent(SeringaActivity.this, AdesivoActivity.class));
                }
            }
        }
    }
}
