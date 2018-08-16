package com.example.multimedia.hospital.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.AdapterListHolder adapterListHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdapterListHolder extends RecyclerView.ViewHolder {

        //Creo las referencias


        public AdapterListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
