package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class elegirCategorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_categorias);

        GestorDB dbHelper = GestorDB.getInstance(this);
        String [] categorias = dbHelper.obtenerCategorias();

        ListView catView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.textRow, categorias);
        catView.setAdapter(adapter);

    }
}