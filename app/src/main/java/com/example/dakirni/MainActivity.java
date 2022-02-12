package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.database.father.FatherDbHelper;
import com.example.dakirni.database.son.SonDbHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //these two lines are to delete the action bar in the top of my application and make it fullscreen
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    //this is a function that starts the father activity intent it is basicly used here with the text view:Father and the image View :Father
    public void startFatherActivity(View view) {
        FatherDbHelper fatherDbHelper = new FatherDbHelper(getApplicationContext());

        ArrayList<String> arrayList = fatherDbHelper.lireTouteDonnees();
        Intent intent;

        if(arrayList.size()<1){
            intent = new Intent(MainActivity.this, FatherLoginActivity.class);
        }else {
            try {
                Iterator<String> iter = arrayList.iterator();
                StringBuffer maListe = new StringBuffer();
                while (iter.hasNext()) {
                    maListe.append(iter.next() + " ");
                }
                Toast.makeText(this,maListe.toString(),Toast.LENGTH_SHORT).show();
            }catch (ArrayIndexOutOfBoundsException e){
                Toast.makeText(this,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
            }
            intent = new Intent(MainActivity.this, FatherChoiceActivity.class);
        }
        startActivity(intent);

    }
    //this is a function that starts the son activity intent it is basicly used here with the text view:Son and the image View :Son
    public void startSonActivity(View view) {
        SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());

        ArrayList<String> arrayList = sonDbHelper.lireToken();
        Intent intent;
        if(arrayList.size()<1){
            intent = new Intent(MainActivity.this, SonLoginActivity.class);
        }else {
            try {
                Iterator<String> iter = arrayList.iterator();
                StringBuffer maListe = new StringBuffer();
                while (iter.hasNext()) {
                    maListe.append(iter.next() + " ");
                }
                Toast.makeText(this,maListe.toString(),Toast.LENGTH_SHORT).show();
            }catch (ArrayIndexOutOfBoundsException e){
                Toast.makeText(this,"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
            }
            intent = new Intent(MainActivity.this, List_Parent.class);
        }
        startActivity(intent);

    }
    public void doLogout(){

    }

}