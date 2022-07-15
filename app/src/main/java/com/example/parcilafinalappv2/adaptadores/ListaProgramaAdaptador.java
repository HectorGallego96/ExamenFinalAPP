/**
 * Clase correspondiente al Adaptador de la clase RecicleView
 * que permite agregar y mostrar los registro en la RecyclerView
 * */

package com.example.parcilafinalappv2.adaptadores;

import static com.example.parcilafinalappv2.R.drawable.ic_baseline_school_24;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcilafinalappv2.Actividades.ViewActivity;
import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.entidades.Programa;

import java.util.ArrayList;


public class ListaProgramaAdaptador extends RecyclerView.Adapter<ListaProgramaAdaptador.ProgramaViewHolder> {

    ArrayList<Programa> listaProgramas;

    // constructor de la lista recibe la lista con los registro a insertar en el RecicleView
    public ListaProgramaAdaptador(ArrayList<Programa> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    @NonNull
    @Override
    public ProgramaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_programa, null, false);
        return new ProgramaViewHolder(view);
    }

    //metodo que para insertar los item en la lista
    @Override
    public void onBindViewHolder(@NonNull ProgramaViewHolder holder, int i) {

        holder.textViewNombre.setText(listaProgramas.get(i).getNombre());
        holder.textViewDuracion.setText("Duración: "+listaProgramas.get(i).getDuracion()+" Años");
        holder.textViewModalidad.setText("Modalidad: "+listaProgramas.get(i).getModalidad());
        holder.textViewFacultad.setText("Facultad: "+listaProgramas.get(i).getFacultad());
        holder.imagen.setImageResource(ic_baseline_school_24);

    }


    @Override
    public int getItemCount() {
        return listaProgramas.size();
    }


    // clase que me permite crear una instancia de cada elemento d el lista
    public class ProgramaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre;
        TextView textViewDuracion;
        TextView textViewModalidad;
        TextView textViewFacultad;
        ImageView imagen;

        public ProgramaViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDuracion = itemView.findViewById(R.id.textViewDuracion);
            textViewModalidad = itemView.findViewById(R.id.textViewModalidad);
            textViewFacultad = itemView.findViewById(R.id.textViewFacultad);
            imagen = itemView.findViewById(R.id.imagen);


            // evento para cuando se presiona un elemento de la lista cambiar a la actividad
            // ViewActivity
            itemView.setOnClickListener(view -> {

                Context context = view.getContext();
                Intent intent = new Intent(context, ViewActivity.class);
                //envio el id con del elemento seleccionado mediante el metodo put extra
                intent.putExtra("ID",listaProgramas.get(getAdapterPosition()).getId());
                context.startActivity(intent);

            });

        }
    }



}

