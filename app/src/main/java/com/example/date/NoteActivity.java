package com.example.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    String content;
    String date1;
    List<String> data = new ArrayList<String>();

    List<RateItem> rateList = new ArrayList<RateItem>();
    private final String TAG = "Add";
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ListView listView = (ListView) findViewById(R.id.notelist);
//        SharedPreferences sp = getSharedPreferences("mydate", MODE_PRIVATE);
//        content = sp.getString("content", "");
//        date1 = sp.getString("date", "");
//        Log.i(TAG, "onCreate: content = " + content);
//        Log.i(TAG, "onCreate: url= " + date1);

        //data.add(content+"-->心得："+date1);
        //rateList.add(new RateItem(content, date1));
        RateManager manager = new RateManager(this);

       // manager.addAll(rateList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        for (RateItem item : manager.listAll()) {
            data.add("标题: "+item.getCurName() + "     心得：" + item.getCurRate());
        }
        //没有内容则显示文本框
        listView.setEmptyView(findViewById(R.id.nodata));
        //点击事件
      //  listView.setOnItemClickListener(this);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.i(TAG, "onItemClick: position" + position);
//        Log.i(TAG, "onItemClick: parent" + parent);
//        adapter.remove(parent.getItemAtPosition(position));
//        adapter.notifyDataSetChanged();
//        RateManager manager = new RateManager(this);
//
//        manager.delete(position);
//        //manager.deleteAll();
//        adapter.remove(data);
//
//        for (RateItem item : manager.listAll()) {
//            data.add("item.getCurName() + "     心得：" + item.getCurRate());
//        }
    }

//    @Override
//    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Log.i(TAG,"onClick: 对话框事件处理");
//               // data.remove(position);
//                adapter.remove(parent.getItemAtPosition(position));
//                adapter.notifyDataSetChanged();
//
//            }
//        })
//                .setNegativeButton("否",null);
//        builder.create().show();
//        Log.i(TAG,"OnItemLongClick:size="+data.size());
//        return true;
//        //return false;
//    }

