package com.example.vuphu.project_hoa_hoc;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemBaiTapActivity extends AppCompatActivity {

    private EditText edtBaiTap;

    private DatabaseReference mdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_bai_tap);

        edtBaiTap = (EditText) findViewById(R.id.edt_ND_bai_tap);
        mdata = FirebaseDatabase.getInstance().getReference();
    }

    public void addBaiTap(View view) {

        BaiTap post = new BaiTap();
        String debai = edtBaiTap.getText().toString();
        if (!TextUtils.isEmpty(debai)) {
            post.setDeBai(edtBaiTap.getText().toString());
            post.setCauTraLoi("Chưa có đáp án");
            Calendar c = Calendar.getInstance();
            DatabaseReference mDatabase2 = mdata.child("BaiTapTuLuan");
            String key = c.getTime().toString() ;
            Map<String, Object> postValues = post.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);
            mDatabase2.updateChildren(childUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Snackbar.make(findViewById(R.id.themBT),
                            Html.fromHtml("<font color=\"#fd592c\">Thêm thành công</font>"),
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
        else
            edtBaiTap.setError("Hãy nhập đề bài");

    }
}
