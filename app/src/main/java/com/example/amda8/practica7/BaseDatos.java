package com.example.amda8.practica7;

/**
 * Created by AMD A8 on 07/05/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by willo on 02/05/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {


    //Sentencia SQL Para crear una tabla
    String sqlCreate = "CREATE TABLE Peluches(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad TEXT,precio INTEGER)";
    String sqlCreate2 = "CREATE TABLE Ventas(nombre TEXT, cantidad TEXT, Valor INTEGER, acomulado INTEGER, cantidad_total INTEGER)";

    public BaseDatos(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Peluches");
        db.execSQL("DROP TABLE IF EXISTS Ventas");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
    }
}