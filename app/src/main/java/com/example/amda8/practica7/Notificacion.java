package com.example.amda8.practica7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notificacion extends AppCompatActivity {

    private TextView alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        alerta = (TextView)findViewById(R.id.alerta);
        Bundle extras = getIntent().getExtras();
        alerta.setText("Quedan solamente: " +extras.getString("Cantidad") +" Peluchitos "  +"\n" +" Del Peluchito: " +extras.getString("Peluche") );

    }

    public void OK (View view){

        finish();
    }
}
