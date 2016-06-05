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

    private int level   = 2;   // PC - "contador" que define a etapa do tratamento que estamos


    private ImageView cateter;
    //private ImageView circulo;

    private int originalCateterX;
    private int originalCateterY;

    private int cateterWidth;
    private int cateterHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cateter);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cateter_activity);


        this.cateter = (ImageView) findViewById(R.id.cateter);
        //this.circulo = (ImageView) findViewById(R.id.circulo);

        this.originalCateterX    = (int) this.cateter.getX();
        this.originalCateterY    = (int) this.cateter.getY();
        System.out.println("Original cateter X: " + originalCateterX);

        assert myLayout != null;
        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }

    public int getOriginalX(ImageView image) {
        if (image == cateter)
            return this.originalCateterX;
        else
            return -1;
    }

    public int getOriginalY(ImageView image) {

        if(image == cateter)
            return this.originalCateterY;
        else
            return -1;
    }

    void handleTouch(MotionEvent m) {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {

            this.cateterWidth = cateter.getWidth();
            this.cateterHeight = cateter.getHeight();

            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            x -= this.cateterWidth / 2;
            y -= this.cateterHeight / 2;


            System.out.println("Cateter X: " + cateter.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + cateter.getY() + ", finger Y: " + y);


            if ((x - this.cateter.getX() <= 100 && x - this.cateter.getX() >= -100) && (y - this.cateter.getY() <= 100 && y - this.cateter.getY() >= -100)) {
                // PC - Movendo o cateter
                this.cateter.setX(x);
                this.cateter.setY(y);


            }
        }
    }


}
