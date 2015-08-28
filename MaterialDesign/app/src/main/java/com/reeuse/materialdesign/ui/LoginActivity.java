package com.reeuse.materialdesign.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reeuse.materialdesign.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout userNameTil;
    private TextInputLayout passwordTil;
    private EditText userNameEt;
    private EditText passwordEt;
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userNameTil = (TextInputLayout) findViewById(R.id.til_user_name);
        userNameTil = (TextInputLayout) findViewById(R.id.til_user_name);

        userNameEt = (EditText) findViewById(R.id.et_user_name);
        passwordEt = (EditText) findViewById(R.id.et_password);

        signInBtn = (Button) findViewById(R.id.btn_sign);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    userNameTil.setError("Required field");
                } else if (TextUtils.isEmpty(password)) {
                    passwordTil.setError("Required field");
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
