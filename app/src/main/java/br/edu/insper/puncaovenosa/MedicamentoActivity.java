package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MedicamentoActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView seringa;
    private int lineX = 715;
    private int lineY = 1445;
    private int seringan;

    private RelativeLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        myLayout = (RelativeLayout) findViewById(R.id.medicamento_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.seringa = (ImageView) findViewById(R.id.seringa);
        seringa.setVisibility(View.INVISIBLE);
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

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);

            System.out.println("Cateter X: " + seringa.getX() + ", finger X: " + dx);
            System.out.println("Cateter Y: " + seringa.getY() + ", finger Y: " + dy);

            if ((dy <= lineY + 70) && ((dx <= lineX + 80 && dx <= lineX + 80))) {
                System.out.println("seringa");
                seringan = 1;
                //startActivity(new Intent(Tri.this, TirarGarroteActivity.class));
            }
            if (seringan == 1) {
                startActivity(new Intent(MedicamentoActivity.this, AdesivoActivity.class));

            }
        }
    }
}
