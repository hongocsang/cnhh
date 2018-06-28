package com.example.vuphu.project_hoa_hoc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SupportActivity extends AppCompatActivity {


    private ListView listGioiThieu;
    private String[] list = {"Cẩm nang hóa học trên Facebook",
            "Website: camnanghoahoc.com  ", "Email góp ý: camnanghoahoc@gmail.com"};
    private static String facebook = "https://www.facebook.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        listGioiThieu = (ListView) findViewById(R.id.list_support);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listGioiThieu.setAdapter(adapter);
        listGioiThieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        facebook();
                        break;
                    case 1:
                        website();
                        break;
                    case 2:
                        email();
                        break;
                }
            }
        });
    }

    public void email()  {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL , "phuongtinhbien12@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi góp ý");
        PackageInfo info = null;
        try {
            info = this.getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        intent.putExtra(Intent.EXTRA_TEXT, "Góp ý về cho phiên bản "+info.versionName.toString());
       // intent.setType("message/rfc822");
//        Intent chooser = Intent.createChooser(intent, "Gửi mail với");
//        startActivity(chooser);
        //Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: phuongtinhbien12@gmail.com"));
        startActivity(Intent.createChooser(intent, "Phản hồi với"));

    }

    public void facebook() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
        Intent chooser = Intent.createChooser(intent, "Mở với");
        startActivity(chooser);
    }

    public void website() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
        Intent chooser = Intent.createChooser(intent, "Mở với");
        startActivity(chooser);
    }
}
