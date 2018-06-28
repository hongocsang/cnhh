package com.example.vuphu.project_hoa_hoc;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemCauHoi extends AppCompatActivity {


    private EditText cauHoi, dapAn1, dapAn2, dapAn3, dapAn4;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cau_hoi);
        init();
    }

    private void init() {
        cauHoi = (EditText) findViewById(R.id.edt_ND_cau_hoi);
        dapAn1 = (EditText) findViewById(R.id.edt_DA1);
        dapAn2 = (EditText) findViewById(R.id.edt_DA2);
        dapAn3 = (EditText) findViewById(R.id.edt_DA3);
        dapAn4 = (EditText) findViewById(R.id.edt_DA4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.check_one);
        radioButton2 = (RadioButton) findViewById(R.id.check_two);
        radioButton3 = (RadioButton) findViewById(R.id.check_three);
        radioButton4 = (RadioButton) findViewById(R.id.check_four);
        mData = FirebaseDatabase.getInstance().getReference();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                findViewById(R.id.add_btnUpload).setClickable(true);
            }
        });
    }

    public void addCauHoi(View view) {
        CauHoi post = new CauHoi();
        if (checkNotNULL()) {

            if (radioButton1.isChecked()) {

                post = new CauHoi(cauHoi.getText().toString(), dapAn1.getText().toString(), dapAn2.getText().toString(),
                        dapAn3.getText().toString(), dapAn4.getText().toString(), dapAn1.getText().toString());
            }

            if (radioButton2.isChecked()) {

                post = new CauHoi(cauHoi.getText().toString(), dapAn1.getText().toString(), dapAn2.getText().toString(),
                        dapAn3.getText().toString(), dapAn4.getText().toString(), dapAn2.getText().toString());
            }

            if (radioButton3.isChecked()) {

                post = new CauHoi(cauHoi.getText().toString(), dapAn1.getText().toString(), dapAn2.getText().toString(),
                        dapAn3.getText().toString(), dapAn4.getText().toString(), dapAn3.getText().toString());
            }

            if (radioButton4.isChecked()) {

                post = new CauHoi(cauHoi.getText().toString(), dapAn1.getText().toString(), dapAn2.getText().toString(),
                        dapAn3.getText().toString(), dapAn4.getText().toString(), dapAn4.getText().toString());
            }

            if (!UploadData(post)){
                final CauHoi cm = post;
                Snackbar snack = Snackbar.make(findViewById(R.id.themCAUHOI),Html.fromHtml("<font color=\"#fd592c\">Không thể thêm đuọc</font>"),Snackbar.LENGTH_LONG)
                        .setAction("Thử lại", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UploadData(cm);
                            }
                        });

                snack.show();
            }
            else
                Snackbar.make(findViewById(R.id.themCAUHOI),
                        Html.fromHtml("<font color=\"#fd592c\">Thêm thành công</font>"),
                        Snackbar.LENGTH_LONG).show();

        }
    }

    public boolean UploadData (CauHoi post){
        Calendar c = Calendar.getInstance();
        DatabaseReference mDatabase2 = mData.child("CauHoiTracNghiem");
        String key = c.getTime().toString();
        Map<String, Object> postValues = post.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);
        final Boolean[] ok = new Boolean[1];
        ok[0] =true;
        mDatabase2.updateChildren(childUpdates).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ok[0] = false;
            }
        });
        return ok[0];
    }

    public boolean checkNotNULL(){
        if (TextUtils.isEmpty(cauHoi.getText().toString())){
            cauHoi.setError("Nhập câu hỏi trước");
            return false;
        }
            if (TextUtils.isEmpty(dapAn1.getText().toString())){
                dapAn1.setError("Nhập đáp án 1");
                return false;
            }

            if (TextUtils.isEmpty(dapAn2.getText().toString())){
                dapAn2.setError("Nhập đáp án 2");
                return false;
            }

            if (TextUtils.isEmpty(dapAn3.getText().toString())){
                dapAn3.setError("Nhập đáp án 3");
                return false;
            }

            if (TextUtils.isEmpty(dapAn4.getText().toString())) {
                dapAn4.setError("Nhập đáp án 4");
            }
                if (radioGroup.getCheckedRadioButtonId() !=-1) {
                    findViewById(R.id.add_btnUpload).setClickable(true);
                }
                else {
                    findViewById(R.id.add_btnUpload).setClickable(false);
                    Toast.makeText(this, "Chọn đáp án đúng", Toast.LENGTH_SHORT).show();
                    return false;
                }
        return true;
    }
}
