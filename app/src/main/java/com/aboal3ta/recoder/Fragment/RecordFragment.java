package com.aboal3ta.recoder.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aboal3ta.recoder.Data.Record;
import com.aboal3ta.recoder.R;
import com.aboal3ta.recoder.Servicses.RecordServise;
import com.aboal3ta.recoder.ViewModel.RecordViewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.itangqi.waveloadingview.WaveLoadingView;

import static com.aboal3ta.recoder.Fragment.FileViewrFragment.add;
import static com.aboal3ta.recoder.Servicses.RecordServise.file;

public class RecordFragment extends Fragment {
    @BindView(R.id.btn_record) FloatingActionButton btn_record;
    @BindView(R.id.btn_pause) Button btn_Pause;
    @BindView(R.id.chronometer) Chronometer chronometer;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.Recod_stuts) TextView record_stusts;
    private Boolean mstartrecording=true;
    private Boolean mPauserecording=true;
    long Timewhenpaused = 0;
    RecordViewmodel recordViewmodel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recordview=inflater.inflate(R.layout.fragment_record,container,false);
        ButterKnife.bind(this,recordview);
        return recordview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_Pause.setVisibility(View.GONE);

    }
    @OnClick(R.id.btn_record)
   public void startrecord()
    {
        record(mstartrecording);
        mstartrecording=!mstartrecording;
    }

    private void record(Boolean start)
    {
        Intent intent=new Intent(getActivity(), RecordServise.class);
        if (start)
        {
            btn_record.setImageResource(R.drawable.ic_action_stop);
            Toast.makeText(getActivity(),"Recors Start",Toast.LENGTH_LONG).show();
            File folder= new File(Environment.getExternalStorageDirectory()+"/Mysoundrecorder");
if (!folder.exists())
{
    folder.mkdir();
}
chronometer.setBase(SystemClock.elapsedRealtime());
chronometer.start();
getActivity().startService(intent);
getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
record_stusts.setText("Recording.....");
btn_Pause.setVisibility(View.VISIBLE);

        }
        else
        {
            btn_record.setImageResource(R.drawable.ic_mic);
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            Timewhenpaused=0;
            record_stusts.setText("Tap Button to start recording ....");
            getActivity().stopService(intent);
            add();
            btn_Pause.setVisibility(View.GONE);


        }
    }


}
