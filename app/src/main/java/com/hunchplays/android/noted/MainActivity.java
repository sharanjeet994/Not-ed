package com.hunchplays.android.noted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.SearchView;

import com.hunchplays.android.noted.Activity.AddNoteActivity;
import com.hunchplays.android.noted.Adapter.NotesAdapter;
import com.hunchplays.android.noted.Model.Notes;
import com.hunchplays.android.noted.ViewModel.ViewModel;
import com.hunchplays.android.noted.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

 ActivityMainBinding binding;

 ViewModel viewModel;
 RecyclerView recyclerView;
 NotesAdapter notesAdapter;
 List<Notes> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.serialized.setBackgroundResource(R.drawable.filter_oval_shape_selected);
        binding.serialized.setTextColor(getColor(R.color.white));

        binding.serialized.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(0);
                binding.serialized.setBackgroundResource(R.drawable.filter_oval_shape_selected);
                binding.serialized.setTextColor(getColor(R.color.white));

                binding.redToGreen.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.redToGreen.setTextColor(getColor(R.color.skyBlue));

                binding.greenToRed.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.greenToRed.setTextColor(getColor(R.color.skyBlue));

            }
        });

        binding.redToGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(1);
                binding.redToGreen.setBackgroundResource(R.drawable.filter_oval_shape_selected);
                binding.redToGreen.setTextColor(getColor(R.color.white));

                binding.greenToRed.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.greenToRed.setTextColor(getColor(R.color.skyBlue));

                binding.serialized.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.serialized.setTextColor(getColor(R.color.skyBlue));


            }
        });
        binding.greenToRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(2);
                binding.greenToRed.setBackgroundResource(R.drawable.filter_oval_shape_selected);
                binding.greenToRed.setTextColor(getColor(R.color.white));

                binding.serialized.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.serialized.setTextColor(getColor(R.color.skyBlue));

                binding.redToGreen.setBackgroundResource(R.drawable.filter_oval_shape);
                binding.redToGreen.setTextColor(getColor(R.color.skyBlue));
            }
        });


        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        recyclerView = binding.recyclerView;
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        viewModel.getNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                allNotes = notes;

            }
        });

    }

    private void loadData(int i) {
        if (i==0){
            viewModel.getNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    allNotes = notes;
                }
            });
        }
        else if(i==1){
            viewModel.redToGreen.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    allNotes = notes;

                }
            });
        }
        else if(i==2){
            viewModel.greenToRed.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    allNotes = notes;

                }
            });
        }
    }

    public void setAdapter(List<Notes> notes){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            notesAdapter = new NotesAdapter(MainActivity.this,notes);
            recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Note...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> filterNote = new ArrayList<>();
        for (Notes notes:this.allNotes){
            if (notes.title.contains(newText)|| notes.subtitle.contains(newText)){

                filterNote.add(notes);
            }
        }
        this.notesAdapter.searchNotes(filterNote);
    }
}