package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SeringaActivity extends AppCompatActivity {

    private ImageView seringa;
    private ImageView cateter;
    private int cateterX = 590;
    private int cateterY = 1035;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seringa);

        myLayout = (RelativeLayout) findViewById(R.id.seringa_activity);

        this.seringa = (ImageView) findViewById(R.id.seringa);
        this.cateter = (ImageView) findViewById(R.id.cateter);
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


            System.out.println("Cateter X: " + cateter.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + cateter.getY() + ", finger Y: " + y);

            if ((x - this.seringa.getX() <= 100 && x - this.seringa.getX() >= -100) && (y - this.seringa.getY() <= 100 && y - this.seringa.getY() >= -100)) {
                this.seringa.setX(x);
                this.seringa.setY(y);
                if((dy-seringaHeight/2<=cateterY+200) && ((dx+seringaWidth/2<=cateterX+300 && dx+seringaWidth/2>=cateterX-300))) {
                    startActivity(new Intent(SeringaActivity.this, MedicamentoActivity.class));
                }
            }
        }
    }
}


