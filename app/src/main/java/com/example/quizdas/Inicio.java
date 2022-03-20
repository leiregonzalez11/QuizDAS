package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    /** Called when the user taps the Entrar button */
    public void iniciarSesion(View view) {
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);

    }

    /** Called when the user taps the Entrar button */
    /*public void registrase(View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);

    }*/

    /** Called when the user taps the Entrar button */
    public void salir(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}