package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TirarSeringaActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView seringa;
    private int seringaX = 595;
    private int seringaY = 1445;
    private int seringan;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_seringa);

        myLayout = (RelativeLayout) findViewById(R.id.tirarseringa_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.seringa = (ImageView) findViewById(R.id.seringa);
        seringa.setVisibility(View.INVISIBLE);
        cateter.setVisibility(View.INVISIBLE);


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


            System.out.println("Cateter X: " + seringa.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + seringa.getY() + ", finger Y: " + y);

            if ((dy <= seringaY + 150) && ((dx <= seringaX + 200 && dx >= seringaX - 200))) {
                System.out.println("seringa");
                this.seringa.setX(x);
                this.seringa.setY(y);
                seringan = 1;
            }
            if (seringan == 1) {
                seringa.setVisibility(View.VISIBLE);
                myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal);
                if ((x - this.seringa.getX() <= 100 && x - this.seringa.getX() >= -100) && (y - this.seringa.getY() <= 100 && y - this.seringa.getY() >= -100)) {
//                    if(dy>=1940){ --> PARA O CEL DO CUNI
                    if(dy>=1360){//--> PARA O TABLET
                        myLayout.setBackgroundResource(R.drawable.arm_cateter);
                    }
                    else{
                        this.seringa.setX(x);
                        this.seringa.setY(y);
                    }

                }
            }
        }
    }
}
