package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Jugar extends AppCompatActivity {

    private boolean correcta = true;
    private int numPregCorrectas = 0;
    private int pregActual = 0;
    private Pregunta [] preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        Bundle datos = this.getIntent().getExtras();
        String tipo = datos.getString("tipo");
        int numPregs = datos.getInt("numPreguntas");
        GestorDB dbHelper = GestorDB.getInstance(this);
        preguntas = new Pregunta[numPregs];
        if (tipo.equals("noAleatorio")){
            String categoria = datos.getString("categoria");
            preguntas = dbHelper.obtenerPreguntas(categoria,numPregs);
        } else {
            preguntas = dbHelper.obtenerPreguntasAleatorio(numPregs);
        }

        jugar();

    }

    public void jugar (){

        mostrarPreguntaActual();

        Button bresp1 = findViewById(R.id.buttonresp1);
        Button bresp2 = findViewById(R.id.buttonresp2);
        Button bresp3 = findViewById(R.id.buttonresp3);

        bresp1.setOnClickListener((view -> {
            bresp2.setEnabled(false);
            bresp3.setEnabled(false);
        }));

        bresp2.setOnClickListener((view -> {
            bresp1.setEnabled(false);
            bresp3.setEnabled(false);
        }));

        bresp3.setOnClickListener((view -> {
            bresp1.setEnabled(false);
            bresp2.setEnabled(false);
        }));

        Button buttonSig = findViewById(R.id.buttonSig);
        buttonSig.setOnClickListener((view -> {
            validarRespuestas();
        }));

    }

    //Actualizamos los datos de la View
    public void mostrarPreguntaActual(){

        TextView preg = findViewById(R.id.textPregunta);
        Button resp1 = findViewById(R.id.buttonresp1);
        Button resp2 = findViewById(R.id.buttonresp2);
        Button resp3 = findViewById(R.id.buttonresp3);

        resp1.setEnabled(true);
        resp2.setEnabled(true);
        resp3.setEnabled(true);

        preg.setText(preguntas[pregActual].getPregunta());
        resp1.setText(preguntas[pregActual].getResp1());
        resp2.setText(preguntas[pregActual].getResp2());
        resp3.setText(preguntas[pregActual].getResp3());
    }

    public void validarRespuestas(){

        Button bresp1 = findViewById(R.id.buttonresp1);
        Button bresp2 = findViewById(R.id.buttonresp2);
        Button bresp3 = findViewById(R.id.buttonresp3);
        boolean correcta = true;

        //Habilitamos los botones

        //Comprobamos el botón que ha sido pulsado y comprobamos si la respuesta es correcta
        if (bresp1.isPressed() || bresp2.isPressed() || bresp3.isPressed()){
            //bresp1.setClickable(false);
            //bresp2.setClickable(false);
            //bresp3.setClickable(false);
            if (bresp1.isPressed() && !preguntas[pregActual].getResp1().equals(preguntas[pregActual].getRespCorrecta())){
                correcta = false;
                Log.d("Pregunta", "respuesta 1 elegida");
            } else if (bresp2.isPressed() && !preguntas[pregActual].getResp2().equals(preguntas[pregActual].getRespCorrecta())){
                correcta = false;
                Log.d("Pregunta", "respuesta 2 elegida");
            }else if (bresp3.isPressed() && !preguntas[pregActual].getResp3().equals(preguntas[pregActual].getRespCorrecta())){
                correcta = false;
                Log.d("Pregunta", "respuesta 3 elegida");
            }
        }

        if (correcta) numPregCorrectas++; //Si la respuesta ha sido correcta la sumamos

        pregActual++;
        if (pregActual<preguntas.length-1) { //Mientras  haya preguntas que mostrar
            mostrarPreguntaActual();
        }
        if (pregActual==preguntas.length-1) { //Si es la última pregunta
            Button bSig = findViewById(R.id.buttonSig);
            bSig.setText("Terminar");
            mostrarPreguntaActual();
        }
        if (pregActual==preguntas.length) { //Si es la última pregunta
            Intent intent = new Intent(this, Terminar.class);
            intent.putExtra("pregsCorrectas", numPregCorrectas);
            intent.putExtra("totalPregs", preguntas.length);

        }

    }
}