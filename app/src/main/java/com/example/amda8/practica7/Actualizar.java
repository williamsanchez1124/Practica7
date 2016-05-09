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

public class Actualizar extends AppCompatActivity {

    private EditText enombre, Nueva_cantidad;
    private Button bActualizando, buscando,batras2,batras;
    private TextView NCantidad, tAvisos;
    String nombre="";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        enombre= (EditText) findViewById(R.id.enombre_actualizar);
        bActualizando= (Button) findViewById(R.id.bActualizando);
        batras= (Button) findViewById(R.id.batras);
        batras2= (Button) findViewById(R.id.batras2);
        nombre = enombre.getText().toString();
        Nueva_cantidad = (EditText) findViewById(R.id.eCantidad_actualizar);
        NCantidad = (TextView) findViewById(R.id.NCantidad);
        tAvisos = (TextView) findViewById(R.id.tAvisos);
        buscando= (Button) findViewById(R.id.bBuscando_peluche);


        BaseDatos Peluches = new BaseDatos(this, "DBPeluches",null, 2);
        db= Peluches.getWritableDatabase();
    }

    public void buscando_peluche(View view) {

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        nombre = enombre.getText().toString();
        Cursor c = db.rawQuery("SELECT nombre,cantidad FROM Peluches where nombre='"+nombre+"'", null);

        if (c.moveToFirst()) {
            enombre.setText(c.getString(0));
            Toast.makeText(this, "Mostrando el peluche", Toast.LENGTH_SHORT).show();

            db.close();
            tAvisos.setVisibility(View.VISIBLE);
            bActualizando.setVisibility(View.VISIBLE);
            NCantidad.setVisibility(View.VISIBLE);
            Nueva_cantidad.setVisibility(View.VISIBLE);
            buscando.setVisibility(View.INVISIBLE);
            batras2.setVisibility(View.INVISIBLE);
            batras.setVisibility(View.VISIBLE);
        }else
        { Toast.makeText(this, "No Existe Peluche en Peluchito.com", Toast.LENGTH_SHORT).show();}


    }
    public void actualizando(View view) {

        Toast.makeText(this, "Buscando Su Peluchito a Actualizar", Toast.LENGTH_SHORT).show();
        ver_inventario(nombre);
    }

    protected void ver_inventario(String  nombre) {
        this.nombre = enombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        db.execSQL("UPDATE Peluches SET cantidad='" + Nueva_cantidad.getText().toString() + "' WHERE nombre='" + this.nombre + "'");

        db.close();
        Toast.makeText(this, "Se Actualizó El Peluche De Peluchitos.com", Toast.LENGTH_SHORT).show();
        enombre.setText("");
        Nueva_cantidad.setText("");

    }
    public void atras1(View view) {

        finish();
    }
}
