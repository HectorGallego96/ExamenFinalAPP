
/**
 * Cñase correspondiente a la actividad principal
 * donde se carga la lista de todos los registros guardados en la base de datos
 * y se accede al actividad de agregar contacto
 * */
package com.example.parcilafinalappv2.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.parcilafinalappv2.Actividades.AddActivity;
import com.example.parcilafinalappv2.R;
import com.example.parcilafinalappv2.adaptadores.ListaProgramaAdaptador;
import com.example.parcilafinalappv2.db.DbController;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaProgramas;
    ListaProgramaAdaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaProgramas = findViewById(R.id.listaProgramas);
        listaProgramas.setLayoutManager(new LinearLayoutManager(this));

        DbController dbController = new DbController(this);

        adaptador = new ListaProgramaAdaptador(dbController.listarProgramas());


        listaProgramas.setAdapter(adaptador);

    }

    // recargo la lista al momento de regresar a la actividad
    @Override
    protected void onResume() {
        super.onResume();
        listaProgramas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    // evento al item del menu de la barra de aplicaciones
    // el cual inicia la actividad de agregar Programa
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.itemAgregar:
                nuevoPrograma();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // metodo para iniciar un la actividad AddActivity correspondiente
    // a agregar un nuevo programa al sistema
    private void nuevoPrograma(){

        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
}