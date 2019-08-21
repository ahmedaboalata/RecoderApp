package com.aboal3ta.recoder.Data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<Record>> allRecord;


    public RecordRepository(Application application) {
        Recorddatabase database = Recorddatabase.getInstance(application);
        recordDao = database.RecordDao();
         allRecord = recordDao.getallRecord();

    }

    public void insert(Record record) {
        new InsertnoteAsyncktask(recordDao).execute(record);
    }


    public void delete(Record record) {
        new deletenoteAsyncktask(recordDao).execute(record);
    }

    public void deleteallnote() {
        new deleteallnoteAsyncktask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllnotes() {
        return allRecord;
    }
    private static class InsertnoteAsyncktask extends AsyncTask<Record,Void,Void>
    {
        private RecordDao recordDao;
        private InsertnoteAsyncktask(RecordDao noteDao)
        {
            recordDao=noteDao;

        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insert(records[0]);
            return null;
        }
    }
    private static class deletenoteAsyncktask extends AsyncTask<Record,Void,Void>
    {
        private RecordDao noteDao;
        private deletenoteAsyncktask(RecordDao noteDao)
        {
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            noteDao.delete(records[0]);
            return null;
        }
    }
    private static class deleteallnoteAsyncktask extends AsyncTask<Void,Void,Void>
    {
        private RecordDao noteDao;
        private deleteallnoteAsyncktask(RecordDao noteDao)
        {
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.dleteallRecord();
            return null;
        }
    }
    }


