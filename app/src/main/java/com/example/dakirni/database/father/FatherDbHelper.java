package com.example.dakirni.database.father;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dakirni.database.son.SonContrat;
import com.example.dakirni.database.son.SonDbHelper;

import java.util.ArrayList;

public class FatherDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dakirni.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + FatherContrat.FatherTable.TABLE_NAME + " (" +
                    FatherContrat.FatherTable._ID + " INTEGER PRIMARY KEY," +

                    FatherContrat.FatherTable.COLUMN_NAME_FATHER_ID + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_FATHER_NAME + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_KEY + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_AGE + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_RELATION + TEXT_TYPE + " )";
    private static final String SQL_CREATE_TABLE_SON =
            "CREATE TABLE " + SonContrat.SonTable.TABLE_NAME + " (" +
                    SonContrat.SonTable._ID + " INTEGER PRIMARY KEY," +
                    SonContrat.SonTable.COLUMN_NAME_TOKEN + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_SON_ID + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_SON_NAME + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_PASSWORD + TEXT_TYPE + " )";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + FatherContrat.FatherTable.TABLE_NAME;
    private static final String SQL_DELETE_CONTENU_TABLE =
            "DELETE FROM  " + FatherContrat.FatherTable.TABLE_NAME;

    public FatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE_SON);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void deleteFather(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_CONTENU_TABLE);
    }
    public void insererDonnee(String _id,String name, String key, int age, String relation ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FatherContrat.FatherTable.COLUMN_NAME_FATHER_ID, _id);
        values.put(FatherContrat.FatherTable.COLUMN_NAME_FATHER_NAME, name);
        values.put(FatherContrat.FatherTable.COLUMN_NAME_KEY, key);
        values.put(FatherContrat.FatherTable.COLUMN_NAME_AGE, age);
        values.put(FatherContrat.FatherTable.COLUMN_NAME_RELATION, relation);

        long newRowId = db.insert
                (FatherContrat.FatherTable.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<String> lireTouteDonnees() {
        SQLiteDatabase bd = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        // Colonnes de la Base de Données.
        String[] projection = {
                FatherContrat.FatherTable._ID,
                FatherContrat.FatherTable.COLUMN_NAME_FATHER_ID,
                FatherContrat.FatherTable.COLUMN_NAME_FATHER_NAME,
                FatherContrat.FatherTable.COLUMN_NAME_KEY,
                FatherContrat.FatherTable.COLUMN_NAME_AGE,
                FatherContrat.FatherTable.COLUMN_NAME_RELATION
        };

        @SuppressLint("Recycle") Cursor c = bd.query(
                FatherContrat.FatherTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();

        while(!c.isAfterLast()){
            String fatherId = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_FATHER_ID));
            String fatherName = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_FATHER_NAME));
            String key = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_KEY));
            String age = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_AGE));
            String relation = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_RELATION));
            array_list.add("Name" + fatherName);
            c.moveToNext();
        }
        return array_list;
    }
    @SuppressLint("Range")
    public ArrayList<String> lireKeyFather() {
        SQLiteDatabase bd = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        // Colonnes de la Base de Données.
        String[] projection = {
                FatherContrat.FatherTable.COLUMN_NAME_KEY,
        };

        @SuppressLint("Recycle") Cursor c = bd.query(
                FatherContrat.FatherTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();

        while(!c.isAfterLast()){
            String key = c.getString(c.getColumnIndex(FatherContrat.FatherTable.COLUMN_NAME_KEY));
            array_list.add(key);
            c.moveToNext();
        }
        return array_list;
    }
}
