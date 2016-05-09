package com.example.amda8.practica7;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Realizar_venta extends AppCompatActivity {

    String nombre = "";
    private EditText Nombre, Cantidad;
    private Button Comprar;
    private TextView Precio, Precio_total;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_venta);

        Nombre = (EditText)findViewById(R.id.eNombre_vendido);
        Cantidad = (EditText)findViewById(R.id.eCantidad_vendida);
        Precio = (TextView)findViewById(R.id.tPrecio);
        Precio_total = (TextView)findViewById(R.id.tPrecio_total);

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();

    }

    public void vender (View view){

        nombre = Nombre.getText().toString();
        String cantidad = Cantidad.getText().toString();

        Toast.makeText(this, "Buscando Peluchito a vender", Toast.LENGTH_SHORT).show();

        ver_inventario(nombre, cantidad);

        Nombre.setText("");
        Cantidad.setText("");


    }

    protected void ver_inventario(String nombre, String cantidad) {
        int Precios=0,total=0,canti=0, cant=0, Nueva_cantidad=0, acomulado=0,  cantidad1=0;
        String  Cant, Total, nueva_cantidad;

        BaseDatos Peluches = new BaseDatos(this, "DBPeluches", null, 2);
        db = Peluches.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT nombre,cantidad,precio FROM Peluches where nombre='" + nombre + "'", null);




if(c.moveToFirst()) {
    Cant = c.getString(1);
    Precios=c.getInt(2);
    canti = Integer.parseInt(cantidad);
    cant = Integer.parseInt(Cant);

    if(cant >=canti) {
        total = canti * Precios;
        Total=String.valueOf(total);
        Precio_total.setText(Total);
        Precio.setText(String.valueOf(Precios));
        Nueva_cantidad = cant-canti;
        nueva_cantidad=String.valueOf(Nueva_cantidad);
        db.execSQL("UPDATE Peluches SET cantidad='" + nueva_cantidad + "' WHERE nombre='" + this.nombre + "'");

        Cursor v = db.rawQuery("SELECT acomulado,cantidad FROM Ventas", null);

        if(v.moveToLast()) {
            acomulado =v.getInt(0);
            cantidad1 = v.getInt(1);
        }

        acomulado+=total;
        cantidad1 +=canti;


        db.execSQL("INSERT INTO Ventas (valor,nombre,cantidad, acomulado, cantidad_total) VALUES ('" + Total + "','" + nombre + "','" + cantidad + "','" + acomulado + "','" + cantidad1 + "') ");



        Toast.makeText(this, "Venta Exitosa", Toast.LENGTH_SHORT).show();

        if(Nueva_cantidad<= 5){

            hacer_notificacion(this.nombre, Nueva_cantidad);
        }


    }else {Toast.makeText(this, "No Existe La Cantidad Pedida En El Momento", Toast.LENGTH_SHORT).show();}
} else {Toast.makeText(this, "No Existe El Peluchito Buscado En EL Momento", Toast.LENGTH_SHORT).show();}




    }

    public void hacer_notificacion(String nombre, int Nueva_cantidad) {


        NotificationManager william = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("Warning").setContentText("Se esta agotando el peluche: "+nombre)
                .setTicker(String.valueOf(Nueva_cantidad)).setSmallIcon(R.drawable.alerta);

        Intent i = new Intent(Realizar_venta.this, Notificacion.class);
        i.putExtra("Peluche", nombre);
        i.putExtra("Cantidad", String.valueOf(Nueva_cantidad));
        PendingIntent sanchez = PendingIntent.getActivity(Realizar_venta.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(sanchez);
        william.notify(1234, builder.build());
    }

    public void back(View view) {

        finish();
    }
}
