package com.osama.mynotelanguages;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class User extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_MAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABEL_NAME = "user";
    public static final String FIRSNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";


    public User(@Nullable Context context) {

        super(context , DATABASE_MAME , null , DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " +  TABEL_NAME +
                " (" + FIRSNAME + " TEXT ,"+LASTNAME + " TEXT  ,"+EMAIL + " TEXT  ,"+PASSWORD + " TEXT ,"+TOKEN + " TEXT) ;";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_NAME);
        onCreate(db);
    }

    public void adduser(String firstname , String lastname , String email , String password , String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSNAME , firstname);
        cv.put(LASTNAME,  lastname);
        cv.put(EMAIL,  email);
        cv.put(PASSWORD,  password);
        cv.put(TOKEN, token);

        long result = db.insert(TABEL_NAME , null , cv);
        if(result == -1){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.ic_baseline_error_24)
                    .setTitle(" ")
                    .setMessage("failed database");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.sucsess)

                    .setView(R.layout.sucsess);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    Cursor redData(){
        String query = "SELECT * FROM " +TABEL_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.ic_baseline_error_24)
                    .setTitle(" ")
                    .setMessage("failed database");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return cursor;
    }



    void delete(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABEL_NAME, "word=?", new String[]{word});
        if (result == -1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.ic_baseline_error_24)
                    .setTitle(" ")
                    .setMessage("failed database");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.sucsess)

                    .setView(R.layout.sucsess);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }
}
