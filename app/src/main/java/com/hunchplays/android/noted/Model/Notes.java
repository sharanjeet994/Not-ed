package com.hunchplays.android.noted.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Table")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Title")
    public String title;

    @ColumnInfo(name = "Subtitle")
    public String subtitle;

    @ColumnInfo(name = "Date")
    public String Date;

    @ColumnInfo(name = "Notes")
    public String notes;

    @ColumnInfo(name = "Priority")
    public String priority;
}
