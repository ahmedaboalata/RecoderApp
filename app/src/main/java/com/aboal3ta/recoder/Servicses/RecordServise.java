package com.aboal3ta.recoder.Servicses;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.widget.Toast;

import com.aboal3ta.recoder.Data.Record;
import com.aboal3ta.recoder.ViewModel.RecordViewmodel;

import java.io.File;
import java.io.IOException;

public class RecordServise extends Service {
    MediaRecorder mediaRecorder;
    long timestartrecord=0;
    long mtimestartrecord=0;
   public static File file;
   public static long time;
    String Filenmae;



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        statrecord();
        return START_STICKY;
    }

    private void statrecord() {
        Long tstime = System.currentTimeMillis()/1000;
        String ts=tstime.toString();
        Filenmae ="auido"+ts;
        file=  new File(Environment.getExternalStorageDirectory()+"/Mysoundrecorder/"+Filenmae+".mp3");
        mediaRecorder =new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            timestartrecord=System.currentTimeMillis();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void stoprecord()
    {
        mediaRecorder.stop();
        mtimestartrecord=(System.currentTimeMillis()-timestartrecord);
        time=(System.currentTimeMillis()-timestartrecord);
        mediaRecorder.release();
       // Toast.makeText(getApplicationContext(),file.getAbsolutePath(),Toast.LENGTH_LONG).show();



    }

    @Override
    public void onDestroy() {

        if (mediaRecorder!=null)
        {

            stoprecord();
        }
        super.onDestroy();
    }
}
