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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateter);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cateter_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);

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

            int cateterWidth = cateter.getWidth();
            int cateterHeight = cateter.getHeight();

            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            x -= cateterWidth / 2;
            y -= cateterHeight / 2;


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
