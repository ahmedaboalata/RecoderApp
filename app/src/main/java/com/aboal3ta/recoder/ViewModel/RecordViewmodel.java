package com.aboal3ta.recoder.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aboal3ta.recoder.Data.Record;
import com.aboal3ta.recoder.Data.RecordRepository;

import java.util.List;

public class RecordViewmodel extends AndroidViewModel {

    private RecordRepository repository;
    private LiveData<List<Record>> allnotes;

    public RecordViewmodel(@NonNull Application application) {
        super(application);
        repository=new RecordRepository(application);
        allnotes=repository.getAllnotes();
    }
    public void insert(Record record)
    {
        repository.insert(record);
        Log.d("yyyyy",record.toString());
    }

    public void delete(Record record)
    {
        repository.delete(record);
    }
    public void deleteall()
    {
        repository.deleteallnote();
    }
    public LiveData<List<Record>> getAllnotes()
    {
        return allnotes ;
    }
}
