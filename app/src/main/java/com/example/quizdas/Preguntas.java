package com.example.quizdas;

import android.provider.BaseColumns;

public class Preguntas {

    /**
     * Esquema de la tabla preguntas
     */

    public static abstract class PreguntasEntry implements BaseColumns {
        public static final String TABLE_NAME ="preguntas";

        public static final String IDPREGUNTA = "idPregunta";
        public static final String CATPREGUNTA = "catPregunta";
        public static final String TEXTPREGUNTA = "textPregunta";
        public static final String RESPUESTA1 = "respuesta1";
        public static final String RESPUESTA2 = "respuesta2";
        public static final String RESPUESTA3 = "respuesta3";
    }
}
