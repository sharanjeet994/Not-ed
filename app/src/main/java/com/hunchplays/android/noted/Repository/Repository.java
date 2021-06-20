package com.hunchplays.android.noted.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hunchplays.android.noted.Dao.Dao;
import com.hunchplays.android.noted.Database.Database;
import com.hunchplays.android.noted.Model.Notes;

import java.util.List;

public class Repository {

    public Dao dao;
    public LiveData<List<Notes>> getNotes;

    public LiveData<List<Notes>> redToGreen;
    public LiveData<List<Notes>> greenToRed;


    public Repository(Application application) {
        Database database = Database.getINSTANCE(application);
        dao = database.dao();
        getNotes = dao.getNotes();

        redToGreen = dao.redToGreen();
        greenToRed = dao.greenToRed();

    }
    public void AddNote(Notes notes){
        dao.addNote(notes);
    }
    public void RemoveNote(int id){
        dao.removeNote(id);
    }
    public void UpdateNote(Notes notes){
        dao.updateNote(notes);
    }

}
