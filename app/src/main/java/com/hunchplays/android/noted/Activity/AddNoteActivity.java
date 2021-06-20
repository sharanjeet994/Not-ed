package com.hunchplays.android.noted.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hunchplays.android.noted.Model.Notes;
import com.hunchplays.android.noted.R;
import com.hunchplays.android.noted.ViewModel.ViewModel;
import com.hunchplays.android.noted.databinding.ActivityAddNoteBinding;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    String heading,context,jotItDown;
    ViewModel viewModel;
    String priority = "2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        binding.redP.setOnClickListener(v -> {
            binding.redP.setImageResource(R.drawable.tick_24);
            binding.yellowP.setImageResource(0);
            binding.greenP.setImageResource(0);
            priority = "1";
        });
        binding.yellowP.setOnClickListener(v -> {
            binding.yellowP.setImageResource(R.drawable.tick_24);
            binding.redP.setImageResource(0);
            binding.greenP.setImageResource(0);
            priority = "2";
        });
        binding.greenP.setOnClickListener(v -> {
            binding.greenP.setImageResource(R.drawable.tick_24);
            binding.yellowP.setImageResource(0);
            binding.redP.setImageResource(0);
            priority = "3";
        });


        binding.fab.setOnClickListener(v -> {
            heading = binding.heading.getText().toString();
            context = binding.context.getText().toString();
            jotItDown = binding.jotItDown.getText().toString();

            addANote(heading,context,jotItDown);

            Toast.makeText(this, "Not'ed", Toast.LENGTH_SHORT).show();
        });



    }

    private void addANote(String heading, String context, String jotItDown) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d yyyy",date.getTime());

        Notes notes = new Notes();
        notes.title = heading;
        notes.subtitle = context;
        notes.priority = priority;
        notes.notes = jotItDown;
        notes.Date = sequence.toString();
        viewModel.addNoteRepo(notes);

        finish();

    }
}