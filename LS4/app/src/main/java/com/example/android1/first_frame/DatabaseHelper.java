package com.example.android1.first_frame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    public static final int DATABASE_VARSION = 1;
    public static final String DB_NAME = "Zametrki.db";
    public static final String TABLE_CONSTANS = "constans";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_MASSEGES = "massegse";
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VARSION);
        this.myContext=context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DB_PATH =context.getDataDir().getPath() +"/databases/"+ DB_NAME;
        }
        //Log.d("DatabaseHelper", DB_PATH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) { }

    void create_db(){

        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
        finally {
            try{
                if(myOutput!=null) myOutput.close();
                if(myInput!=null) myInput.close();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

}
