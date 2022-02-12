package com.example.dakirni.database.father;

import android.provider.BaseColumns;

public class FatherContrat {
    private FatherContrat() {
    }    // Classe Interne qui définit les données d’une table

    public static class FatherTable implements BaseColumns {
        public static final String TABLE_NAME = "Father";
        public static final String COLUMN_NAME_TOKEN = "token_father";
        public static final String COLUMN_NAME_FATHER_ID = "father_id";
        public static final String COLUMN_NAME_FATHER_NAME = "name";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_RELATION = "relation";

    }
}
