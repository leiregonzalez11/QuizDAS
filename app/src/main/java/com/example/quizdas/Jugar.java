package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Jugar extends AppCompatActivity {

    private boolean correcta = true;
    private int numPregCorrectas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        Bundle datos = this.getIntent().getExtras();
        String categoria = datos.getString("categoria");
        int numPregs = datos.getInt("numPreguntas");
        GestorDB dbHelper = GestorDB.getInstance(this);
        Pregunta [] preguntas = dbHelper.obtenerPreguntas(categoria,numPregs);

        jugar(preguntas);

    }

    public void jugar (Pregunta[] preguntas){



        int respCorrectas = 0;

        for (int i=0; i<preguntas.length;i++) {

            int numPreg = i;

            TextView preg = findViewById(R.id.textPregunta);
            Button resp1 = findViewById(R.id.buttonresp1);
            Button resp2 = findViewById(R.id.buttonresp2);
            Button resp3 = findViewById(R.id.buttonresp3);

            //Seteamos las preguntas en la interfaz

            preg.setText(preguntas[i].getPregunta());
            resp1.setText(preguntas[i].getResp1());
            resp2.setText(preguntas[i].getResp2());
            resp3.setText(preguntas[i].getResp3());

            //Añadimos el listeners a cada botón

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    correcta = validarRespuestas(preguntas[numPreg], view);
                    if (correcta){
                        numPregCorrectas++;
                        DialogFragment correctoAlert = new CorrectDialogFragment();
                        correctoAlert.show(getSupportFragmentManager(),"correcto_dialog");
                    }
                    onResume();
                }
            };

            resp1.setOnClickListener(onClickListener);
            resp2.setOnClickListener(onClickListener);
            resp3.setOnClickListener(onClickListener);

        }

    }

    public boolean validarRespuestas(Pregunta pregunta, View view){

        Button bresp1 = findViewById(R.id.buttonresp1);
        Button bresp2 = findViewById(R.id.buttonresp2);
        Button bresp3 = findViewById(R.id.buttonresp3);
        boolean correcta = true;

        if (bresp1.isPressed()){
            if (pregunta.getResp1().equals(pregunta.getRespCorrecta())){
                Log.d("Respuesta:", " ¡Respuesta 1 correcta!" );
            }else{
                Log.d("Respuesta:", " ¡Respuesta 1 incorrecta!" );
                correcta = false;
            }
        } else if (bresp2.isPressed()){
            if (pregunta.getResp2().equals(pregunta.getRespCorrecta())){
                Log.d("Respuesta:", " ¡Respuesta 2 correcta!" );
            }else{
                Log.d("Respuesta:", " ¡Respuesta 2 incorrecta!" );
                correcta = false;
            }
        }else{
            if (pregunta.getResp3().equals(pregunta.getRespCorrecta())){
                Log.d("Respuesta:", " ¡Respuesta 3 correcta!" );
            }else{
                Log.d("Respuesta:", " ¡Respuesta 3 incorrecta!" );
                correcta = false;
            }
        }

        return correcta;
    }
}