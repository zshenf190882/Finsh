package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add2Activity extends AppCompatActivity {
    private final String TAG = "Add";
    EditText inp1;
    EditText inp2;
    Button btn2;
    private String in2, in1;
    List<RateItem> rateList = new ArrayList<RateItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        init();
    }

    private void init() {

        inp1 = (EditText) findViewById(R.id.inp1);
        inp2 = (EditText) findViewById(R.id.inp2);
        btn2 = findViewById(R.id.btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in1 = inp1.getText().toString().trim();
                in2 = inp2.getText().toString().trim();

                rateList.add(new RateItem(in1, in2));
                RateManager manager = new RateManager(Add2Activity.this);

                manager.addAll(rateList);
                Toast.makeText(Add2Activity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Intent list = new Intent(Add2Activity.this, UserActivity.class);
                startActivity(list);
            }
        });
    }
}
