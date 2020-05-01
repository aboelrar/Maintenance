package com.carsyalla.www.cars.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.carsyalla.www.cars.Model.check;

import java.util.ArrayList;

public class sql extends SQLiteOpenHelper {
    public static final String DB_NAME = "favourite.db";
    public static final String TABLE_NAME ="favourite";
    public sql(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favourite(id INTEGER PRIMARY KEY AUTOINCREMENT, num TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favourite");
        onCreate(db);
    }

    public boolean addToFavourite(String num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("num", num);

        long status = db.insert(TABLE_NAME, null, values);
        if(status == -1){
            return false;
        }
        return true;
    }

    public ArrayList RetreiveAllData()
    {
        ArrayList<check> allPerson=new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM favourite", null);
        if(cursor.moveToNext())
        {
            do{
                int id=cursor.getInt(0);
                String num=cursor.getString(1);

                allPerson.add(new check(id,num));
            }while(cursor.moveToNext());
        }
        return allPerson;

    }

    public boolean removeAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int del=  db.delete(TABLE_NAME,null,null);
        if(del>0)
        {
            return true;
        }return false;
    }
    public boolean deleteItem(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int del=db.delete(TABLE_NAME,"id=?",new String[]{id});
        if(del>0)
        {
            return true;
        }return false;
    }
}
