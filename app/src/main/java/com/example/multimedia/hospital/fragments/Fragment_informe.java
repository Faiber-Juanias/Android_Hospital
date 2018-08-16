package com.example.multimedia.hospital.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.multimedia.hospital.Adaptadores.AdapterList;
import com.example.multimedia.hospital.Adaptadores.Datos;
import com.example.multimedia.hospital.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_informe.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_informe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_informe extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //-----------------------------------------
    RecyclerView recyclerView;
    ArrayList<Datos> arrayList;
    Context context;

    private OnFragmentInteractionListener mListener;

    public Fragment_informe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_informe.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_informe newInstance(String param1, String param2) {
        Fragment_informe fragment = new Fragment_informe();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_informe, container, false);

        //Creo las referencias necesarias
        recyclerView = (RecyclerView) vista.findViewById(R.id.recycler_lista);

        llenaRecycler();

        return vista;
    }

    public void llenaRecycler(){
        try{
            arrayList = new ArrayList<>();
            //Obtenemos un array con los archivos internos del dispositivo
            String[] archivo = context.fileList();
            //Recorremos el array
            for (int i = 0; i < archivo.length; i++) {
                if (archivo[i].equalsIgnoreCase("instant-run")) {
                    continue;
                }

                //Abrimos el archivo a leer
                InputStreamReader objAbreArchivo = new InputStreamReader(context.openFileInput(archivo[i]));
                //Leemos el archivo
                BufferedReader objBuffered = new BufferedReader(objAbreArchivo);

                //Guardamos todos los datos del archivo
                String entrada = objBuffered.readLine();
                String fechaHora = objBuffered.readLine();
                String nombre = objBuffered.readLine();
                String comentario = objBuffered.readLine();
                String coordenada = objBuffered.readLine();

                //Insertamos los datos dentro de una instancia del arrayList
                arrayList.add(new Datos(fechaHora, nombre, comentario, coordenada));
            }
            //valido que el arrayList no este vacio
            if (arrayList.size() != 0){
                //Creo una instancia de AdapterList
                AdapterList objAdapter = new AdapterList(context, arrayList);
                //Asigno el adaptador al reclycler
                recyclerView.setAdapter(objAdapter);
            }else {
                Toast.makeText(context, "No hay nada para mostrar.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
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
