package com.example.vuphu.project_hoa_hoc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class KimLoaiActivity extends AppCompatActivity {
    
    RecyclerView listKl;
    private KimLoaiAdapter mAdapter;
    private String[] listkimloai = {  "K\n1+ ",
         "Na\n1+ ",
         "Mg\n2+ ",
         "Al\n3+ ",
         "Zn\n2+ ",
         "Fe\n2+ ",
         "Ni\n2+ ",
         "Sn\n2+ ",
         "Pb\n2+ ",
         "H\n1+ ",
         "Cu\n2+ ",
         "Ag\n1+ ",
         "Au\n2+ "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kim_loai);
        listKl = (RecyclerView) findViewById(R.id.listKl);
        listKl.setHasFixedSize(true);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listKl.setLayoutManager(mManager);
        mAdapter = new KimLoaiAdapter(this, listkimloai);
        listKl.setAdapter(mAdapter);
    }
    
    private static class kimloai extends RecyclerView.ViewHolder{

        TextView kimloai;
        public kimloai(View itemView) {
            super(itemView);
            kimloai = (TextView) itemView.findViewById(R.id.kimLoai);
        }
    }
    
    private class KimLoaiAdapter extends RecyclerView.Adapter<kimloai>{
        private Context mContext;
        private String[] listKL;

        public KimLoaiAdapter(Context mContext, String[] listKL) {
            this.mContext = mContext;
            this.listKL = listKL;
        }

        @Override
        public kimloai onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_kl, parent, false);
            return new kimloai(view);
        }

        @Override
        public void onBindViewHolder(kimloai holder, final int position) {

            holder.kimloai.setText(listKL[position]);
            final ChatHoaHoc chatHoaHoc;
            DatabaseReference mdata = FirebaseDatabase.getInstance().getReference().child("ChatHoaHoc");
            Query query = mdata.child(listKL[position]);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //chatHoaHoc = dataSnapshot.getValue(ChatHoaHoc.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(KimLoaiActivity.this,DetailCHHActivity.class);
                   // intent.putExtra("myObject", new Gson().toJson(chatHoaHoc));
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return listKL.length;
        }
    }
}
