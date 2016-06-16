package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CateterActivity extends AppCompatActivity {

    private ImageView cateter;
    private ImageView garrote;
    private int circleX = 560;
    private int circleY = 765;

    private RelativeLayout myLayout;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cateter);

        myLayout = (RelativeLayout) findViewById(R.id.cateter_activity);

        this.cateter = (ImageView) findViewById(R.id.cateter);
        this.garrote = (ImageView) findViewById(R.id.garrote);
        garrote.setVisibility(View.INVISIBLE);
        index = getIntent().getIntExtra("i", -1);

        if(index == 2 || index == 7) {
            myLayout.setBackgroundResource(R.drawable.arm3_dot);
        }
        else if(index == 3 || index ==8) {
            myLayout.setBackgroundResource(R.drawable.arm2_dot);
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

            int cateterWidth = cateter.getWidth();
            int cateterHeight = cateter.getHeight();

            int dx = (int) m.getX(i);
            int dy = (int) m.getY(i);
            int x = dx - cateterWidth / 2;
            int y = dy - cateterHeight / 2;



            if ((x - this.cateter.getX() <= 100 && x - this.cateter.getX() >= -100) && (y - this.cateter.getY() <= 100 && y - this.cateter.getY() >= -100)) {
                this.cateter.setX(x);
                this.cateter.setY(y);
                if((dy-cateterHeight/2<=circleY+100) && ((dx<=circleX+100 && dx>=circleX-100)))
                {
                    startActivity(new Intent(CateterActivity.this, TirarGarroteActivity.class).putExtra("i", index));

                }
            }
        }
    }
}
