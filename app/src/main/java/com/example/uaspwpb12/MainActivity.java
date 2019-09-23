package com.example.uaspwpb12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements RecyclerViewAdapter.OnUserActionListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<Data> listDataInfo;
    FloatingActionButton floatBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();

        floatBtnAdd = findViewById(R.id.floatBtnAdd);
        floatBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, InputUpdate.class);
                pindah.putExtra("Action", "Insert");
                startActivity(pindah);
            }
        });

    }

    private void setupRecyclerView() {
        Database db = new Database(this);
        listDataInfo = db.selectUserData();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listDataInfo, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onUserAction(final Data data) {

        final CharSequence[] dialogItem = {"Ubah Data", "Hapus Data"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilihan");
        builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent updateData = new Intent(context, InputUpdate.class);
                        updateData.putExtra("Action", "Update");
                        updateData.putExtra("Data", data);
                        startActivity(updateData);
                        break;
                    case 1:
                        Database db = new Database(context);
                        db.delete(data.getJudul());
                        setupRecyclerView();
                        break;
                }
            }
        });

        builder.create().show();
    }
}
