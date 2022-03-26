package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        /*Cargamos la BD...*/
        GestorDB.getInstance(this);
    }

    /** Called when the user taps the Iniciar Sesión button */
    public void iniciarSesion(View view) {
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);

    }

    /** Called when the user taps the registrase button */
    public void registrase(View view) {
        Intent intent = new Intent(this, Registrarse.class);
        startActivity(intent);

    }

    /** Called when the user taps the Salir button */
    public void salir(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}