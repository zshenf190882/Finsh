package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =(Button)findViewById(R.id.btn_login);
        Button btn2 =(Button)findViewById(R.id.btn_register);

    }public void btnClick(View btn){
        openConfig();

    }

    private void openConfig() {
        Intent config = new Intent(this, LoginActivity.class);
        startActivity(config);
    }
}
