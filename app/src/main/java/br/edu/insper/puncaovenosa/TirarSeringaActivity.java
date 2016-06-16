package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TirarSeringaActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView seringa;
    private ImageView adesivo;
    private int seringaX = 595;
    private int seringaY = 1445;
    private int seringan;
    private int seringan2=1;
    private int index;
    private RelativeLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_seringa);

        myLayout = (RelativeLayout) findViewById(R.id.tirarseringa_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.seringa = (ImageView) findViewById(R.id.seringa);
        this.adesivo = (ImageView) findViewById(R.id.adesivo);
        seringa.setVisibility(View.INVISIBLE);
        cateter.setVisibility(View.INVISIBLE);
        adesivo.setVisibility(View.INVISIBLE);
        index = getIntent().getIntExtra("i", -1);

        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_seringa_vazia);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_seringa_vazia);
        }

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


            System.out.println("Cateter X: " + seringa.getX() + ", finger X: " + x);
            System.out.println("Cateter Y: " + seringa.getY() + ", finger Y: " + y);

            if ((dy <= seringaY + 150) && ((dx <= seringaX + 200 && dx >= seringaX - 200))) {
                System.out.println("seringa");
                this.seringa.setX(x);
                this.seringa.setY(y);
                seringan = 1;
            }
            if (seringan == 1 && seringan2==1) {
                seringa.setVisibility(View.VISIBLE);
//                myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal);
                if ((x - this.seringa.getX() <= 100 && x - this.seringa.getX() >= -100) && (y - this.seringa.getY() <= 100 && y - this.seringa.getY() >= -100)) {
                    if(dy>=1940){ //--> PARA O CEL DO CUNI
//                    if(dy>=1360){//--> PARA O TABLET
                        if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                            myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal_adesivo);
                        }
                        else if(index == 2 || index == 7) {
                            myLayout.setBackgroundResource(R.drawable.arm3_bandeja_sinal_adesivo);
                        }
                        else if(index == 3 || index ==8) {
                            myLayout.setBackgroundResource(R.drawable.arm2_bandeja_sinal_adesivo);}

                        seringan2 = 2;
                    }
                    else{
                        if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                            myLayout.setBackgroundResource(R.drawable.arm_bandeja_sinal_adesivo);
                        }
                        else if(index == 2 || index == 7) {
                            myLayout.setBackgroundResource(R.drawable.arm3_bandeja_sinal_adesivo);
                        }
                        else if(index == 3 || index ==8) {
                            myLayout.setBackgroundResource(R.drawable.arm2_bandeja_sinal_adesivo);}
                        this.seringa.setX(x);
                        this.seringa.setY(y);
                    }

                }
            }

            if(seringan2 ==2 && seringan ==1){
                this.seringa.setX(675);
                this.seringa.setY(1865);
                startActivity(new Intent(TirarSeringaActivity.this, CertificateActivity.class));
            }
        }
    }
}
