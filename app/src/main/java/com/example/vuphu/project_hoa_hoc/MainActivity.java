package com.example.vuphu.project_hoa_hoc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener{

    private CircleImageView avatar;
    private TextView userName,email;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);

//        avatar = (CircleImageView) view.findViewById(R.id.main_avatar);
//        userName = (TextView) view.findViewById(R.id.main_username);
//        email = (TextView) view.findViewById(R.id.main_email);
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        Log.i("email", user.getEmail());
//        Uri uri = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
//        String name = user.getDisplayName().toString();
//        String em = user.getEmail();
//        Log.i("user", String.valueOf(user));
//        if (user != null ) {
//            Picasso.with(MainActivity.this).load(uri).into(avatar);
//            userName.setText(name);
//            email.setText(em);
//        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.menu_log_out) {
//            FirebaseAuth.getInstance().signOut();
//            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(@NonNull Status status) {
//                            startActivity(new Intent(MainActivity.this,LoginGoogle.class));
//                            finish();
//                        }
//                    });
//            return true;
//        }
        if (id == R.id.menu_add_cau_hoi){
            startActivity(new Intent(MainActivity.this,ThemCauHoi.class));
            return true;
        }
        if (id == R.id.menu_tinhTan){
            startActivity(new Intent(MainActivity.this,TinhTanActivity.class));
            return true;
        }
        if(id == R.id.menu_KimLoai){
            startActivity(new Intent(MainActivity.this,KimLoaiActivity.class));
            return true;
        }

        if (id == R.id.menu_chat_hoa_hoc){
            startActivity(new Intent(MainActivity.this,ChatHoaHocActivity.class));
            return true;
        }

        if (id == R.id.menu_add_bai_tap){
            startActivity(new Intent(MainActivity.this,ThemBaiTapActivity.class));
            return true;
        }
        if (id == R.id.menu_info){
            startActivity(new Intent(MainActivity.this, SupportActivity.class));
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void tracNghiem(View view) {
        Intent intent = new Intent(MainActivity.this,TracNghiemActivity.class);
        startActivity(intent);

    }

    public void bangTuanHoan(View view) {
        startActivity(new Intent(MainActivity.this,BangTuanHoanActivity.class));

    }

    public void kienThuc(View view) {
        startActivity(new Intent(MainActivity.this,KienThucActivity.class));
    }

    public void CanBang(View view) {
        startActivity(new Intent(MainActivity.this,CanBangActivity.class));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Kết nối mạng bị mất", Toast.LENGTH_SHORT).show();
    }

}
