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

    private ImageView adesivo;
    private int adesivoX;
    private int adesivoY;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adesivo);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.adesivo_activity);

        ImageView cateter = (ImageView) findViewById(R.id.cateter);
        this.adesivo= (ImageView) findViewById(R.id.adesivo);
        assert cateter != null;
        cateter.setVisibility(View.INVISIBLE);
        index = getIntent().getIntExtra("i", -1);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 440 / 1440;
        double yMultiplier = (double) 860 / 2560;

        this.adesivoX = (int) (width * xMultiplier);
        this.adesivoY = (int) (height * yMultiplier);

        assert myLayout != null;
        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_cateter);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_cateter);
        }

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

            if ((x - this.adesivo.getX() <= 200 && x - this.adesivo.getX() >= -200) && (y - this.adesivo.getY() <= 200 && y - this.adesivo.getY() >= -200)) {
                this.adesivo.setX(x);
                this.adesivo.setY(y);
                if(((dy - adesivoHeight / 2 <= adesivoY + 250) && (dy - adesivoHeight / 2 >= adesivoY - 250)) && ((dx+adesivoWidth/2<=adesivoX+250 && dx-adesivoWidth/2>=adesivoX-250)))
                {
                    startActivity(new Intent(AdesivoActivity.this, SeringaActivity.class).putExtra("i", index));
                }
            }
        }
    }
}
