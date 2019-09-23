package com.example.uaspwpb12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InputUpdate extends AppCompatActivity {

    EditText edtTextJudul, edtTextDeskripsi;
    Context context;
    Button btnSimpan;
    String action, submit;
    Data update;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_update);
        context = this;

        bundle = getIntent().getExtras();
        action = bundle.getString("Action");
        update = getIntent().getParcelableExtra("Data");

        edtTextJudul = findViewById(R.id.edtTextJudul);
        edtTextDeskripsi = findViewById(R.id.edtTextDeskripsi);

        if(action.equals("Update")){
            edtTextJudul.setText(update.getJudul());
            edtTextDeskripsi.setText(update.getDeskripsi());
        }

        btnSimpan = findViewById(R.id.btnSimpan);



        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database db = new Database(context);

                if (action.equals("Insert")){


                    Data data = new Data();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = simpleDateFormat.format(new Date());
                    data.setTanggal(date);

                    data.setJudul(edtTextJudul.getText().toString());
                    data.setDeskripsi(edtTextDeskripsi.getText().toString());
                    db.insert(data);
                    Intent move = new Intent(context,MainActivity.class);
                    startActivity(move);
                }
                if (action.equals("Update")){
                    Data data = new Data();
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    data.setTanggal(date);
                    data.setJudul(edtTextJudul.getText().toString());
                    data.setDeskripsi(edtTextDeskripsi.getText().toString());
                    db.update(data);
                    Intent move = new Intent(context,MainActivity.class);
                    startActivity(move);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}