package com.aboal3ta.recoder.Data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Record_table")
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String lengh;
    private String filepath;
    private String time;

    public Record(String title, String lengh, String filepath, String time) {
        this.title = title;
        this.lengh = lengh;
        this.filepath = filepath;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLengh() {
        return lengh;
    }

    public void setLengh(String lengh) {
        this.lengh = lengh;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
