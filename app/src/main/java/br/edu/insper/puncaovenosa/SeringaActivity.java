package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SeringaActivity extends AppCompatActivity {

    private ImageView seringa;
    private ImageView cateter;
    private int cateterX = 590;
    private int cateterY = 1035;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seringa);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.seringa_activity);

        ImageView adesivo = (ImageView) findViewById(R.id.adesivo);
        this.seringa = (ImageView) findViewById(R.id.seringa);
        this.cateter = (ImageView) findViewById(R.id.cateter);

        assert adesivo != null;
        adesivo.setVisibility(View.INVISIBLE);
        cateter.setVisibility(View.INVISIBLE);

        index = getIntent().getIntExtra("i", -1);

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMultiplier = (double) 590 / 1440;
        double yMultiplier = (double) 1035 / 2560;

        this.cateterX = (int) (width * xMultiplier);
        this.cateterY = (int) (height * yMultiplier);

        assert myLayout != null;
        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_cateter_sinalizado);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_cateter_sinalizado);
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

            int seringaWidth = seringa.getWidth();
            int seringaHeight = seringa.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - seringaWidth / 2;
            int y = dy - seringaHeight / 2;


            System.out.println("Cateter X: " + cateter.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + cateter.getY() + ", finger Y: " + y);

            if ((x - this.seringa.getX() <= 150 && x - this.seringa.getX() >= -150) && (y - this.seringa.getY() <= 150 && y - this.seringa.getY() >= -150)) {
                this.seringa.setX(x);
                this.seringa.setY(y);
                if(((dy - seringaHeight / 2 <= cateterY + 200) && (dy - seringaHeight / 2 >= cateterY - 200)) && ((dx+seringaWidth/2<=cateterX+300 && dx+seringaWidth/2>=cateterX-300))) {
                    startActivity(new Intent(SeringaActivity.this, MedicamentoActivity.class).putExtra("i", index));
                }
            }
        }
    }
}


