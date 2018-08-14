package com.example.multimedia.hospital;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imagen = (ImageView) findViewById(R.id.image_splash);
        texto = (TextView) findViewById(R.id.view_principal);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent objIntent = new Intent(SplashActivity.this, Principal.class);
                startActivity(objIntent);
                finish();
            }
        }, 3000);
    }
}
