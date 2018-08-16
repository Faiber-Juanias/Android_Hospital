package com.example.multimedia.hospital.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.multimedia.hospital.R;
import com.example.multimedia.hospital.full_screen_dialog.FullScreenDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_solicitud.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_solicitud#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_solicitud extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //---------------------------------------------
    private EditText txtSolicitante;
    private EditText txtComentario;
    private EditText txtGps;
    private ImageButton btnSync;
    private Button btnEnviaSolicitud;

    private Context context;


    private OnFragmentInteractionListener mListener;

    public Fragment_solicitud() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_solicitud.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_solicitud newInstance(String param1, String param2) {
        Fragment_solicitud fragment = new Fragment_solicitud();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_solicitud, container, false);

        //Creamos las referencias
        txtSolicitante = (EditText) vista.findViewById(R.id.txt_nombre_solicitante);
        txtComentario = (EditText) vista.findViewById(R.id.txt_comentario);
        txtGps = (EditText) vista.findViewById(R.id.txt_gps);
        btnSync = (ImageButton) vista.findViewById(R.id.btn_sync);
        btnEnviaSolicitud = (Button) vista.findViewById(R.id.btn_envia_solicitud);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                //Almaceno en un objeto de tipo LocationManager al cual asignamos los servicios del sistema
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                //Validamos los permisos necesarios
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }else{
                    try{
                        assert locationManager != null;
                        Location location = new Location(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
                        String latitud = String.valueOf(location.getLatitude());
                        String longitud = String.valueOf(location.getLongitude());
                        txtGps.setText(latitud + " - " + longitud);
                        Toast.makeText(context, "Latitud - Longitud mostrados.", Toast.LENGTH_SHORT).show();
                    }catch (NullPointerException e){
                        e.getMessage();
                        e.getStackTrace();
                    }
                }
            }
        });

        btnEnviaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creo una instancia de FullScreenDialog
                FullScreenDialog objFullDialog = new FullScreenDialog();
                FragmentManager objManajer = getFragmentManager();

                //Creamos un Bundle para enviar los datos al FullScreenDialog
                Bundle bundle = new Bundle();
                bundle.putString("solicitante", txtSolicitante.getText().toString());
                bundle.putString("comentario", txtComentario.getText().toString());
                bundle.putString("coordenada", txtGps.getText().toString());

                //Enviamos el bundle al Dialogo
                objFullDialog.setArguments(bundle);

                assert objManajer != null;
                FragmentTransaction objTransaction = objManajer.beginTransaction();
                objTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                objTransaction.add(android.R.id.content, objFullDialog).addToBackStack(null).commit();
            }
        });

        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.context = context;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
