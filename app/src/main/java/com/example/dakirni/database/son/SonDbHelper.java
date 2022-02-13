package com.example.dakirni.database.son;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dakirni.database.father.FatherContrat;
import com.example.dakirni.database.son.SonContrat;

import java.util.ArrayList;

public class SonDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dakirni.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    SQLiteDatabase db = this.getWritableDatabase();
    private static final String SQL_CREATE_TABLE_Father =
            "CREATE TABLE " + FatherContrat.FatherTable.TABLE_NAME + " (" +
                    FatherContrat.FatherTable._ID + " INTEGER PRIMARY KEY," +

                    FatherContrat.FatherTable.COLUMN_NAME_FATHER_ID + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_FATHER_NAME + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_KEY + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_AGE + TEXT_TYPE + COMMA_SEP +
                    FatherContrat.FatherTable.COLUMN_NAME_RELATION + TEXT_TYPE + " )";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + SonContrat.SonTable.TABLE_NAME + " (" +
                    SonContrat.SonTable._ID + " INTEGER PRIMARY KEY," +
                    SonContrat.SonTable.COLUMN_NAME_TOKEN + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_SON_ID + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_SON_NAME + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    SonContrat.SonTable.COLUMN_NAME_PASSWORD + TEXT_TYPE + " )";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + SonContrat.SonTable.TABLE_NAME;
    private static final String SQL_DELETE_CONTENU_TABLE =
            "DELETE FROM " + SonContrat.SonTable.TABLE_NAME;

    public SonDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE_Father);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
    public void deleteSon() {
        db.execSQL(SQL_DELETE_CONTENU_TABLE);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void insererDonnee(String token, String _id,String username, String email, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SonContrat.SonTable.COLUMN_NAME_TOKEN, token);
        values.put(SonContrat.SonTable.COLUMN_NAME_SON_ID, _id);
        values.put(SonContrat.SonTable.COLUMN_NAME_SON_NAME, username);
        values.put(SonContrat.SonTable.COLUMN_NAME_EMAIL, email);
        values.put(SonContrat.SonTable.COLUMN_NAME_PASSWORD, password);

        long newRowId = db.insert
                (SonContrat.SonTable.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<String> lireTouteDonnees() {
        SQLiteDatabase bd = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        // Colonnes de la Base de Données.
        String[] projection = {
                SonContrat.SonTable._ID,
                SonContrat.SonTable.COLUMN_NAME_SON_ID,
                SonContrat.SonTable.COLUMN_NAME_SON_NAME,
                SonContrat.SonTable.COLUMN_NAME_EMAIL,
                SonContrat.SonTable.COLUMN_NAME_PASSWORD
        };

        @SuppressLint("Recycle") Cursor c = bd.query(
                SonContrat.SonTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();

        while(!c.isAfterLast()){
            String son_id = c.getString(c.getColumnIndex(SonContrat.SonTable.COLUMN_NAME_SON_ID));
            String usrername = c.getString(c.getColumnIndex(SonContrat.SonTable.COLUMN_NAME_SON_NAME));
            String email = c.getString(c.getColumnIndex(SonContrat.SonTable.COLUMN_NAME_EMAIL));
            String password = c.getString(c.getColumnIndex(SonContrat.SonTable.COLUMN_NAME_PASSWORD));
            array_list.add("Name" + usrername + " Age: " +email+"Password"+ password);
            c.moveToNext();
        }
        return array_list;
    }
    @SuppressLint("Range")
    public ArrayList<String> lireToken() {
        SQLiteDatabase bd = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        // Colonnes de la Base de Données.
        String[] projection = {
                SonContrat.SonTable.COLUMN_NAME_TOKEN
        };

        @SuppressLint("Recycle") Cursor c = bd.query(
                SonContrat.SonTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();

        while(!c.isAfterLast()){
            String token = c.getString(c.getColumnIndex(SonContrat.SonTable.COLUMN_NAME_TOKEN));
            array_list.add(token);

            c.moveToNext();
        }
        return array_list;
    }
}
