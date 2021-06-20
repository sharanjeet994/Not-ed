package com.hunchplays.android.noted.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hunchplays.android.noted.Model.Notes;
import com.hunchplays.android.noted.Repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    public Repository repository;
    public LiveData<List<Notes>> getNotes;

    public LiveData<List<Notes>> redToGreen;
    public LiveData<List<Notes>> greenToRed;

    public ViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        getNotes = repository.getNotes;
        redToGreen = repository.redToGreen;
        greenToRed = repository.greenToRed;
    }
    public void addNoteRepo(Notes notes){
        repository.AddNote(notes);
    }
    public void removeNoteRepo(int id){
        repository.RemoveNote(id);
    }
    public void updateNoteRepo(Notes notes){
        repository.UpdateNote(notes);
    }
}
