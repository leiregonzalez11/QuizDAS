package com.example.quizdas;

import android.provider.BaseColumns;

public class Usuario {

    /**
     * Esquema de la tabla usuario
     */

    public static abstract class PreguntasEntry implements BaseColumns {
        public static final String TABLE_NAME ="categoria";

        public static final String ID = "id";
        public static final String DNI = "dni";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDOS = "apellidos";
        public static final String FECHANAC = "fechaNac";
        public static final String TELEFONO = "telefono";
        public static final String EMAIL = "email";
        public static final String PASSWD = "passwd";

    }
}
