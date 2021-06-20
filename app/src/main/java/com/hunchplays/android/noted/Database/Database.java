package com.hunchplays.android.noted.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hunchplays.android.noted.Dao.Dao;
import com.hunchplays.android.noted.Model.Notes;

@androidx.room.Database(entities = {Notes.class},version = 1)
public abstract class Database extends RoomDatabase {

    public abstract Dao dao();
    public static Database INSTANCE;
    public static Database getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class,"Notes_Table").allowMainThreadQueries().build();
        }
            return INSTANCE;
    }
}
