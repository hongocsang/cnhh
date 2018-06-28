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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class KienThucActivity extends AppCompatActivity {


    private RecyclerView listClass;
    private String []clas ={"8","9","10","11","12"};
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kien_thuc);
        listClass = (RecyclerView) findViewById(R.id.list_class);
        listClass.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        listClass.setLayoutManager(manager);
        ListClassAdapter adapter = new ListClassAdapter(this,clas);
        listClass.setAdapter(adapter);
    }

    private static class Class extends RecyclerView.ViewHolder{

        TextView sttLop, tenLop;
        CircleImageView imgLop;
        public Class(View itemView) {
            super(itemView);

            sttLop = (TextView) itemView.findViewById(R.id.txtkiHieu);
            tenLop = (TextView) itemView.findViewById(R.id.txt_tenCHH);
            imgLop = (CircleImageView) itemView.findViewById(R.id.img_CHH);
        }
    }

        private class ListClassAdapter extends RecyclerView.Adapter<Class>{

        Context mContext;
        String[] sttClass;

        public ListClassAdapter(Context mContext, String[] sttClass) {
            this.mContext = mContext;
            this.sttClass = sttClass;
        }

        @Override
        public Class onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item, parent, false);
            return new Class(view);
        }

        @Override
        public void onBindViewHolder(Class holder, final int position) {

            //holder.imgLop.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                holder.tenLop.setText("Lớp "+sttClass[position]);
            holder.sttLop.setText(sttClass[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(KienThucActivity.this,DetailKienThucActivity.class);
                    intent.putExtra("lop",sttClass[position]);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return sttClass.length;
        }
    }
}
