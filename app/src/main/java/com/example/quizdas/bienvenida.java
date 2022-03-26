package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        GestorDB dbHelper = GestorDB.getInstance(this);
        TextView textUser = findViewById(R.id.textBienvenida);

        /*Recuperación de datos de la actividad anterior*/
        Bundle datos = this.getIntent().getExtras();
        String email = datos.getString("email");

        String nameUser = dbHelper.obtenerNombreUser(email);
        textUser.setText("¡Bienvenid@ " + nameUser + "!");


    }

    /*public void aleatorio(View view){
        Intent intent = new Intent(this, numpreg.class);
        startActivity(intent);
        finish();
    }*/

    public void categorias(View view){
        Intent intent = new Intent(this, elegirCategorias.class);
        startActivity(intent);
    }

    public void cerrarSesion(View view){
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
        finish();
    }
}