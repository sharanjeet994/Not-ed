package com.hunchplays.android.noted.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hunchplays.android.noted.Activity.UpdateNote;
import com.hunchplays.android.noted.MainActivity;
import com.hunchplays.android.noted.Model.Notes;
import com.hunchplays.android.noted.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotes = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filteredNote){

        this.notes = filteredNote;
        notifyDataSetChanged();
    }


    @Override
    public notesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewHolder holder, int position) {
        Notes n = notes.get(position);
        if (n.priority.equals("1")) {
            holder.priority.setBackgroundResource(R.drawable.red_priority);
        }
        if (n.priority.equals("2")){
            holder.priority.setBackgroundResource(R.drawable.yellow_priority);
        }
        if (n.priority.equals("3")){
            holder.priority.setBackgroundResource(R.drawable.green_priority);
        }
        holder.heading.setText(n.title);
        holder.context.setText(n.subtitle);
        holder.date.setText(n.Date);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity,UpdateNote.class);

            intent.putExtra("id",n.id);
            intent.putExtra("heading",n.title);
            intent.putExtra("context",n.subtitle);
            intent.putExtra("note",n.notes);
            intent.putExtra("priority",n.priority);
            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {
        TextView heading,context,date;
        View priority;
        public notesViewHolder(View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.headingItems);
            context = itemView.findViewById(R.id.contextItems);
            date = itemView.findViewById(R.id.dateItems);
            priority = itemView.findViewById(R.id.priorityItems);

        }
    }
}
