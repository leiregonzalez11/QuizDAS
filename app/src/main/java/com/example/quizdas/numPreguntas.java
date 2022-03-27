package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class numPreguntas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_preguntas);

    }

    public void jugar (View view){

        TextView textnumPreg = findViewById(R.id.textnumPreg);
        String numP = textnumPreg.getText().toString();

        if ( numP.equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.numpregvacio), Toast.LENGTH_SHORT).show();
            textnumPreg.setText("");
        }
        else{
            int numPreg = Integer.parseInt(textnumPreg.getText().toString());
            if (numPreg < 1 || numPreg>20){
                Toast.makeText(getApplicationContext(), getString(R.string.minmaxpreg), Toast.LENGTH_SHORT).show();
                textnumPreg.setText("");
            }else{
                Log.d("Jugar", "jugarrrrrrrrrrrrrrrrr");
                /*Recuperación de datos de la actividad anterior*/
                Bundle datos = this.getIntent().getExtras();
                String categoria = datos.getString("categoria");
                Log.d("Datos: ", categoria + " " + numPreg );
                Intent intent = new Intent(this, Jugar.class);
                intent.putExtra("categoria",categoria);
                intent.putExtra("numPreguntas",numPreg);
                startActivity(intent);
            }
        }
    }
}