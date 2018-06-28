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
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailKienThucActivity extends AppCompatActivity {



    private List<String> chuong;
    private RecyclerView list_Chuong;
    private int lop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kien_thuc);
        Intent intent = getIntent();
        lop = Integer.parseInt(intent.getStringExtra("lop"));
        setTitle("Lớp " + lop);
        list_Chuong = (RecyclerView) findViewById(R.id.list_Chuong);
        list_Chuong.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list_Chuong.setLayoutManager(manager);
        chuong = new ArrayList<>();
        createListChuong();
        ListChuongAdapter adapter = new ListChuongAdapter(this, chuong);
        list_Chuong.setAdapter(adapter);
    }

    private static class Chuong extends RecyclerView.ViewHolder{

        TextView sttChuong, tenChuong;
        CircleImageView imgChuong;
        public Chuong(View itemView) {
            super(itemView);

            sttChuong = (TextView) itemView.findViewById(R.id.txtSoChuong);
            tenChuong = (TextView) itemView.findViewById(R.id.txt_tenChuong);
            imgChuong = (CircleImageView) itemView.findViewById(R.id.img_Chuong);
        }
    }

    private class ListChuongAdapter extends RecyclerView.Adapter<Chuong>{

        Context mContext;
        List<String> listChuong;

        public ListChuongAdapter(Context mContext, List<String> listChuong) {
            this.mContext = mContext;
           this.listChuong = listChuong;
        }

        @Override
        public Chuong onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_chuong, parent, false);
            return new Chuong(view);
        }

        @Override
        public void onBindViewHolder(Chuong holder, final int position) {

            holder.imgChuong.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            holder.tenChuong.setText(listChuong.get(position));
            final int i = position+1;
            holder.sttChuong.setText(""+i);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailKienThucActivity.this,NoiDungChuongActivity.class);
                    intent.putExtra("lop",lop);
                    intent.putExtra("chuong",listChuong.get(position));
                    intent.putExtra("sttChuong", i);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return listChuong.size();
        }
    }

    public void createListChuong(){
        if(lop==8){
            chuong = Arrays.asList(getResources().getStringArray(R.array.Lop_8));
        }
        if(lop==9){
            chuong = Arrays.asList(getResources().getStringArray(R.array.Lop_9));
        }
        if(lop==10){
            chuong = Arrays.asList(getResources().getStringArray(R.array.Lop_10));
        }
        if(lop==11){
            chuong = Arrays.asList(getResources().getStringArray(R.array.Lop_11));
        }
        if(lop==12){
            chuong = Arrays.asList(getResources().getStringArray(R.array.Lop_12));
        }

    }



}
