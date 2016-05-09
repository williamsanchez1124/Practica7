package com.example.amda8.practica7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {

    private EditText enombre;
    private Button bEliminando;
    String nombre="";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        enombre= (EditText) findViewById(R.id.enombre_eliminar);
        bEliminando= (Button) findViewById(R.id.bEliminando);
        nombre = enombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches",null, 2);
        db= Peluches.getWritableDatabase();

    }

    public void eliminando(View view) {


        Toast.makeText(this, "Buscando Su Peluchito", Toast.LENGTH_SHORT).show();

        ver_inventario(nombre);
    }

    protected void ver_inventario(String  nombre) {
            this.nombre = enombre.getText().toString();
        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        db.execSQL("DELETE FROM Peluches WHERE nombre='"+this.nombre+"'");
        Toast.makeText(this, this.nombre, Toast.LENGTH_SHORT).show();
            enombre.setText("");
            db.close();
            Toast.makeText(this, "Se Eliminó El Peluche De Peluchitos.com", Toast.LENGTH_SHORT).show();

    }

    public void atras5(View view) {

        finish();
    }
}
