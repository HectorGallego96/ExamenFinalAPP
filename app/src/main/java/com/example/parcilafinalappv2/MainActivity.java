package com.example.parcilafinalappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.parcilafinalappv2.adaptadores.ListaProgramaAdaptador;
import com.example.parcilafinalappv2.db.DbHelper;
import com.example.parcilafinalappv2.db.DbUnicartagena;
import com.example.parcilafinalappv2.entidades.Programa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaProgramas;
    ListaProgramaAdaptador adaptador;
    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        listaProgramas = findViewById(R.id.listaProgramas);
        listaProgramas.setLayoutManager(new LinearLayoutManager(this));

        DbUnicartagena dbUnicartagena = new DbUnicartagena(this);
        adaptador = new ListaProgramaAdaptador(dbUnicartagena.listarProgramas());

        System.out.println(adaptador);

        listaProgramas.setAdapter(adaptador);

        // inicio la actividad agregar programa
        btnAdd.setOnClickListener(view -> {
            nuevoPrograma();
        });
    }




    private void nuevoPrograma(){

        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);


    }
}