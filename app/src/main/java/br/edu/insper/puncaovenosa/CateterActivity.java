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

    private int level = 2;   // GA - "contador" que define a etapa do tratamento que estamos


    private ImageView cateter;

    private int originalCateterX;
    private int originalCateterY;
    private int circleX = 470;
    private int circleY = 650;
    private int cateterWidth;
    private int cateterHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cateter);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cateter_activity);


        this.cateter = (ImageView) findViewById(R.id.cateter);

        this.originalCateterX = (int) this.cateter.getX();
        this.originalCateterY = (int) this.cateter.getY();
        System.out.println("Original cateter X: " + originalCateterX);

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

            this.cateterWidth = cateter.getWidth();
            this.cateterHeight = cateter.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - this.cateterWidth / 2;
            int y = dy - this.cateterHeight / 2;
//            private int circleX = 470;
//            private int circleY = 650;
//            dedoX= 427;
//            dedoY= 748

            System.out.println("Cateter X: " + cateter.getX() + ", finger X: " + dx);
            System.out.println("Cateter Y: " + cateter.getY() + ", finger Y: " + dy);

            if ((dy - this.cateter.getY()/2 >= circleY + 100) && (dx + this.cateter.getX()/2 >= circleX + 100 || dx - this.cateter.getX()/2 <= circleX - 100)) {
                if ((x - this.cateter.getX() <= 100 && x - this.cateter.getX() >= -100) && (y - this.cateter.getY() <= 100 && y - this.cateter.getY() >= -100)) {
                    // GA - Movendo o cateter
                    this.cateter.setX(x);
                    this.cateter.setY(y);
            } else {

                    System.out.println("colocou cateter");

                }
            }

        }
    }
}