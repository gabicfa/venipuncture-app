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

    // Valores das quinas do quadrilatero que define a área a ser limpa
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    private RelativeLayout myLayout;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotton);

        myLayout = (RelativeLayout) findViewById(R.id.cotton_activity);

        this.cotton  = (ImageView) findViewById(R.id.algodao);
        ImageView garrote = (ImageView) findViewById(R.id.garrote);
        this.counter = 250;

        // PC - Definimos uma constante X para multiplicar pelo valor da tela e encontrar a posição
        //      em porcentagem na tela (mesmo para Y)
        int width = getWindowManager().getDefaultDisplay().getWidth();   // Conseguir a largura da tela
        int height = getWindowManager().getDefaultDisplay().getHeight(); // e a altura

        double xMinMultiplier = (double) 250 / 1440;
        double xMaxMultiplier = (double) 520 / 1440;
        double yMinMultiplier = (double) 550 / 2560;
        double yMaxMultiplier = (double) 930 / 2560;

        this.xMin = (int) (width * xMinMultiplier);
        this.xMax = (int) (width * xMaxMultiplier);
        this.yMin = (int) (height * yMinMultiplier);
        this.yMax = (int) (height * yMaxMultiplier);

        assert garrote != null;
        garrote.setVisibility(View.INVISIBLE);

        index = getIntent().getIntExtra("i", -1);


        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_germes);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_germes);
        }

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

            if(this.counter > 0) {
                // PC - Precisamos checar se a pessoa está apertando em um lugar relativamente proximo
                //      a onde esta o algodão (ou estava), se estiver, podemos movê-lo, caso contrário,
                //      nada acontece.
                if((x - this.cotton.getX() <= 200 && x - this.cotton.getX() >= -200) && (y - this.cotton.getY() <= 200 && y -this.cotton.getY() >= -200)) {
                    // PC - Movendo o algodão
                    this.cotton.setX(x);
                    this.cotton.setY(y);

                    // PC - Se a pessoa está movendo o algodão, checamos se ela está de fato sobre uma
                    //      área a ser limpada
                    if((x >= xMin && x <= xMax) && (y >= yMin && y <= yMax)) {
                        // PC - Se ela estiver, reduzimos o contador de "sujeiras" em um
                        this.counter--;
                        System.out.println("Counter: " + this.counter);
                        if(this.counter<180 && this.counter>80){

                            if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                                myLayout.setBackgroundResource(R.drawable.arm_germes2);
                            }
                            else if(index == 2 || index == 7) {
                                myLayout.setBackgroundResource(R.drawable.arm3_germes2);
                            }
                            else if(index == 3 || index ==8) {
                                myLayout.setBackgroundResource(R.drawable.arm2_germes2);
                            }

                        }
                        else{
                            if(this.counter<=80){
                                if(index == 0 || index ==1 || index ==4 || index==5 || index ==6 || index==9){
                                    myLayout.setBackgroundResource(R.drawable.arm_germes1);
                                }
                                else if(index == 2 || index == 7) {
                                    myLayout.setBackgroundResource(R.drawable.arm3_germes1);
                                }
                                else if(index == 3 || index ==8) {
                                    myLayout.setBackgroundResource(R.drawable.arm2_germes1);
                                }

                            }
                        }
                    }
                }
            } else {
                // PC - Se o contador chegou a zero, significa que o braço está limpo e devemos, portanto
                //      devemos mudar de activity
                startActivity(new Intent(CottonActivity.this, CateterActivity.class).putExtra("i", index));
            }
        }
    }
}
