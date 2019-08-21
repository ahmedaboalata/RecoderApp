package com.aboal3ta.recoder.Fragment;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aboal3ta.recoder.Adapter.FileviewrAdpter;
import com.aboal3ta.recoder.Data.Record;
import com.aboal3ta.recoder.R;
import com.aboal3ta.recoder.ViewModel.RecordViewmodel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aboal3ta.recoder.Servicses.RecordServise.file;
import static com.aboal3ta.recoder.Servicses.RecordServise.time;

public class FileViewrFragment extends Fragment {

    @BindView(R.id.recycle_view) RecyclerView recyclerView;

    private static RecordViewmodel recordViewmodel;
    private FileviewrAdpter adpter;
    Fragment fragment;
    static Date currentTime ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recordview =inflater.inflate(R.layout.fragment_fileviewr,container,false);
        ButterKnife.bind(this,recordview);

        adpter =new FileviewrAdpter(getActivity());
        currentTime = Calendar.getInstance().getTime();
        recordViewmodel = ViewModelProviders.of(this).get(RecordViewmodel.class);
        recordViewmodel.getAllnotes().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> notes) {
                //updateReycleview
                adpter.setData(notes);

            }
        });

        return recordview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recordViewmodel.delete(adpter.getpostion(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"Note delete",Toast.LENGTH_LONG).show();

            }
        }).attachToRecyclerView(recyclerView);



        adpter.SetOnclicklister(new FileviewrAdpter.Onutemclicklister() {
            @Override
            public void Onitemclick(Record rcord) {
                fragment=new Media_Fragment();
                loadFragment(fragment);

            }
        });

    }
    private void setRecyclerView ()
    {
        LinearLayoutManager llm= new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adpter);

    }
    public static void add()
    {
        Record record20=new Record("Audio"+currentTime,String.valueOf(time),file.getAbsolutePath(),String.valueOf(currentTime.getTime()));
        recordViewmodel.insert(record20);
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
