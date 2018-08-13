package com.example.multimedia.hospital;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.multimedia.hospital.fragments.Fragment_informe;
import com.example.multimedia.hospital.fragments.Fragment_solicitud;

public class Principal extends AppCompatActivity implements Fragment_informe.OnFragmentInteractionListener, Fragment_solicitud.OnFragmentInteractionListener{

    private int[] idBotones = new int[]{R.id.btn_solicitud, R.id.btn_informe, R.id.btn_salida};
    private ImageButton[] imageBotones = new ImageButton[3];
    Fragment[] objFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Almacenamos las instancias de los Fragments
        objFragment = new Fragment[2];
        objFragment[0] = new Fragment_solicitud();
        objFragment[1] = new Fragment_informe();


        for (int i=0; i<idBotones.length; i++){
            //Creamos las referencias
            imageBotones[i] = (ImageButton) findViewById(idBotones[i]);

            final int n = i;
            //Creamos el evento
            imageBotones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                switch (imageBotones[n].getId()){
                    case R.id.btn_solicitud:
                        //imageBotones[n].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        imageBotones[n].setBackground(getResources().getDrawable(R.drawable.boton_blue, null));
                        imageBotones[1].setBackgroundColor(0);
                        break;
                    case R.id.btn_informe:
                        //imageBotones[n].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        imageBotones[n].setBackground(getResources().getDrawable(R.drawable.boton_blue, null));
                        imageBotones[0].setBackgroundColor(0);
                        break;
                }
                if (n == 2){
                    finish();
                }else{
                    //Mostramos el Fragment correspondiente
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, objFragment[n]).addToBackStack(null).commit();
                }
                }
            });
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
