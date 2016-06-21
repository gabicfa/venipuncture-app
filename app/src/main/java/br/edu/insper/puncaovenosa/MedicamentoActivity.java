package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MedicamentoActivity extends AppCompatActivity {

    private int circleX; // red circle
    private int circleY;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.medicamento_activity);

        ImageView cateter = (ImageView) findViewById(R.id.cateter);
        ImageView adesivo = (ImageView) findViewById(R.id.adesivo);
        ImageView seringa = (ImageView) findViewById(R.id.seringa);

        assert cateter != null;
        assert adesivo != null;
        assert seringa != null;
        cateter.setVisibility(View.INVISIBLE);
        adesivo.setVisibility(View.INVISIBLE);
        seringa.setVisibility(View.INVISIBLE);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 715 / 1440;
        double yMultiplier = (double) 1445 / 2560;

        this.circleX = (int) (width * xMultiplier);
        this.circleY = (int) (height * yMultiplier);

        index = getIntent().getIntExtra("i", -1);

        assert myLayout != null;
        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_seringa_cateter);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_seringa_cateter);
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

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);

            if (((dy <= circleY + 70) && (dy >= circleY - 70)) && ((dx <= circleX + 80 && dx <= circleX + 80))) {
                startActivity(new Intent(MedicamentoActivity.this, TirarSeringaActivity.class).putExtra("i", index));
            }
        }
    }
}
