package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btn;
    private final String TAG = "Rate";
    private String uerName,password,uer,pas;
    EditText user_input,password_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }
//    public void btnClick2 (View btn3){
//        openConfig2();
//
//    }
//    private void openConfig2() {
//        Intent config2 = new Intent(this, RegisterActivity.class);
//        startActivity(config2);
//    }

    private void init() {

        btn=findViewById(R.id.btn_login);
        user_input=findViewById(R.id.user_input);
        password_input=findViewById(R.id.password_input);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uerName=user_input.getText().toString().trim();
                password=password_input.getText().toString().trim();
                SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
                uer=sp.getString("userName","");
                pas=sp.getString("psw","");
//                Log.i(TAG,pas);
//                Log.i(TAG,uer);
                if(uerName.length()<=0){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.length()<=0){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;

                }else if(password.equals(pas)&& uerName.equals(uer)){
                    Intent list = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(list);


                }else if((pas!=null||!uerName.equals(uer)||!password.equals(pas))){
                    Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}



