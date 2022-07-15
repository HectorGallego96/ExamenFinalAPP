package com.example.parcilafinalappv2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parcilafinalappv2.entidades.Programa;

import java.util.ArrayList;

public class DbUnicartagena extends DbHelper {

    Context context;
    public DbUnicartagena(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarPrograma(String nombre){


        long id = 0;

        try{

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre );
            id = db.insert(TABLA_PROGRAMAS, null, values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Programa> listarProgramas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Programa> listaProgramas = new ArrayList<>();
        Programa programa = null;
        Cursor cursorProgramas = null;

        cursorProgramas = db.rawQuery("SELECT * FROM "+ TABLA_PROGRAMAS, null);

        if(cursorProgramas.moveToFirst()){
            do{
                programa = new Programa(cursorProgramas.getInt(0),cursorProgramas.getString(1));
                listaProgramas.add(programa);

            }while (cursorProgramas.moveToNext());

        }
        cursorProgramas.close();

        return listaProgramas;
    }

}
