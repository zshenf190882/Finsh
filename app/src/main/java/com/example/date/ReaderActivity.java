package com.example.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReaderActivity extends ListActivity implements Runnable, AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    Handler handler;
    private final String TAG = "mylist2";
    private List<HashMap<String, String>> listItems;//存放文字，图片信息
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);


        //MyAdapter myAdapter =new MyAdapter(this,R.layout.list_item,listItems);
        //this.setListAdapter(myAdapter);
        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 7) {
                    listItems= (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(ReaderActivity.this, listItems,//listTtems数据源
                            R.layout.list_reader,//ListItem的XML布局实现
                            new String[]{"ItemTitle", "ItemDetail"},
                            new int[]{R.id.itemTitle, R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);
                }

                super.handleMessage(msg);
            }
        };
        //getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //  @Override
        // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //  }
        //});
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);

    }



    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "content  " + i);//标题文字
            map.put("ItemDetail", "url" + i);//详细描述
            listItems.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems,//listTtems数据源
                R.layout.list_reader,//ListItem的XML布局实现
                new String[]{"ItemTitle", "ItemDetail"},
                new int[]{R.id.itemTitle, R.id.itemDetail}
        );
    }


    @Override
    public void run() {
//获取网络数据，放入list带回到到主线程中
        List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            Thread.sleep(10);
            doc = Jsoup.connect("https://www.duxieren.com/shanghaishuping/").get();
            Log.i(TAG, "run:" + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table1 = tables.get(0);
            Elements tds = table1.getElementsByTag("td");
           // List<RateItem> rateList = new ArrayList<RateItem>();
            for (int i =0;i<tds.size();i+=2){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+1);
                String url0 = tds.get(i+1).select("a").attr("href");
                Log.i(TAG,"run: url["+i+"]=" + url0);
                Log.i(TAG,"run:"+td1.text()+"==>"+td2.text());
                String str1 = td1.text();
                String val = td2.text();
                String ss=str1+val;

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemTitle", ss);
                map.put("ItemDetail", url0);
                retList.add(map);
               // rateList.add(new RateItem(ss,url0));
//
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }
//    public void btnClick(View btn){
//        if()
//        openConfig();
//
//    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String,String> map=(HashMap<String,String>) getListView().getItemAtPosition(position);
        String titleStr=map.get("ItemTitle");
        String detailStr =map.get("ItemDetail");
        Log.i(TAG,"onItemClick: titleStr="+titleStr);
        Log.i(TAG,"onItemClick: detailStr="+detailStr);

        TextView title=(TextView)view.findViewById(R.id.itemTitle);
        TextView detail=(TextView)view.findViewById(R.id.itemDetail);
        String content=String.valueOf(title.getText());
        String url=String.valueOf(detail.getText());
        Uri uri = Uri.parse((String) url);
        Log.i(TAG,"onItemClick: content="+content);
        Log.i(TAG,"onItemClick:url="+url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG,"OnItemLongClick:长按列表项position"+position);

        HashMap<String,String> map=(HashMap<String,String>) getListView().getItemAtPosition(position);
        String titleStr=map.get("ItemTitle");
        String detailStr =map.get("ItemDetail");
        Log.i(TAG,"onItemClick: titleStr="+titleStr);
        Log.i(TAG,"onItemClick: detailStr="+detailStr);

        TextView title=(TextView)view.findViewById(R.id.itemTitle);
        TextView detail=(TextView)view.findViewById(R.id.itemDetail);
        String content=String.valueOf(title.getText());
        String url=String.valueOf(detail.getText());
       // Uri uri = Uri.parse((String) url);
        SharedPreferences sharedPreferences = getSharedPreferences("mydate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("content",content);
        editor.commit();
      Intent add=new Intent(this,AddActivity.class);
      add.putExtra("content",content);
      add.putExtra("Url",url);
      startActivity(add);
        return true;

    }
}
