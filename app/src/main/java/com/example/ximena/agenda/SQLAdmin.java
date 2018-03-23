package com.example.ximena.agenda;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ximena on 11/03/18.
 */

public class SQLAdmin extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE Contactos (nombre TEXT, numero INTEGER)";


    public SQLAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList llenar(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM Contactos";
        Cursor registros = db.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1));

            }while(registros.moveToNext());
        }

        return lista;
    }
}
