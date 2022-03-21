package com.example.quizdas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorDB extends SQLiteOpenHelper{

    private static GestorDB sInstance;
    private static final String DATABASE_NAME = "quizDB";
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor should be private to prevent direct instantiation.
     */
    private GestorDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

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
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        loaders loader = new loaders();
        loader.crearTablas(sqLiteDatabase);
        loader.insertarCategorias(sqLiteDatabase);
        //loader.insertarPreguntas(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
