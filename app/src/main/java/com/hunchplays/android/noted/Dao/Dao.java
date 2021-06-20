package com.hunchplays.android.noted.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hunchplays.android.noted.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM Notes_Table")
    LiveData<List<Notes>> getNotes();

    @Query("SELECT * FROM Notes_Table ORDER BY Priority ASC")
    LiveData<List<Notes>> redToGreen();

    @Query("SELECT * FROM Notes_Table ORDER BY Priority DESC")
    LiveData<List<Notes>> greenToRed();

    @Insert()
    void addNote(Notes... notes);

    @Query("DELETE FROM Notes_Table WHERE id=:id")
    void removeNote(int id);

    @Update
    void updateNote(Notes notes);

}
