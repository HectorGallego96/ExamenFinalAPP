/**
 * Actividad correspondiente a la edicion de un registro a la app
 * */
package com.example.parcilafinalappv2.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.db.DbController;
import com.example.parcilafinalappv2.entidades.Programa;

public class EditActivity extends AppCompatActivity {


    Programa programa;
    EditText nombrePrograma;
    EditText duracionPrograma;
    EditText modalidadPrograma;
    EditText facultadPrograma;
    Button btnActualizar;


    boolean estado = false;
    int id =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        findViewById(R.id.fabEditar).setVisibility(View.INVISIBLE);
        findViewById(R.id.fabEliminar).setVisibility(View.INVISIBLE);

        btnActualizar = findViewById(R.id.btnActualizar);


        nombrePrograma = findViewById(R.id.txtNombre);
        duracionPrograma = findViewById(R.id.txtDuracion);
        modalidadPrograma = findViewById(R.id.txtModalidad);
        facultadPrograma = findViewById(R.id.txtFacultad);

        // verifico y asigno el parametro que se paso en el Extras
        // el cual corresponde al id del registro a editar
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbController dbController = new DbController(this);

        // obtengo los datos del programa a editar
        programa = dbController.getPrograma(id);


        // si la instancia es correcta cargo los datos en los campos correspondientes
        if(programa != null){
            nombrePrograma.setText(programa.getNombre());
            duracionPrograma.setText(String.valueOf(programa.getDuracion()));
            modalidadPrograma.setText(programa.getModalidad());
            facultadPrograma.setText(programa.getFacultad());
        }
        else{
            Toast.makeText(this, "Lo sentimos no se encontro el programa buscado", Toast.LENGTH_SHORT).show();
        }

        // evento actualizar el cual permite realizar la edicion de un registro en la base de datos
        btnActualizar.setOnClickListener(view -> {

            // valido que los campos esten llenos
            if(validarCampos()){

                String nombre = nombrePrograma.getText().toString();
                int duracion = Integer.parseInt(duracionPrograma.getText().toString());
                String modalidad = modalidadPrograma.getText().toString();
                String facultad = facultadPrograma.getText().toString();

                // edito el programa llamando al metodo editar programa
                // y pasandole los nuevos valores
                estado = dbController.editarPrograma(id,nombre,duracion,modalidad,facultad);

                // verifico el estado de la peracion
                if(estado){
                    Toast.makeText(EditActivity.this, "Programa Editado Exitosamente", Toast.LENGTH_SHORT).show();
                    verRegistro();
                }else{
                    Toast.makeText(EditActivity.this, "Error al editar el programa", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(EditActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // metodo para regresar a la Actividad ViewActivity
    private void  verRegistro(){
        Intent intent = new Intent(this, ViewActivity.class );
        intent.putExtra("ID",id);
        startActivity(intent);
    }

    // metdodo para verificar que los campos a editar no esten vacios
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
