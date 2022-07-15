
/**
* clase correspondiente a la creacion de la base de datos y tablas de la base de datso
*
* */

package com.example.parcilafinalappv2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "unicartagena.db";
    public static final    String TABLA_PROGRAMAS = "programas";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    // metodo para la creacion de la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE  " + TABLA_PROGRAMAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "duracion INT NOT NULL," +
                "modalidad TEXT NOT NULL," +
                "facultad TEXT NOT NULL)");

    }

    // metodo para modificar la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE "+ TABLA_PROGRAMAS);
        onCreate(sqLiteDatabase);

    }


}
