package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AdesivoActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView adesivo;
    private int cateterX = 440;
    private int cateterY = 860;

    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adesivo);

        myLayout = (RelativeLayout) findViewById(R.id.adesivo_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.adesivo= (ImageView) findViewById(R.id.adesivo);
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

            int adesivoWidth = adesivo.getWidth();
            int adesivoHeight = adesivo.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - adesivoWidth / 2;
            int y = dy - adesivoWidth / 2;

            if ((x - this.adesivo.getX() <= 100 && x - this.adesivo.getX() >= -100) && (y - this.adesivo.getY() <= 100 && y - this.adesivo.getY() >= -100)) {
                this.adesivo.setX(x);
                this.adesivo.setY(y);
                if((dy-adesivoWidth/2<=cateterY+250) && ((dx+adesivoHeight/2<=cateterX+250 && dx-adesivoHeight/2>=cateterX-250)))
                {
                    System.out.println("Colocou adesivo");
                    myLayout.setBackgroundResource(R.drawable.arm_adesivo);
                    adesivo.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(AdesivoActivity.this, SeringaActivity.class));

                }
            }
        }
    }
}
