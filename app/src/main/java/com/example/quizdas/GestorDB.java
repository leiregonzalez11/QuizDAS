package com.example.quizdas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorDB extends SQLiteOpenHelper{

    private static GestorDB sInstance;
    private static Context context;
    private static final String DATABASE_NAME = "quizDB";
    private static final int DATABASE_VERSION = 1;


    /**
     * Constructor should be private to prevent direct instantiation.
     */
    private GestorDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    public static synchronized GestorDB getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            context.deleteDatabase("quizDB");
            sInstance = new GestorDB(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLitedatabase){
        crearTablas(sqLitedatabase);
        insertarCategorias(sqLitedatabase);
        try {
            insertarPreguntas(sqLitedatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void crearTablas(SQLiteDatabase sqLiteDatabase){

        //Esquema de la tabla Usuario
        String query1 = "CREATE TABLE IF NOT EXISTS Usuario (idUser INTEGER NOT NULL PRIMARY KEY AutoIncrement," +
                "nombre VARCHAR(30) NOT NULL, " +
                "tel int(9)," +
                "email VARCHAR NOT NULL UNIQUE," +
                "password VARCHAR(15) NOT NULL);";
        Log.d("Tabla Usuario", query1);
        sqLiteDatabase.execSQL(query1);

        //Esquema de la tabla Categoria
        String query2 = "CREATE TABLE IF NOT EXISTS Categoria (idCat INT NOT NULL PRIMARY KEY, "+
                "categoria VARCHAR(15) NOT NULL)";
        Log.d("Tabla Categoria", query2);
        sqLiteDatabase.execSQL(query2);

        //Esquema de la tabla Usuario
        String query3 = "CREATE TABLE IF NOT EXISTS Preguntas (idPreg INTEGER NOT NULL PRIMARY KEY, "+
                "pregunta VARCHAR NOT NULL, " +
                "resp1 VARCHAR NOT NULL, " +
                "resp2 VARCHAR NOT NULL," +
                "resp3 VARCHAR NOT NULL," +
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

    public void insertarPreguntas(SQLiteDatabase sqLiteDatabase) throws IOException {

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
                seguir = false;
                buffer.close();
            }
        }

    }

    public void insertarUsuario(ContentValues values){
        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();
        sqLiteDatabase.insert("Usuario", null, values);
    }

    public boolean buscarUsuario(String email){
        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();

        boolean existe = true;
        Cursor c = sqLiteDatabase.rawQuery("SELECT email FROM Usuario WHERE email = \'" + email + "\';", null);
        if (!c.moveToNext()){
            existe = false;
        }
        return existe;
    }

    public boolean validarContraseña(String email, String passwd){
        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();
        boolean existe = true;
        Cursor c = sqLiteDatabase.rawQuery("SELECT password FROM Usuario WHERE email = \'" + email + "\';", null);
        while (c.moveToNext()){
            String password = c.getString(0);
            if (!passwd.equals(password))
                existe = false;
        }
        return existe;
    }

    public String obtenerNombreUser(String email){
        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();
        boolean existe = true;
        String name = "";
        Cursor c = sqLiteDatabase.rawQuery("SELECT nombre FROM Usuario WHERE email = \'" + email + "\';", null);
        while (c.moveToNext()){
            name = c.getString(0);

        }
        return name;
    }

    public String [] obtenerCategorias (){

        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();

        String nameCat = "";
        int i = 0;
        String [] categorias = new String[11];

        Cursor c = sqLiteDatabase.rawQuery("SELECT categoria FROM Categoria ORDER BY categoria ASC;", null);
        while (c.moveToNext()){
            nameCat = c.getString(0);
            categorias[i] = nameCat;
            i++;
        }
        return categorias;
    }

    public void obtenerPreguntas(String categoria, int numPreg){

        SQLiteDatabase sqLiteDatabase = sInstance.getWritableDatabase();

        //Obtenemos el número de preguntas total de la categoria seleccionadas
        int numPreguntasTotal = 0;
        Cursor c = sqLiteDatabase.rawQuery("SELECT COUNT(idPreg) FROM Preguntas WHERE catPregunta = \'"+ categoria + "\');", null);
        while (c.moveToNext()){
            numPreguntasTotal = c.getInt(0);
        }

        //Obtenemos los ids de las preguntas de la categoría

        int[] idPregs = new int[numPreguntasTotal];
        int i = 0;
        Cursor cu = sqLiteDatabase.rawQuery("SELECT idPreg FROM Preguntas WHERE catPregunta = \'"+ categoria + "\');", null);
        while (c.moveToNext()){
            int idPreg = c.getInt(0);
            idPregs[i] = idPreg;
            i++;
        }

        Log.d("Id Preguntas",idPregs.toString());



    }

}
