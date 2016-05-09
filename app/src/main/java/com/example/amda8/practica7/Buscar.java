package com.example.amda8.practica7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar extends AppCompatActivity {

    private EditText  enombre;
    private Button bBuscar;
    private TextView Tbuscar;



SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        enombre= (EditText) findViewById(R.id.enombre_buscar);
        bBuscar= (Button) findViewById(R.id.bBuscando);
        Tbuscar = (TextView)findViewById(R.id.Tbuscado);

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches",null, 2);
        db= Peluches.getWritableDatabase();

    }

    public void buscando(View view) {

        String nombre = enombre.getText().toString();

        Toast.makeText(this, "Buscando Su Peluchito", Toast.LENGTH_SHORT).show();

        ver_inventario(nombre);
    }

    protected void ver_inventario(String  nombre) {
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT nombre,cantidad,precio,id FROM Peluches where nombre='"+nombre+"'", null);
        String Nombre="",cantidad="",precio="";
        int id=0;
        Tbuscar.setText("");
        if (c.moveToFirst()) {
            Nombre = c.getString(0);
            cantidad = c.getString(1);
            id = c.getInt(3);
            precio = c.getString(2);
            Toast.makeText(this, "Mostrando el peluche", Toast.LENGTH_SHORT).show();

            Tbuscar.setText("id:" + id + "\n" + "Nombre: " + Nombre + "\n" + "cantidad: " + cantidad + "\n" +
                    " Precio:" + precio + "\n");
            db.close();
            enombre.setText("");
        }else{ Toast.makeText(this, "No Existe Peluche en Peluchito.com", Toast.LENGTH_SHORT).show();}


    }

    public void atras4(View view) {

        finish();
    }
}
