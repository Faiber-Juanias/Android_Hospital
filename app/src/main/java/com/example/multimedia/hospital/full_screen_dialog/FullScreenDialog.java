package com.example.multimedia.hospital.full_screen_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multimedia.hospital.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FullScreenDialog extends DialogFragment{

    private TextView viewFecha;
    private TextView viewNombre;
    private TextView viewCoordenada;
    private Button btnEnvia;
    private String fechaHora="0";
    private String nombre="0";
    private String comentario="0";
    private String coordenada="0";

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.full_screen_dialog, container, false);

        //Creamos las referencias
        viewFecha = (TextView) vista.findViewById(R.id.view_fecha);
        viewNombre = (TextView) vista.findViewById(R.id.view_nombre);
        viewCoordenada = (TextView) vista.findViewById(R.id.view_gps);
        btnEnvia = (Button) vista.findViewById(R.id.btn_envia_solicitud_dos);

        //Si recibimos los datos del bundle
        if (getArguments() != null){
            //Almaceno los datos en variables globales
            fechaHora = getFechaHora();
            nombre = getArguments().getString("solicitante");
            comentario = getArguments().getString("comentario");
            coordenada = getArguments().getString("coordenada");

            viewFecha.setText(fechaHora);
            viewNombre.setText(nombre);
            viewCoordenada.setText(coordenada);

            btnEnvia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    creaArchivoSolicitud();
                }
            });
        }

        return vista;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog objDialog = super.onCreateDialog(savedInstanceState);
        objDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return objDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.context = context;
        }
    }

    public String getFechaHora(){
        String fechaHora;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);

        Calendar objCalendar = Calendar.getInstance();
        int h = objCalendar.get(Calendar.HOUR_OF_DAY);
        int m = objCalendar.get(Calendar.MINUTE);

        String horas = String.valueOf(h);
        String minutos = String.valueOf(m);

        String horaMinuto = "" + horas + ":" + minutos;
        return fechaHora = "" + fecha + " " + horaMinuto;
    }

    public void creaArchivoSolicitud(){
        try {
            int contadorArchivoEntradas = 1;
            //Creamos un ArrayList para almacenar el orden de los archivos
            ArrayList<String> arrayOrdenArchivo = new ArrayList<>();
            //Traemos todos los archivos de la aplicacion
            String[] archivo = context.fileList();
            for (int i = 0; i < archivo.length; i++) {
                if (archivo[i].equalsIgnoreCase("instant-run")) {
                    continue;
                }

                InputStreamReader objAbreArchivo = new InputStreamReader(context.openFileInput(archivo[i]));
                BufferedReader objBuffered = new BufferedReader(objAbreArchivo);

                String entrada = objBuffered.readLine();
                String fechaHora = objBuffered.readLine();
                String nombre = objBuffered.readLine();
                String comentario = objBuffered.readLine();
                String coordenada = objBuffered.readLine();

                //Verificamos el orden de los archivos
                arrayOrdenArchivo.add(entrada);

            }

            //recorremos el array para almacenar el numero de posiciones en contadorArchivoEntradas
            for (int i = 0; i < arrayOrdenArchivo.size(); i++) {
                contadorArchivoEntradas++;
            }

            //Creamos un archivo
            OutputStreamWriter objCreaArchivo = new OutputStreamWriter(context.openFileOutput("" + contadorArchivoEntradas, Activity.MODE_PRIVATE));
            //Escribimos un numero de entrada

            //Escribimos el orden del archivo
            objCreaArchivo.write("" + contadorArchivoEntradas + "\n");
            //Escribimos la fecha y la hora
            objCreaArchivo.write("" + fechaHora + "\n");
            //Escribimos el nombre del solicitante
            objCreaArchivo.write("" + nombre + "\n");
            //Escribimos los comentarios del solicitante
            objCreaArchivo.write("" + comentario + "\n");
            //Escribimos las coordenadas del solicitante
            objCreaArchivo.write("" + coordenada);
            //Limpiamos el archivo
            objCreaArchivo.flush();
            //Cerramos el archivo
            objCreaArchivo.close();
            Toast.makeText(context, "Solicitud hecha.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
