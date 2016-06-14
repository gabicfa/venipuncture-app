package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TirarGarroteActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView garrote;
    private int lineX = 420;
    private int lineY = 660;
    private int garroten;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_garrote);

        myLayout = (RelativeLayout) findViewById(R.id.tirargarrote_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.garrote = (ImageView) findViewById(R.id.garrote);
        garrote.setVisibility(View.INVISIBLE);
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

            int garroteWidth = garrote.getWidth();
            int garroteHeight = garrote.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - garroteWidth / 2;
            int y = dy - garroteHeight / 2;


            System.out.println("Cateter X: " + garrote.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + garrote.getY() + ", finger Y: " + y);

            if ((dy <= lineY + 150) && ((dx <= lineX + 200 && dx >= lineX - 200))) {
                System.out.println("garrote");
                this.garrote.setX(x);
                this.garrote.setY(y);
                garroten = 1;
                //startActivity(new Intent(Tri.this, TirarGarroteActivity.class));
            }
            if (garroten == 1) {
                garrote.setVisibility(View.VISIBLE);
                myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal);
                if ((x - this.garrote.getX() <= 100 && x - this.garrote.getX() >= -100) && (y - this.garrote.getY() <= 100 && y - this.garrote.getY() >= -100)) {
//                    if(dy>=1940){
                    if(dy>1290){
                        startActivity(new Intent(TirarGarroteActivity.this, SeringaActivity.class));
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
