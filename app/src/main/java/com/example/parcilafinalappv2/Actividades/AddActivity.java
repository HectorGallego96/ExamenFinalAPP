/**
 * Clase correspondiente a la actividad de agregar un nuevo programa a la app
 *
 * */
package com.example.parcilafinalappv2.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.db.DbController;

public class AddActivity extends AppCompatActivity {

    EditText nombrePrograma;
    EditText duracionPrograma;
    EditText modalidadPrograma;
    EditText facultadPrograma;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAgregar = findViewById(R.id.btnActualizar);

        // evento que agrega un nuevo programa a la app
        btnAgregar.setOnClickListener(view -> {
            agregarPrograma();
        });


    }

    // metodo on la logica para agregar un nuevo programa a la app
    public void agregarPrograma() {


        nombrePrograma = findViewById(R.id.txtNombre);
        duracionPrograma = findViewById(R.id.txtDuracion);
        modalidadPrograma = findViewById(R.id.txtModalidad);
        facultadPrograma = findViewById(R.id.txtFacultad);

        // verifico si los campos no estan vacios
        if(validarCampos()){

            DbController db = new DbController(this);

            String nombre = nombrePrograma.getText().toString();
            int duracion = Integer.parseInt(duracionPrograma.getText().toString());
            String modalidad = modalidadPrograma.getText().toString();
            String facultad = facultadPrograma.getText().toString();


            // llamo al metodo de insertar y le envio los parametros a insertar
            long id = db.insertarPrograma(nombre, duracion, modalidad, facultad);

            if (id > 0) {
                Toast.makeText(this, "registro guardado con exito", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "error al gaurdar registro", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }
        }else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }

    // metodo para limpiar los campos
    public void limpiarCampos() {
        nombrePrograma.setText("");
        duracionPrograma.setText("");
        facultadPrograma.setText("");
        modalidadPrograma.setText("");
    }

    // metodo para validar que ninguno de los campos esten vacios
    public boolean validarCampos() {
        boolean validacion = true;

        if (nombrePrograma.getText().toString().isEmpty() ||
                duracionPrograma.getText().toString().isEmpty() ||
                facultadPrograma.getText().toString().isEmpty() ||
                modalidadPrograma.getText().toString().isEmpty())
        {
            validacion = false;
        }

        return validacion;
    }

}