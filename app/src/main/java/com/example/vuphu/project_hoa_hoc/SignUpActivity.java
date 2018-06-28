package com.example.vuphu.project_hoa_hoc;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;


public class SignUpActivity extends AppCompatActivity {

    private static final int CODE_PICK = 100;
    private static final int CODE_TAKE_PICTURE = 200;

    private static final String TAG = "SignUp";
    private EditText edt_signupEmail, edt_signupPass;
    //khai báo firebase dang ki
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String email,password;
    public ProgressDialog mProgressDialog;
    private byte[] arrayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        edt_signupEmail = (EditText) findViewById(R.id.signUp_edtEmail);
        edt_signupPass = (EditText) findViewById(R.id.signUp_edtPass);

        mAuth = FirebaseAuth.getInstance();
    }


    public void signUp(View view) {
        if (validateForm() && !checkEmailNotAvailable()) {
            showProgressDialog();
            final Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Đăng kí không thành công
                            if (!task.isSuccessful()) {
                                hideProgressDialog();
                                if (!checkEmailNotAvailable()) {
                                    Snackbar snackBar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),"Tài khoản tồn tại",Snackbar.LENGTH_LONG)
                                            .setAction("Đăng nhập", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    startActivity(intent);
                                                }
                                            });
                                    snackBar.show();
                                }else
                                    Toast.makeText(SignUpActivity.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                            }else {
                                hideProgressDialog();
                                //Đăng kí thành công
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                Snackbar snackBar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),"Xác nhận email",Snackbar.LENGTH_LONG)
                                        .setAction("Xác nhận", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mAuth.getCurrentUser().sendEmailVerification();
                                                startActivity(intent);
                                            }
                                        });
                                snackBar.show();
                                if (mAuth.getCurrentUser().sendEmailVerification().isSuccessful()){
                                    mAuth.signOut();
                                }

                            }
                        }});
        }
    }

    private boolean checkEmailNotAvailable(){
        email = edt_signupEmail.getText().toString();
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

    private boolean validateForm() {
        boolean valid = true;

        email = edt_signupEmail.getText().toString();
        password = edt_signupPass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edt_signupEmail.setError("Nhập email để đăng kí");
            valid = false;
        } else if(!email.contains("@")){
            edt_signupEmail.setError("Không phải email\n Nhập lại");
            valid=false;
        } else{
            edt_signupEmail.setError(null);
        }

        password = edt_signupPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            edt_signupPass.setError("Nhập mật khẩu");
            valid = false;
        } else {
            edt_signupPass.setError(null);
        }

        return valid;
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

//    public void choosePicture(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Thay anh dai dien");
//        builder.setMessage("Chon anh");
//
//        builder.setPositiveButton("Take picture", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CODE_TAKE_PICTURE);
//            }
//        });
//
//        builder.setNegativeButton("Choose picture", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, CODE_PICK);
//            }
//        });
//        Dialog dialog = builder.create();
//        dialog.show();
//    }
//
//    private void pictureDefault() {
//        findViewById(R.id.signup_avatar).setDrawingCacheEnabled(true);
//        findViewById(R.id.signup_avatar).buildDrawingCache();
//        Bitmap bitmap = findViewById(R.id.signup_avatar).getDrawingCache();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        arrayImage = baos.toByteArray();
//    }

}


