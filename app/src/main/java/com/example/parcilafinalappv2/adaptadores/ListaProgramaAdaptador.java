package com.example.parcilafinalappv2.adaptadores;

import static com.example.parcilafinalappv2.R.drawable.ic_baseline_school_24;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.entidades.Programa;

import java.util.ArrayList;


public class ListaProgramaAdaptador extends RecyclerView.Adapter<ListaProgramaAdaptador.ProgramaViewHolder> {

    ArrayList<Programa> listaProgramas;



    public ListaProgramaAdaptador(ArrayList<Programa> listaProgramas) {
        this.listaProgramas = listaProgramas;

    }

    @NonNull
    @Override
    public ProgramaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_programa, null, false);
        return new ProgramaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramaViewHolder holder, int i) {

        holder.textViewNombre.setText(listaProgramas.get(i).getNombre());
        holder.imagen.setImageResource(ic_baseline_school_24);

    }


    @Override
    public int getItemCount() {
        return listaProgramas.size();
    }


    public class ProgramaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre;
        ImageView imagen;

        public ProgramaViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            imagen = itemView.findViewById(R.id.imagen);

        }
    }
}

