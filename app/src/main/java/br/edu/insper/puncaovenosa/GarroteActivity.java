package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GarroteActivity extends AppCompatActivity {

    private ImageView garrote;
    private int lineX = 220;
    private int lineY = 270;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garrote);

        myLayout = (RelativeLayout) findViewById(R.id.garrote_activity);

        this.garrote = (ImageView) findViewById(R.id.garrote);
        //garrote.setVisibility(View.INVISIBLE);


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

            if((dy-garroteHeight/2<=lineY+70) && ((dx+garroteWidth/2<=lineX+80 && dx+garroteWidth/2<=lineX+80)))
            {
                startActivity(new Intent(GarroteActivity.this, CottonActivity.class));

            }
            else{
                if ((x - this.garrote.getX() <= 100 && x - this.garrote.getX() >= -100) && (y - this.garrote.getY() <= 100 && y - this.garrote.getY() >= -100)) {
                    // ®PC - Movendo o cateter
                    this.garrote.setX(x);
                    this.garrote.setY(y);
                }
            }
        }
    }


}
