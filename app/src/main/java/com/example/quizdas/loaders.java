package com.example.quizdas;


import android.app.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class loaders {

    /*
    * Método utilizado para cargar las tablas en la db
    * */
    public void crearTablas(SQLiteDatabase sqLiteDatabase){

        //Esquema de la tabla Usuario
        String query1 = "CREATE TABLE IF NOT EXISTS Usuario (" +
                "nombre VARCHAR(30) NOT NULL, " +
                "dni VARCHAR(9) NOT NULL PRIMARY KEY," +
                "tel INT(9)," +
                "email VARCHAR NOT NULL," +
                "password VARCHAR(15) NOT NULL);";
        Log.d("Tabla Usuario", query1);
        sqLiteDatabase.execSQL(query1);

        //Esquema de la tabla Categoria
        String query2 = "CREATE TABLE IF NOT EXISTS Categoria (idCat INT NOT NULL PRIMARY KEY, "+
                "categoria VARCHAR(15) NOT NULL)";
        Log.d("Tabla Categoria", query2);
        sqLiteDatabase.execSQL(query2);

        //Esquema de la tabla Usuario
        String query3 = "CREATE TABLE IF NOT EXISTS Preguntas (idPreg INT NOT NULL PRIMARY KEY, "+
                "pregunta VARCHAR NOT NULL, " +
                "respuesta1 VARCHAR NOT NULL, " +
                "respuesta2 VARCHAR NOT NULL," +
                "respuesta3 VARCHAR NOT NULL," +
                "respCorrecta VARCHAR NOT NULL," +
                "catPegunta INT NOT NULL, " +
                "FOREIGN KEY (catPegunta) REFERENCES Categoria(idCat));";

        Log.d("Tabla Preguntas", query3);
        sqLiteDatabase.execSQL(query3);

    }

    public void insertarCategorias(SQLiteDatabase sqLiteDatabase){
        String query = "INSERT INTO Categoria VALUES (1, 'Deportes'),(2, 'Entretenimiento'),(3, 'Historia'),(4, 'Geografia'),(5, 'Ciencias'), " +
                "(6, 'Lengua y Arte'),(7, 'Matematicas'),(8, 'Musica'),(9, 'Gastronomia'),(10, 'Moda'),(11, 'Tecnología');";
        sqLiteDatabase.execSQL(query);
    }

    public void insertarPreguntas(SQLiteDatabase sqLiteDatabase, Context context) throws IOException {

        //Carga de datos desde un archivo .txt usando res/raw
        InputStream file = context.getResources().openRawResource(R.raw.preguntas);
        BufferedReader buffer = new BufferedReader((new InputStreamReader(file)));
        boolean seguir = true;

        while (seguir){
            try{
                String query = buffer.readLine();
                Log.d("Query: ", query);
                sqLiteDatabase.execSQL(query);
            }catch (Exception e){
                e.printStackTrace();
                seguir = false;
                buffer.close();
            }
        }

    }




    public void insertarUsuario(SQLiteDatabase sqLiteDatabase){}

    public void buscarUsuario(String dni){}


}
