package br.edu.insper.puncaovenosa;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProcedureActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.procedure_activity);

        final ImageView cotton = (ImageView) findViewById(R.id.algodao);
        final ImageView shot = (ImageView) findViewById(R.id.seringa);

        assert cotton != null;

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
        final ImageView cotton = (ImageView) findViewById(R.id.algodao);
        final int cottonWidth = cotton.getWidth();
        final int cottonHeight = cotton.getHeight();

//        final ImageView shot = (ImageView) findViewById(R.id.seringa);

        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++)
        {
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

            // PC - Precisamos checar se a pessoa está apertando em um lugar relativamente proximo
            //      a onde esta o algodão (ou estava), se estiver, podemos movê-lo, caso contrário,
            //      nada acontece.
            if((x - cotton.getX() <= 100 && x - cotton.getX() >= -100) && (y - cotton.getY() <= 100 && y - cotton.getY() >= -100)) {
                // PC - Movendo o algodão
                cotton.setX(x);
                cotton.setY(y);
            }

        }
    }
}
