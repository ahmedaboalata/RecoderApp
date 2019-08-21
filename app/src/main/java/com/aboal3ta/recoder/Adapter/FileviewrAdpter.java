package com.aboal3ta.recoder.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.aboal3ta.recoder.Data.Record;
import com.aboal3ta.recoder.R;
import com.aboal3ta.recoder.ViewModel.RecordViewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aboal3ta.recoder.Servicses.RecordServise.file;

public class FileviewrAdpter extends RecyclerView.Adapter<FileviewrAdpter.ViewHolder> {

    private List<Record> records =new ArrayList<>();
    private Onutemclicklister listner;
    Context context;


    public FileviewrAdpter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Record cureentrecord=records.get(position);
        holder.filename_txt.setText(cureentrecord.getTitle());
        holder.filelengh_txt.setText(cureentrecord.getLengh());
        holder.filetime_txt.setText( cureentrecord.getTime());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
    public Record getpostion( int postion)
    {
        return records.get(postion);
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.file_name) TextView filename_txt;
        @BindView(R.id.file_lengh) TextView filelengh_txt;
        @BindView(R.id.file_time) TextView filetime_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int postion=getAdapterPosition();
                    if (listner!=null && postion!=RecyclerView.NO_POSITION) {
                        listner.Onitemclick(records.get(postion));


                    }
                }
            });
        }
    }
    public void setData(List<Record> item) {
        //  set data to view
        records= item;
        notifyDataSetChanged();

    }
    public interface Onutemclicklister
    {
        void Onitemclick(Record record);

    }

    public void SetOnclicklister(Onutemclicklister listner)
    {
        this.listner=listner;
    }


}
