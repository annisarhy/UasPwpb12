package com.example.uaspwpb12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    Context context;
    OnUserActionListener Listener;
    List<Data> listDataInfo;


    public RecyclerViewAdapter(Context context, List<Data> listDataInfo, OnUserActionListener listener){
        this.context = context;
        this.listDataInfo = listDataInfo;
        this.Listener = listener;
    }

    public interface OnUserActionListener{
        void onUserAction(Data data);
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_item,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(v);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final Data currentData = listDataInfo.get(position);
        holder.txtJudul.setText(currentData.getJudul());
        holder.txtDeskripsi.setText(currentData.getDeskripsi());
        holder.txtTanggal.setText(currentData.getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.onUserAction(currentData);
            }
        });
    }

    @Override
    public int getItemCount() {

        return listDataInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul,txtDeskripsi,txtTanggal;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTanggal =itemView.findViewById(R.id.txtTime);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
        }
    }
}

