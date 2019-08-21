package com.aboal3ta.recoder.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert
    void insert(Record record);

    @Delete
    void delete(Record record);

    @Query("DELETE FROM Record_table")
    void dleteallRecord();

    @Query("SELECT * FROM Record_table ORDER BY id ASC")
    LiveData<List<Record>> getallRecord();
}
