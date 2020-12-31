package com.thkha.roomdatabase;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class MyApplicationDatabase extends RoomDatabase {
    private static volatile MyApplicationDatabase instance;

    public abstract MyApplicationDao getMyApplicationDao();

    public static synchronized MyApplicationDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyApplicationDatabase.class, "DatabaseApp.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
