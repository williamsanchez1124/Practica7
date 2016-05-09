package com.example.amda8.practica7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void agregar_peluche (View v){
        Intent i=new Intent(this,Agregar.class);
        startActivity(i);
    }

    public void buscar_peluche (View v){
        Intent i=new Intent(this,Buscar.class);
        startActivity(i);
    }
    public void eliminar_peluche (View v){
        Intent i=new Intent(this,Eliminar.class);
        startActivity(i);
    }
    public void actualizar_peluche (View v){
        Intent i=new Intent(this,Actualizar.class);
        startActivity(i);
    }

    public void realizar_venta (View v){
        Intent i=new Intent(this,Realizar_venta.class);
        startActivity(i);
    }

    public void mostrar_Ganancias (View v){
        Intent i=new Intent(this,Ganancias_totales.class);
        startActivity(i);
    }
    public void mostrar_peluche (View v){
        Intent i=new Intent(this,Mostrar_inventario.class);
        startActivity(i);
    }
}
