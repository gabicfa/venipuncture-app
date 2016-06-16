package br.edu.insper.puncaovenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CertificateActivity extends AppCompatActivity {

    private LinearLayout playagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.main_menu_activity);
        playagain = (LinearLayout) findViewById(R.id.playagain) ;

        assert myLayout != null;
        playagain.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent m) {
                startActivity(new Intent(CertificateActivity.this, CharacterSelectionActivity.class));
                return true;
            }
        });
    }
}