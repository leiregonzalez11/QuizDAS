package com.example.quizdas;

import android.provider.BaseColumns;

public class Categoria {

    /**
     * Esquema de la tabla categoria
     */

    public static abstract class PreguntasEntry implements BaseColumns {
        public static final String TABLE_NAME ="categoria";

        public static final String IDCAT = "idCat";
        public static final String CATEGORIA = "categoria";
        public static final String DESCR = "descr";

    }
}
