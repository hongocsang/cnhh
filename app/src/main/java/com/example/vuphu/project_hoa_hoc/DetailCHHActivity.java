package com.example.vuphu.project_hoa_hoc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailCHHActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static String API = "AIzaSyDqhyUoBDO9yO9wkYZCa-xQiU2sHD5tVp4";

    private ChatHoaHoc chatHoaHoc;
    private TextView kiHieu, name, hoaTri, nguyenTuKhoi, title, phanLoai, txt_ChiTiet;
    private YouTubePlayerView videoView;
    private Toolbar toolbar;
    private CircleImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chh);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title2);


        Bundle extras = getIntent().getExtras();
        String jsonMyObject = null;
        if (extras != null) {

            jsonMyObject = extras.getString("myObject");
        }
        chatHoaHoc = new Gson().fromJson(jsonMyObject, ChatHoaHoc.class);
        title.setText(chatHoaHoc.getName());
        videoView = (YouTubePlayerView) findViewById(R.id.videoView);
        kiHieu = (TextView) findViewById(R.id.txtkiHieu1);
        name = (TextView) findViewById(R.id.txt_tenCHH1);
        hoaTri = (TextView) findViewById(R.id.txt_HoaTri);
        nguyenTuKhoi = (TextView) findViewById(R.id.txt_NTK);
        phanLoai = (TextView) findViewById(R.id.txt_phanLoai);
        txt_ChiTiet = (TextView) findViewById(R.id.txt_chiTiet);
        img = (CircleImageView) findViewById(R.id.img_CHH_detail);
        setValue();

    }

    public void setValue(){
        if (chatHoaHoc.getPhanLoai().equals("Phi Kim")){
            img.setBackgroundResource(R.color.phiKim);
        }
        if (chatHoaHoc.getPhanLoai().equals("Kim Loại")){
            img.setBackgroundResource(R.color.kimLoai);
        }
        if (chatHoaHoc.getPhanLoai().equals("Khí Hiếm")){
            img.setBackgroundResource(R.color.khiHiem);
        }
        name.setText(chatHoaHoc.getName());
        hoaTri.setText("Hóa trị: "+chatHoaHoc.getHoaTri());
        kiHieu.setText(chatHoaHoc.getKiHieu());
        nguyenTuKhoi.setText("Nguyên tử khối: "+chatHoaHoc.getNguyenTuKhoi());
        phanLoai.setText("Phân loại: "+chatHoaHoc.getPhanLoai());
        txt_ChiTiet.setText("Trạng thái vật chất:  " + chatHoaHoc.getTrangThaiVatChat()+"\n\n"+
                            "Cấu hình Electron:  "+ chatHoaHoc.getCauHinhElectron()+"\n\n"+
                            "Độ âm điện:  "+ chatHoaHoc.getDoAmDien());
        videoView.initialize(API, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(chatHoaHoc.getVideo());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public void back(View view) {
        startActivity(new Intent(DetailCHHActivity.this, ChatHoaHocActivity.class));
    }
}
