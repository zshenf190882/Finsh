package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;
    private EditText user;
    private EditText password;
    private EditText password1;
    private String userName, psw, pswAgain, uer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        btn_register = findViewById(R.id.btn_register);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = user.getText().toString().trim();
                psw = password.getText().toString().trim();
                pswAgain = password1.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
                uer = sp.getString("userName", "");

                if (userName.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (psw.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pswAgain.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                } else if (userName.equals(uer)) {
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp2 = getSharedPreferences("loginInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp2.edit();
                    editor.putString("userName", userName);
                    editor.putString("psw", psw);
                    editor.commit();
                    Intent data = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(data);
//
                }
            }
        });
    }
}
