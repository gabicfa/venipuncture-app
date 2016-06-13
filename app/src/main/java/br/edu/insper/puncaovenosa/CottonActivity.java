package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CottonActivity extends AppCompatActivity {

    private int counter; // PC - Contador para saber se o braço fora limpo

    private ImageView cotton;
    private ImageView garrote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotton);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cotton_activity);

        this.cotton  = (ImageView) findViewById(R.id.algodao);
        this.garrote = (ImageView) findViewById(R.id.garrote);
        this.counter = 100;

        garrote.setVisibility(View.INVISIBLE);

        assert myLayout != null;
        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }


    void handleTouch(MotionEvent m)
    {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            int cottonWidth = cotton.getWidth();
            int cottonHeight = cotton.getHeight();


            // PC - A posição que reconhecemos é a superior esquerda da
            //      imagem, no entanto, queremos que o nosso usuário a mova
            //      com base no seu centro, por isso somamos metade da sua
            //      altura e largura
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            x -= cottonWidth / 2;
            y -= cottonHeight / 2;
//
//            int id = m.getPointerId(i);
//            int action = m.getActionMasked();
//            int actionIndex = m.getActionIndex();
//            String actionString;
//
//            switch (action)
//            {
//                /* PC - Atualmente esse switch case não é necessário,
//                 *      usei ele para entender como funciona o sensor
//                 *      de touch do android, se alguem estiver com
//                 *      dúvida, recomendo descomentar este bloco
//                 *      para entender melhor */
//                case MotionEvent.ACTION_DOWN:
//                    actionString = "DOWN";
//                    break;
//                case MotionEvent.ACTION_UP:
//                    actionString = "UP";
//                    break;
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    actionString = "PNTR DOWN";
//                    break;
//                case MotionEvent.ACTION_POINTER_UP:
//                    actionString = "PNTR UP";
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    actionString = "MOVE";
//                    break;
//                default:
//                    actionString = "";
//            }
//
//            String touchStatus = "Action: " + actionString + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
//
//            System.out.println(touchStatus);

            System.out.println("Cotton X: " + cotton.getX() + ", finger X: " + x);
            System.out.println("Cotton Y: " + cotton.getY() + ", finger Y: " + y);


            if(this.counter > 0) {
                // PC - Precisamos checar se a pessoa está apertando em um lugar relativamente proximo
                //      a onde esta o algodão (ou estava), se estiver, podemos movê-lo, caso contrário,
                //      nada acontece.
                if((x - this.cotton.getX() <= 100 && x - this.cotton.getX() >= -100) && (y - this.cotton.getY() <= 100 && y -this.cotton.getY() >= -100)) {
                    // PC - Movendo o algodão
                    this.cotton.setX(x);
                    this.cotton.setY(y);
                    this.counter--;

                    // PC - Se a pessoa está movendo o algodão, checamos se ela está de fato sobre uma
                    //      área a ser limpada
                    if((x >= 250 && x <= 520) && (y >= 550 && y <= 930)) {
                        // PC - Se ela estiver, reduzimos o contador de "sujeiras" em um
                        System.out.println("Counter: " + this.counter);
                    }
                }
            } else {
                // PC - Se o contador chegou a zero, significa que o braço está limpo e devemos, portanto
                //      devemos mudar de activity
                startActivity(new Intent(CottonActivity.this, CateterActivity.class));
            }
        }
    }
}
