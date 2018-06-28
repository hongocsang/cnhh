package com.example.vuphu.project_hoa_hoc;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CanBangActivity extends AppCompatActivity {

    private AutoCompleteTextView pthh;
    private TextView txtCanBang;
    private List<String> phanUngHoaHoc;
    private List<String> phanUngHoaHocCanBang;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_bang);
        pthh = (AutoCompleteTextView) findViewById(R.id.edt_PTHH);
        phanUngHoaHoc = new ArrayList<>();
        phanUngHoaHocCanBang = new ArrayList<>();
        phanUngHoaHoc = Arrays.asList(getResources().getStringArray(R.array.phan_ung_hoa_hoc));
        phanUngHoaHocCanBang = Arrays.asList(getResources().getStringArray(R.array.phan_ung_hh_can_bang));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,phanUngHoaHoc);
        pthh.setThreshold(2);
        pthh.setAdapter(adapter);
        txtCanBang = (TextView) findViewById(R.id.txt_phtth_da_can_bang);
        pthh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = phanUngHoaHoc.indexOf(pthh.getText().toString());
            }
        });

    }

    public void CanBangHoaHoc(View view) {
        String pthh = this.pthh.getText().toString();
        if(TextUtils.isEmpty(pthh)){
            this.pthh.setError("Nhập phương trình hóa học");
        }
        else{
            Log.i("index",""+i);
            String pthhCanBang= this.phanUngHoaHocCanBang.get(i).toString();
            txtCanBang.setText(pthhCanBang);

        }
    }
}
