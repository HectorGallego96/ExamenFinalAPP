/**
 * Clase correspondiente a el manejo de la base datos y los distintos metodos
 * para interectuar con ella: insertar registro, modificar registro, obtener registro
 * eliminar registro
 *
 * */

package com.example.parcilafinalappv2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.parcilafinalappv2.entidades.Programa;

import java.util.ArrayList;

public class DbController extends DbHelper {

    // constructor donde se instancia el contexto que se recibe por parametro
    Context context;
    public DbController(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    // metodo para inserter un registro en la base de datos
    // retorna el id del registro que se inserto
    public long insertarPrograma(String nombre, int duracion, String modalidad, String facultad){

        long id = 0;
        try{

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            // se agregan los valores recibidos por parametro y se realiza la inserción en la
            // base de datos
            values.put("nombre", nombre );
            values.put("duracion", duracion );
            values.put("modalidad", modalidad);
            values.put("facultad", facultad);

            id = db.insert(TABLA_PROGRAMAS, null, values);

        }catch (Exception ex){
            ex.toString();
            Toast.makeText(context, "Ha ocurrido un error, intentelo de nuevo", Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    // metotodo para listar todos los registros en la base de datos
    // retorna un array con todos los registros de la base de datos
    public ArrayList<Programa> listarProgramas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Programa> listaProgramas = new ArrayList<>();
        Programa programa = null;
        Cursor cursorProgramas = null;

        cursorProgramas = db.rawQuery("SELECT * FROM "+ TABLA_PROGRAMAS, null);

        if(cursorProgramas.moveToFirst()){

            do{
                programa = new Programa(
                        cursorProgramas.getInt(0),
                        cursorProgramas.getString(1),
                        cursorProgramas.getInt(2),
                        cursorProgramas.getString(3),
                        cursorProgramas.getString(4));
                listaProgramas.add(programa);

            }while (cursorProgramas.moveToNext());

        }

        cursorProgramas.close();
        return listaProgramas;
    }

    // metodo para obtener un registro de la base de datos
    // retorna una instancia del programa encontrado
    public Programa getPrograma(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Programa programa = null;

        Cursor cursorProgramas;

        cursorProgramas = db.rawQuery("SELECT * FROM "+ TABLA_PROGRAMAS + " WHERE id = "+ id + " LIMIT 1", null);

        if(cursorProgramas.moveToFirst()){

            programa = new Programa(cursorProgramas.getInt(0),
                    cursorProgramas.getString(1),
                    cursorProgramas.getInt(2),
                    cursorProgramas.getString(3),
                    cursorProgramas.getString(4));

        }
        cursorProgramas.close();

        return programa;
    }

    // metodo pa editar un registro en la base de datos
    // retorna el estado de la operacion: exitosa:true fallida:false
    public boolean editarPrograma(int id, String nombre, int duracion, String modalidad, String facultad){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        boolean estado = false;

        try{
            db.execSQL(" UPDATE "+ TABLA_PROGRAMAS +
                    " SET nombre = '"+nombre+
                    "', duracion = '"+duracion +
                    "', modalidad = '"+modalidad+
                    "', facultad = '"+facultad+
                    "' WHERE id = '"+id+"'");
            estado = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }

        return estado;
    }

    // metodo para eliminar un registro de la base de datos
    // retorna el estado de la operacion: exitosa:true fallida:false
    public boolean eliminarProrama(int id){

        boolean estado = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL(" DELETE FROM "+ TABLA_PROGRAMAS + " WHERE id = '" + id + "'");
            estado = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }

        return estado;

    }
}
