/**
 * Actividad correspondiente a la visualizacion detallada de un
 * programa en la app
 * */
package com.example.parcilafinalappv2.Actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.db.DbController;
import com.example.parcilafinalappv2.entidades.Programa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewActivity extends AppCompatActivity {

    FloatingActionButton fabEditar, fabEliminar;

    EditText txtNombre;
    EditText txtDuracion;
    EditText txtModalidad;
    EditText txtFacultad;
    Button btnActualizar;
    Programa programa;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnActualizar = findViewById(R.id.btnActualizar);

        txtNombre = findViewById(R.id.txtNombre);
        txtDuracion = findViewById(R.id.txtDuracion);
        txtModalidad = findViewById(R.id.txtModalidad);
        txtFacultad = findViewById(R.id.txtFacultad);

        // recupero el Extra enviado en el intent que corresponde al id
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbController dbController = new DbController(this);
        programa = dbController.getPrograma(id);


        // verifico que el programa se haya instanciado con exito
        if (programa != null) {

            txtNombre.setText(programa.getNombre());
            txtDuracion.setText(String.valueOf(programa.getDuracion()));
            txtFacultad.setText(programa.getFacultad());
            txtModalidad.setText(programa.getModalidad());

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDuracion.setInputType(InputType.TYPE_NULL);
            txtFacultad.setInputType(InputType.TYPE_NULL);
            txtModalidad.setInputType(InputType.TYPE_NULL);
            btnActualizar.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "Lo sentimos no se encontro el programa buscado", Toast.LENGTH_SHORT).show();
        }

        // evento para activar la Actividad EditActivity
        // envio como parametro el id del registro a editar con putExtra
        fabEditar.setOnClickListener(view -> {

            Intent intent = new Intent(ViewActivity.this, EditActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);

        });

        // evento que permite eliminar un programa de la app
        fabEliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);

            // activo un cuadro de dialogo
            builder.setMessage("Desea Eliminar este Programa?")

                    // de aceptar llamo al evento eliminar
                    // verifico el estado de la operacion
                    // y regreso a la actividad principal
                    .setPositiveButton("Eliminar", (dialogInterface, i) -> {

                        boolean estado = dbController.eliminarProrama(id);

                        if(estado){
                            Toast.makeText(this, "Programa Eliminado con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                        }

                    })

                    // de cancelar sigo con la ejecucion normal del programa
                    .setNegativeButton("Cancelar", (dialogInterface, i) -> {

                    }).show();
        });


    }


}