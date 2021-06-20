package com.hunchplays.android.noted.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hunchplays.android.noted.Model.Notes;
import com.hunchplays.android.noted.R;
import com.hunchplays.android.noted.ViewModel.ViewModel;
import com.hunchplays.android.noted.databinding.ActivityUpdateNoteBinding;

import java.util.Date;

public class UpdateNote extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String priority = "2";
    String sHeading,sContext,sNote,sPriority;
    int sId;
    String heading,context,jotItDown;
    ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);


        sId = getIntent().getIntExtra("id",2);
        sHeading = getIntent().getStringExtra("heading");
        sContext = getIntent().getStringExtra("context");
        sNote = getIntent().getStringExtra("note");
        sPriority = getIntent().getStringExtra("priority");

        if (sPriority.equals("1")){
            binding.redPUpdate.setImageResource(R.drawable.tick_24);
            binding.yellowPUpdate.setImageResource(0);
            binding.greenPUpdate.setImageResource(0);
        }
        if (sPriority.equals("2")){
            binding.yellowPUpdate.setImageResource(R.drawable.tick_24);
            binding.redPUpdate.setImageResource(0);
            binding.greenPUpdate.setImageResource(0);
        }
        if (sPriority.equals("3")){
            binding.greenPUpdate.setImageResource(R.drawable.tick_24);
            binding.yellowPUpdate.setImageResource(0);
            binding.redPUpdate.setImageResource(0);
        }
        binding.headingUpdate.setText(sHeading);
        binding.contextUpdate.setText(sContext);
        binding.jotItDownUpdate.setText(sNote);

        binding.redPUpdate.setOnClickListener(v -> {
            binding.redPUpdate.setImageResource(R.drawable.tick_24);
            binding.yellowPUpdate.setImageResource(0);
            binding.greenPUpdate.setImageResource(0);
            priority = "1";
        });
        binding.yellowPUpdate.setOnClickListener(v -> {
            binding.yellowPUpdate.setImageResource(R.drawable.tick_24);
            binding.redPUpdate.setImageResource(0);
            binding.greenPUpdate.setImageResource(0);
            priority = "2";
        });
        binding.greenPUpdate.setOnClickListener(v -> {
            binding.greenPUpdate.setImageResource(R.drawable.tick_24);
            binding.yellowPUpdate.setImageResource(0);
            binding.redPUpdate.setImageResource(0);
            priority = "3";
        });

        binding.fabUpdate.setOnClickListener(v -> {
            heading = binding.headingUpdate.getText().toString();
            context = binding.contextUpdate.getText().toString();
            jotItDown = binding.jotItDownUpdate.getText().toString();

            updateNote(heading,context,jotItDown);

            Toast.makeText(this, "Not'ed", Toast.LENGTH_SHORT).show();
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.removeNoteMenu){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNote.this,R.style.BottomSheetStyle);

            View view  = LayoutInflater.from(UpdateNote.this).inflate(R.layout.remove_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheet));
            bottomSheetDialog.setContentView(view);

            bottomSheetDialog.show();

            TextView yes,no;
            yes = view.findViewById(R.id.yesRemove);
            no = view.findViewById(R.id.noRemove);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                viewModel.removeNoteRepo(sId);
                finish();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

        }

        return true;
    }

    private void updateNote(String heading, String context, String jotItDown) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d yyyy",date.getTime());

        Notes notes = new Notes();
        notes.id = sId;
        notes.title = heading;
        notes.subtitle = context;
        notes.priority = priority;
        notes.notes = jotItDown;
        notes.Date = sequence.toString();
        viewModel.updateNoteRepo(notes);

        finish();
    }




}