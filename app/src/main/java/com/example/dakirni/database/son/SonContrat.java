package com.example.dakirni.database.son;

import android.provider.BaseColumns;

public class SonContrat {
    private SonContrat() {
    }    // Classe Interne qui définit les données d’une table

    public static class SonTable implements BaseColumns {
        public static final String TABLE_NAME = "Son";
        public static final String COLUMN_NAME_TOKEN = "token_son";
        public static final String COLUMN_NAME_SON_ID = "son_id";
        public static final String COLUMN_NAME_SON_NAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
