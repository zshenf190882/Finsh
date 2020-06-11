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
    private Button btn_register;//注册按钮
    //用户名，密码，再次输入的密码的控件
    private EditText user, password, password1;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName, psw, pswAgain, uer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置页面布局 ,注册界面
//        setContentView(R.layout.activity_register);
//        //设置此界面为竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        init();
    }

    private void init() {

        btn_register = findViewById(R.id.btn_register);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                userName = user.getText().toString().trim();
                psw = password.getText().toString().trim();
                pswAgain = password1.getText().toString().trim();
                SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
                uer = sp.getString("userName", "");


                //判断输入框内容
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
                    /**
                     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                     */
                } else if (userName.equals(uer)) {
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面
                    /**
                     * 保存账号和密码到SharedPreferences中
                     */
                    SharedPreferences sp2 = getSharedPreferences("loginInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp2.edit();
                    editor.putString("userName", userName);
                    editor.putString("psw", psw);
                    editor.commit();
//                    saveRegisterInfo(userName, psw);
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    RegisterActivity.this.finish();
                }
            }
        });
    }
}
