package com.example.anton.proyecto_browser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

/**
 * Created by anton on 25/10/2016.
 */

public class BDBrowser extends SQLiteOpenHelper{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "BD_BROWSER.db";
    private static final String ins= "CREATE TABLE browser(id INT PRIMARY KEY, nombre VARCHAR(100))";
    public BDBrowser(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ins);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertar(String s) {

        long nreg_afectados = -1;
        /* Abrimos la BD de Escritura */
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
        /* en este metodo utilizaremos ContentValues */
            ContentValues valores = new ContentValues();
            valores.put("id", s);
            valores.put("nombre", s);

            nreg_afectados = db.insert("browser", null, valores);
        }
        db.close();
        return nreg_afectados;
    }

    public Vector obtenerSugerencias(){
        Vector v=new Vector();
        SQLiteDatabase db=this.getReadableDatabase();
        String campos[]={"id","nombre"};
        Cursor c;
        if(db!=null){

            c=db.query("browser",campos,null,null,null,null,null,null);

            if(c!=null){
                c.moveToFirst();
                do {

                    v.add(c.getString(1));

                }while(c.moveToNext());
            }
        }
        return v;

    }

}
