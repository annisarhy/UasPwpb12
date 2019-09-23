package com.example.uaspwpb12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

      private  static  final int DB_VERSION=1;
      private  static  final String DB_NAME="UserInfo";
      private  static  final String TABLE_NAME="tbl_user";
      private  static  final String KEY_JUDUL="judul";
      private  static  final String KEY_DESKRIPSI="deskripsi";
      private  static  final String KEY_TANGGAL="tanggal";
    public Database(Context context) {
       super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable="Create Table "+TABLE_NAME+"("+KEY_JUDUL+" TEXT PRIMARY KEY,"+KEY_DESKRIPSI+" TEXT "+","+KEY_TANGGAL+" TEXT "+")";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
           db.execSQL(sql);
           onCreate(db);
    }
    public void  insert(Data data){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_JUDUL,data.getJudul());
        values.put(KEY_TANGGAL,data.getTanggal());
        values.put(KEY_DESKRIPSI,data.getDeskripsi());

        db.insert(TABLE_NAME,null,values);
    }
        public List<Data> selectUserData(){
        ArrayList<Data> userList=new ArrayList<Data>();

        SQLiteDatabase db= getReadableDatabase();
        String[] columns={KEY_JUDUL,KEY_DESKRIPSI,KEY_TANGGAL};

        Cursor c =db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (c.moveToNext()){
            String judul=c.getString(0);
            String deskripsi=c.getString(1);
            String tanggal=c.getString(2);

            Data data=new Data();
            data.setJudul(judul);
            data.setDeskripsi(deskripsi);
            data.setTanggal(tanggal);
            userList.add(data);
        }

        return  userList;
    }
    public  void  delete(String judul){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_JUDUL+"='"+judul+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }
    public void update(Data data){
        SQLiteDatabase db=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_JUDUL,data.getJudul());
        String whereClause=KEY_DESKRIPSI+"='"+data.getDeskripsi()+"'";
        values.put(KEY_TANGGAL,data.getTanggal());
        db.update(TABLE_NAME,values,whereClause,null);
    }
}








