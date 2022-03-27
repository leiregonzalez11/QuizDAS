package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Jugar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        Bundle datos = this.getIntent().getExtras();
        String categoria = datos.getString("categoria");
        int numPregs = datos.getInt("numPreguntas");
        GestorDB dbHelper = GestorDB.getInstance(this);
        Pregunta [] preguntas = dbHelper.obtenerPreguntas(categoria,numPregs);

        for (int i=0; i<preguntas.length;i++){
            TextView preg = findViewById(R.id.textPregunta);
            preg.setText(preguntas[i].getPregunta());
            Button resp1 = findViewById(R.id.buttonresp1);
            resp1.setText(preguntas[i].getResp1());
            Button resp2 = findViewById(R.id.buttonresp2);
            resp2.setText(preguntas[i].getResp2());
            Button resp3 = findViewById(R.id.buttonresp3);
            resp3.setText(preguntas[i].getResp3());
        }
    }
}