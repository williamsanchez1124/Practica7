package com.example.amda8.practica7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {

    private EditText eId;
    private EditText enombre;
    private EditText eCantidad;
    private EditText ePrecio;
    private Button bGuardar;
    private TextView Ver;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        eId = (EditText) findViewById(R.id.eId);
        enombre = (EditText) findViewById(R.id.enombre);
        eCantidad = (EditText) findViewById(R.id.eCantidad);
        ePrecio = (EditText) findViewById(R.id.ePrecio);
        bGuardar = (Button) findViewById(R.id.bGuardando);
        Ver = (TextView) findViewById(R.id.tMuestra_agregar);



    }

    public void guardando(View view) {

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        String nombre = enombre.getText().toString();
        // String  identificacion= eId.getText().toString();
        String cantidad = eCantidad.getText().toString();
        String precio = ePrecio.getText().toString();

        ContentValues nuevoPeluche = new ContentValues();
        nuevoPeluche.put("nombre", nombre);
        //nuevoPeluche.put("identificacion", identificacion);
        nuevoPeluche.put("cantidad", cantidad);
        nuevoPeluche.put("precio", precio);
        db.insert("Peluches", null, nuevoPeluche);
        db.close();


        eId.setText("");
        eCantidad.setText("");
        ePrecio.setText("");
        ver_inventario();
        Toast.makeText(this, "Su Peluchito Fue Guardado Con Exito", Toast.LENGTH_SHORT).show();
    }

    protected void ver_inventario() {
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT nombre,cantidad,precio,id FROM Peluches where nombre='" + enombre.getText().toString() + "'", null);
        String nombre="",cantidad="",precio="";
        int id=0;
        Ver.setText("");
        if (c.moveToLast()) {
             nombre = c.getString(0);
            cantidad = c.getString(1);
            id = c.getInt(3);
             precio = c.getString(2);
            Toast.makeText(this, "Mostrando el peluche", Toast.LENGTH_SHORT).show();



        }

        Ver.setText("id:" + id + "\n" + "Nombre: " + nombre + "\n" + "cantidad: " + cantidad + "\n" +
                " Precio:" + precio + "\n");

        db.close();
        enombre.setText("");
    }

    public void atras2(View view) {

        finish();
    }
}
