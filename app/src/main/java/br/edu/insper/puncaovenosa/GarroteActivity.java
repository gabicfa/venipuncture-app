package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GarroteActivity extends AppCompatActivity {

    private ImageView garrote;
    private int lineX = 400;
    private int lineY = 675;

    private RelativeLayout myLayout;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garrote);

        myLayout = (RelativeLayout) findViewById(R.id.garrote_activity);
        assert myLayout != null;


        this.garrote = (ImageView) findViewById(R.id.garrote);
        index = getIntent().getIntExtra("i", -1);


        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_sinal_garrote);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_sinal_garrote);
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

            int garroteWidth = garrote.getWidth();
            int garroteHeight = garrote.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - garroteWidth / 2;
            int y = dy - garroteHeight / 2;

            if ((x - this.garrote.getX() <= 200 && x - this.garrote.getX() >= -200) && (y - this.garrote.getY() <= 200 && y - this.garrote.getY() >= -200)) {
                this.garrote.setX(x);
                this.garrote.setY(y);
                if((dy<=lineY+100) && ((dx<=lineX+200 && dx>=lineX-100)))
                {
                    startActivity(new Intent(GarroteActivity.this, CottonActivity.class).putExtra("i", index));

                }
            }
        }
    }
}
