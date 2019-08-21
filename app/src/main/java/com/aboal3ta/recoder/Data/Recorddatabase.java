package com.aboal3ta.recoder.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Record.class, version = 1 ,exportSchema = false)
public  abstract class Recorddatabase extends RoomDatabase {

    private static Recorddatabase instanse;

    public abstract RecordDao RecordDao();

   public static synchronized Recorddatabase getInstance(Context context) {
        if (instanse == null) {
            instanse = Room.databaseBuilder(context.getApplicationContext(), Recorddatabase.class, "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instanse;
    }

    private static RoomDatabase.Callback roomcallback =new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateAsynck(instanse).execute();

        }
    };

    private static class populateAsynck extends AsyncTask<Void,Void,Void>
    {
private RecordDao recordDao;

        private populateAsynck(Recorddatabase recorddatabase) {
            this.recordDao =recorddatabase.RecordDao() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
