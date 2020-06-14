package com.example.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity  {
    Button btn1;
    Button btn2 ;
    Button btn3;
    Button btn4;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnote,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btn1= (Button)findViewById(R.id.button8);
        btn2= (Button)findViewById(R.id.book2);
        btn3= (Button)findViewById(R.id.book3);
        btn4= (Button)findViewById(R.id.zhuan);






    }
    public void btnClick(View btn1){
        Intent config = new Intent(this, ReaderActivity.class);
        startActivity(config);

    }
    public void btnClick2(View btn2){
        Intent config = new Intent(this, Reader2Activity.class);
        startActivity(config);

    }
    public void btnClick3(View btn3){
        Intent config = new Intent(this, Reader3Activity.class);
        startActivity(config);

    }
    public void btnClick4(View btn4){
        Intent config = new Intent(this, NoteActivity.class);
        startActivity(config);

    }





}
