package com.example.vuphu.project_hoa_hoc;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TracNghiemActivity extends AppCompatActivity {


    private boolean[] Dung_Sai;
    private DatabaseReference mDatabase;
    private LinearLayout linear1, linear2, ketqua;
    private RecyclerView list_CHTN;
    private DatabaseReference query;
    private ScrollView scroll;
    private List<CauHoi> cauHois;
    private ChildEventListener childEventListener;
    private CHTNAdapter adapter;
    private int diem=0;
    private TextView kqd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem);
        init();

    }

    private void init() {

        cauHois = new ArrayList<>();
        Dung_Sai = new boolean[10];

        mDatabase = FirebaseDatabase.getInstance().getReference();
        query = mDatabase.child("CauHoiTracNghiem");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CauHoi cauHoi = dataSnapshot.getValue(CauHoi.class);
                cauHois.add(cauHoi);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);


        adapter = new CHTNAdapter(this,cauHois);
        adapter.notifyDataSetChanged();


        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        ketqua = (LinearLayout) findViewById(R.id.ketqua);
        list_CHTN = (RecyclerView) findViewById(R.id.list_CHTN);
        kqd = (TextView) findViewById(R.id.diem);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        list_CHTN.setHasFixedSize(true);
        list_CHTN.setLayoutManager(manager);

        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.GONE);
        ketqua.setVisibility(View.GONE);

        scroll = (ScrollView) findViewById(R.id.sroll);
        scroll.setVisibility(View.GONE);

        list_CHTN.setAdapter(adapter);

    }

    private static class itemCHTN extends RecyclerView.ViewHolder{
        TextView cauHoi;
        RadioButton dapAn1, dapAn2,dapAn3,dapAn4;
        RadioGroup radioGroup;
        public itemCHTN(View itemView) {
            super(itemView);
            cauHoi = (TextView) itemView.findViewById(R.id.tv_CHTN_cauHoi);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroupTN);
            dapAn1 = (RadioButton) itemView.findViewById(R.id.check_oneTN);
            dapAn2 = (RadioButton) itemView.findViewById(R.id.check_twoTN);
            dapAn3 = (RadioButton) itemView.findViewById(R.id.check_threeTN);
            dapAn4 = (RadioButton) itemView.findViewById(R.id.check_fourTN);
        }
    }

    private class CHTNAdapter extends RecyclerView.Adapter<itemCHTN>{
        Context context;
        List<CauHoi> cauHois = new ArrayList<>();
        int i = 0;

        public CHTNAdapter(Context context, List<CauHoi> cauHois) {
            this.context = context;
            this.cauHois = cauHois;
        }

        @Override
        public itemCHTN onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.item_row_tracnghiem,parent,false);
            return new itemCHTN(itemView);
        }

        @Override
        public void onBindViewHolder(final itemCHTN holder, int position) {
            final CauHoi ch = cauHois.get(position);
            holder.cauHoi.setText((i+1)+"."+ch.getCauHoi());
            holder.dapAn1.setText(ch.getDapAn1());
            holder.dapAn2.setText(ch.getDapAn2());
            holder.dapAn3.setText(ch.getDapAn3());
            holder.dapAn4.setText(ch.getDapAn4());
            final Boolean[] check = new Boolean[4];
            check[0] = false;
            check[1] = false;
            check[2] = false;
            check[3] = false;
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    holder.dapAn1.setTextColor(Color.BLACK);
                    holder.dapAn2.setTextColor(Color.BLACK);
                    holder.dapAn3.setTextColor(Color.BLACK);
                    holder.dapAn4.setTextColor(Color.BLACK);

                    if (checkedId == R.id.check_oneTN ){
                        Dung_Sai[i] = kiemDapAn(ch, holder.dapAn1.getText().toString());
                        holder.dapAn1.setTextColor(getResources().getColor(R.color.colorAccent));
                        if (Dung_Sai[i] && !check[0]) {
                            diem++;
                            check[0] = true;
                        }
                    }
                    if (checkedId == R.id.check_twoTN){
                        Dung_Sai[i] = kiemDapAn(ch, holder.dapAn2.getText().toString());
                        holder.dapAn2.setTextColor(getResources().getColor(R.color.colorAccent));
                        if (Dung_Sai[i] && !check[1]) {
                            diem++;
                            check[1] = true;
                        }
                    }
                    if (checkedId == R.id.check_threeTN){
                        Dung_Sai[i] = kiemDapAn(ch, holder.dapAn3.getText().toString());
                        holder.dapAn3.setTextColor(getResources().getColor(R.color.colorAccent));
                        if (Dung_Sai[i]&& !check[2]) {
                            diem++;
                            check[2] = true;
                        }
                    }
                    if (checkedId == R.id.check_fourTN){
                        Dung_Sai[i] = kiemDapAn(ch, holder.dapAn4.getText().toString());
                        holder.dapAn4.setTextColor(getResources().getColor(R.color.colorAccent));
                        if (Dung_Sai[i] && !check[3]) {
                            diem++;
                            check[3] = true;
                        }
                    }
                }
            });
            i++;
        }
        @Override
        public int getItemCount() {
            return cauHois.size();
        }
    }

    public void DongY(View view) {
        linear1.setVisibility(View.GONE);
        linear2.setVisibility(View.VISIBLE);
        scroll.setVisibility(View.VISIBLE);

    }

    public boolean kiemDapAn (CauHoi x, String dapAnChon){
        if (dapAnChon.equals(x.getTraLoi()))
            return true;
        return false;
    }

    public void Finish(View view) {
        linear2.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        ketqua.setVisibility(View.VISIBLE);
        kqd.setText(""+diem);
    }

    public int randomGenerator(int minimum, int maximum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum =  rn.nextInt(range) + minimum;
        return randomNum;
    }

}
