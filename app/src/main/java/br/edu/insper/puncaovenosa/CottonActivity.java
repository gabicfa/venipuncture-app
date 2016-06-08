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

    private int counter = 250; // PC - Contador para saber se o braço fora limpo
    private int level   = 1;   // PC - "contador" que define a etapa do tratamento que estamos

    private ImageView cotton;
//    private ImageView garrote;
//    private ImageView shot;

    private int originalCottonX;
    private int originalCottonY;

    private int cottonWidth;
    private int cottonHeight;
//    private int originalGarroteX;
//    private int originalGarroteY;
//    private int garroteWidth;
//    private int garroteHeight;
//    private int originalShotX;
//    private int originalShotY;
//    private int shotWidth;
//    private int shotHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cotton);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.cotton_activity);

        this.cotton  = (ImageView) findViewById(R.id.algodao);
//        this.garrote = (ImageView) findViewById(R.id.garrote);
//        this.shot    = (ImageView) findViewById(R.id.seringa);

        this.originalCottonX  = (int) this.cotton.getX();
        System.out.println(originalCottonX);
        this.originalCottonY  = (int) this.cotton.getY();
//        this.originalGarroteX = (int) this.garrote.getX();
//        this.originalGarroteY = (int) this.garrote.getY();
//        this.originalShotX    = (int) this.shot.getX();
//        this.originalShotY    = (int) this.shot.getY();
        System.out.println("Original cotton X: " + originalCottonX);

        assert myLayout != null;
        myLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                handleTouch(m);
                return true;
            }
        });
    }

    public int getOriginalX(ImageView image) {
        if(image == cotton)
            return this.originalCottonX;
//        else if(image == shot)
//            return this.originalShotX;
//        else if(image == garrote)
//            return this.originalGarroteX;
        else
            return -1;
    }

    public int getOriginalY(ImageView image) {
        if(image == cotton)
            return this.originalCottonY;
//        else if(image == shot)
//            return this.originalShotY;
//        else if(image == garrote)
//            return this.originalGarroteY;
        else
            return -1;
    }

    void handleTouch(MotionEvent m)
    {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            this.cottonWidth = cotton.getWidth();
            this.cottonHeight = cotton.getHeight();
//            this.garroteWidth  = garrote.getWidth();
//            this.garroteHeight = garrote.getHeight();
//            this.shotWidth     = shot.getWidth();
//            this.shotHeight    = shot.getHeight();

            // PC - A posição que reconhecemos é a superior esquerda da
            //      imagem, no entanto, queremos que o nosso usuário a mova
            //      com base no seu centro, por isso somamos metade da sua
            //      altura e largura
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            x -= this.cottonWidth / 2;
            y -= this.cottonHeight / 2;
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

            // PC - Precisamos checar se a pessoa está apertando em um lugar relativamente proximo
            //      a onde esta o algodão (ou estava), se estiver, podemos movê-lo, caso contrário,
            //      nada acontece.
            if(this.counter > 0) {
                if ((x - this.cotton.getX() <= 100 && x - this.cotton.getX() >= -100) && (y - this.cotton.getY() <= 100 && y -this.cotton.getY() >= -100)) {
                    // PC - Movendo o algodão
                    this.cotton.setX(x);
                    this.cotton.setY(y);
                    this.counter--;
                    System.out.println("Counter: " + this.counter);
                }
            } else {
                //Toast.makeText(getApplicationContext(), "Limpou", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CottonActivity.this, CateterActivity.class));

                int originalX = getOriginalX(cotton);
                System.out.println(originalX);
                int originalY = getOriginalY(cotton);
                System.out.print(originalY);


            }
        }
    }



}
