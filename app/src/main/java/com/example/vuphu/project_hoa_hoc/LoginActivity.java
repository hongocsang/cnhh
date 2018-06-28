package com.example.vuphu.project_hoa_hoc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;

import static android.content.DialogInterface.*;

public class LoginActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Login";
    private EditText edt_loginEmail, edt_loginPass;
    private String email,password;
    public ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edt_loginEmail = (EditText) findViewById(R.id.login_edtEmail);
        edt_loginPass = (EditText) findViewById(R.id.login_edtPass);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        email = edt_loginEmail.getText().toString();
        password = edt_loginPass.getText().toString();
        if (!isInternetOn(getApplicationContext())){
            Toast.makeText(this, "Không có kết nối INTERNET", Toast.LENGTH_SHORT).show();
        }
        else
        if (validateForm()) {
            showProgressDialog();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        hideProgressDialog();
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user.isEmailVerified())
                            updateUI(user);
                        else{
                            updateUI(null);
                            Toast.makeText(LoginActivity.this, "Tài khoản chưa kích hoạt", Toast.LENGTH_SHORT).show();
                            mAuth.getCurrentUser().sendEmailVerification();
                            mAuth.signOut();
                        }
                    }
                    if (!task.isSuccessful()) {
                        hideProgressDialog();
                        if (!checkEmailNotAvailable()) {
                            hideProgressDialog();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Mật khẩu/ tài khoản sai",
                                    Toast.LENGTH_SHORT).show();
                    }

                    // [END_EXCLUDE]
                }
            });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

        }
    }


    private boolean validateForm() {
        boolean valid = true;

        email = edt_loginEmail.getText().toString();
        password = edt_loginPass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edt_loginEmail.setError("Nhập email để đăng nhập");
            valid = false;
        } else if(!email.contains("@")){
            edt_loginEmail.setError("Không phải email\nNhập lại");
            valid=false;
        } else{
            edt_loginEmail.setError(null);
        }

        password = edt_loginPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            edt_loginPass.setError("Nhập mật khẩu");
            valid = false;
        } else {
            edt_loginPass.setError(null);
        }
        return valid;
    }
    private boolean checkEmailNotAvailable(){
        email = edt_loginEmail.getText().toString();

        final boolean[] available = new boolean[1];

        mAuth.fetchProvidersForEmail(email).addOnCompleteListener(this, new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                if(task.isSuccessful()){
                    available[0] = !task.getResult().getProviders().isEmpty();
                }
            }
        });

        return available[0];
    }

    public void setDialog(Context context){
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Không có kết nối mạng");
        dialog.setMessage("Kiểm tra wifi hoặc 3G/4G máy của bạn...");
        dialog.setIcon(android.R.drawable.alert_light_frame);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            } });
        dialog.show();
    }


    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public static boolean isInternetOn(Context context)
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
