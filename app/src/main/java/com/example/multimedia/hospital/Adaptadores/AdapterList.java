package com.example.multimedia.hospital.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.multimedia.hospital.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterList extends RecyclerView.Adapter<AdapterList.AdapterListHolder> {

    private Context context;
    private ArrayList<Datos> arrayList;

    public AdapterList(Context context, ArrayList<Datos> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterList.AdapterListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_list, null, false);
        return new AdapterListHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.AdapterListHolder holder, int i) {
        holder.fechaLista.setText(arrayList.get(i).getFechaHora());
        holder.nombre.setText(arrayList.get(i).getNombre());
        holder.comentario.setText(arrayList.get(i).getComentario());
        holder.coordenada.setText(arrayList.get(i).getcoordenada());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AdapterListHolder extends RecyclerView.ViewHolder {

        //Creo las referencias
        TextView fechaLista;
        TextView nombre;
        TextView comentario;
        TextView coordenada;

        public AdapterListHolder(@NonNull View itemView) {
            super(itemView);

            //Creo las referencias con la interfaz
            fechaLista = (TextView) itemView.findViewById(R.id.view_fecha_lista);
            nombre = (TextView) itemView.findViewById(R.id.view_nombre_lista);
            comentario = (TextView) itemView.findViewById(R.id.view_comentario_lista);
            coordenada = (TextView) itemView.findViewById(R.id.view_comentario_lista);
        }
    }
}
