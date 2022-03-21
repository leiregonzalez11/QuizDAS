package com.example.quizdas;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class loaders {

    /*
    * Método utilizado para cargar las tablas en la db
    * */
    public void crearTablas(SQLiteDatabase sqLiteDatabase){

        //Esquema de la tabla Usuario
        String query1 = "CREATE TABLE IF NOT EXISTS Usuario (" +
                "nombre VARCHAR(30) NOT NULL, " +
                "apellidos VARCHAR(100)NOT NULL, " +
                "fechaNac DATE NOT NULL," +
                "dni VARCHAR(9) NOT NULL PRIMARY KEY," +
                "tel INT(9)," +
                "password VARCHAR(15) NOT NULL" +
                ")";
        Log.d("Tabla Usuario", query1);
        sqLiteDatabase.execSQL(query1);

        //Esquema de la tabla Categoria
        String query2 = "CREATE TABLE IF NOT EXISTS Categoria (idCat INT NOT NULL PRIMARY KEY, "+
                "categoria VARCHAR(15) NOT NULL, " +
                "descr VARCHAR(100) NOT NULL)";
        Log.d("Tabla Categoria", query2);
        sqLiteDatabase.execSQL(query2);

        //Esquema de la tabla Usuario
        String query3 = "CREATE TABLE IF NOT EXISTS Preguntas (idPreg INT NOT NULL PRIMARY KEY, "+
                "pregunta VARCHAR NOT NULL, " +
                "respuesta1 VARCHAR NOT NULL, " +
                "respuesta2 DATE NOT NULL," +
                "respuesta3 VARCHAR NOT NULL," +
                "respCorrecta VARCHAR NOT NULL," +
                "catPegunta INT NOT NULL, " +
                "FOREIGN KEY (catPegunta) REFERENCES Categoria(idCat));";

        Log.d("Tabla Preguntas", query3);
        sqLiteDatabase.execSQL(query3);

    }

    /*public void insertarCategorias(SQLiteDatabase sqLiteDatabase){}

    public void insertarPreguntas(SQLiteDatabase sqLiteDatabase){}

    public void insertarUsuario(SQLiteDatabase sqLiteDatabase){}

    public void buscarUsuario(SQLiteDatabase sqLiteDatabase){}*/

}
s