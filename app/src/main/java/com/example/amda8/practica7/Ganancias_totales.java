package com.example.amda8.practica7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Ganancias_totales extends AppCompatActivity {


    private TextView ganancia;
    String encabezado="(" + " Nombre - " + "Cantidad - " + "Valor Total - " + "Ganancia Total - " + "Cantidad Total "+ ")"+"\n";

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganancias_totales);

        ganancia = (TextView)findViewById(R.id.ganancias_totales);

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();

        Cursor v = db.rawQuery("SELECT * FROM Ventas", null);

        while (v.moveToNext())
        {
            encabezado+="  "+v.getString(0);
            encabezado+=" - "+v.getString(1);
            encabezado+=" - "+v.getString(2);
            encabezado+=" - "+v.getString(3);
            encabezado+=" - "+v.getString(4);
            encabezado+="\n";
        }

        ganancia.setText(encabezado);


    }

    public void borrar(View view) {

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        db.delete("Ventas", null, null);
        db.close();
        encabezado= "No Existen Datos En la Tabla";
        ganancia.setText(encabezado);
        Toast.makeText(this, "Tabla De Ventas Borrada", Toast.LENGTH_SHORT).show();
    }

    public void atras9(View view) {

        finish();
    }
}
