package com.example.vuphu.project_hoa_hoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;

public class NoiDungChuongActivity extends AppCompatActivity {

    String fileName;
    String fileData;
    String tenChuong;
    TextView tv;
    int solop;
    int soChuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_chuong);
        tv = (TextView) findViewById(R.id.tvktdata);
        Intent intent = getIntent();
        tenChuong = intent.getStringExtra("chuong");
        soChuong = intent.getIntExtra("sttChuong",0);
        solop = intent.getIntExtra("lop",0);
        fileName = "lop"+solop+"chuong"+soChuong+".txt";
        setTitle(tenChuong);
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            fileData = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv.setText(fileData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tools, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.BTH)
        {
            startActivity( new Intent(NoiDungChuongActivity.this, BangTuanHoanActivity.class));
        }
        if (item.getItemId() == R.id.CHH)
            startActivity( new Intent(NoiDungChuongActivity.this, ChatHoaHocActivity.class));
        if (item.getItemId() == R.id.BaiCaHoaTri){
            startActivity( new Intent(NoiDungChuongActivity.this, BaiCaHoaTriActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }
}
