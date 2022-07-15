package com.example.parcilafinalappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcilafinalappv2.db.DbUnicartagena;

public class AddActivity extends AppCompatActivity {

    EditText nombrePrograma;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAgregar = findViewById(R.id.btnAgregar);


        btnAgregar.setOnClickListener(view -> {
           agregarPrograma();
        });


    }

    public void agregarPrograma() {
        nombrePrograma = findViewById(R.id.txtNombre);
        DbUnicartagena db = new DbUnicartagena(this);

        long id = db.insertarPrograma(nombrePrograma.getText().toString());

        if (id > 0) {
            Toast.makeText(this, "registro guardado con exito", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "error al gaurdar registro", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }

    }

    public void limpiarCampos() {
        nombrePrograma.setText("");
    }
}