package com.example.amda8.practica7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.PrivateKey;

public class Mostrar_inventario extends AppCompatActivity {

    private TextView tMostrar_inventario;

    String Lista=" Id - " + "Nombre  -  " + "Cantidad - " + "Precio " + "\n";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_inventario);

        tMostrar_inventario = (TextView)findViewById(R.id.tInventario);

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Peluches", null);

        while (c.moveToNext())
        {
            Lista+="  "+c.getString(0);
            Lista+="  -  "+c.getString(1);
            Lista+="  -  "+c.getString(2);
            Lista+="  -  "+c.getString(3);
            Lista+="\n";
        }

        tMostrar_inventario.setText(Lista);


    }

    public void atras3(View view) {

        finish();
    }


}
