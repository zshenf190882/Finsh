package com.example.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    Handler handler;
    private final String TAG = "Add";
    EditText inp1;
    Button btn1;
    private String inp, cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        init();

    }

    private void init() {
        final String content = getIntent().getStringExtra("content");
        String url = getIntent().getStringExtra("Url");
        Log.i(TAG, "onCreate: content = " + content);
        Log.i(TAG, "onCreate: url= " + url);
        ((TextView) findViewById(R.id.add1)).setText(content);
        inp1 = (EditText) findViewById(R.id.inp2);
        btn1 = findViewById(R.id.but);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inp = inp1.getText().toString().trim();
                cont = content;
                SharedPreferences sharedPreferences = getSharedPreferences("mydate", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("date", String.valueOf(inp));
                //editor.putString("content", cont);
                editor.commit();
                Log.i(TAG, "shujuyibaocun");
                Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Intent list = new Intent(AddActivity.this, UserActivity.class);
                startActivity(list);
            }
        });


    }
}