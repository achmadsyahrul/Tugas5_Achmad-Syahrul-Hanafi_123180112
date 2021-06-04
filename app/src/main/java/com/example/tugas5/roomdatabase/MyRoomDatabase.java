package com.example.tugas5.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodTable.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract FoodDAO foodDAO();
    public static MyRoomDatabase instance;

    public static MyRoomDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, MyRoomDatabase.class, "DatabaseName")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
