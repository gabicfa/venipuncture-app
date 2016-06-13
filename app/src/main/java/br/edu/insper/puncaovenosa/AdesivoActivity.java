package br.edu.insper.puncaovenosa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdesivoActivity extends AppCompatActivity {
    private ImageView cateter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adesivo);
        this.cateter = (ImageView) findViewById(R.id.cateter);
        cateter.setVisibility(View.INVISIBLE);
    }
}
