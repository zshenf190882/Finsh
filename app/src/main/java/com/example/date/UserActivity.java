package com.example.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {
    String data[] = {"one","two","three"};
    Handler handler;
    ListView show1;
    private String TAG = "ListActivity";
    private String logDate = "";
    private final  String DATE_SP_KEY = "lastRateDateStr";
    private  ListView listView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnote,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user); //因为父类已经包含一个布局，不需要再一个布局
        listView = (ListView) findViewById(R.id.mylist);
        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        logDate = sp.getString(DATE_SP_KEY, "");
        Log.i("List", "lastRateDateSt" + logDate);


        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        listView.setAdapter(adapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 7){
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(UserActivity.this,android.R.layout.simple_list_item_1,list2);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);

            }
        };listView.setOnItemClickListener(this);

    }

    @Override
    public void run() {
        //获取网络数据，放入list带回主线程中
        List<String> retList = new ArrayList<String>();
        String curDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Log.i("run","curDateStr"+curDateStr+"logDate"+logDate);

        if(curDateStr.equals(logDate)){
            Log.i("run","日期相等，从数据库中获取数据");
            RateManager manager = new RateManager(this);
            for(RateItem item:manager.listAll()){
                retList.add(item.getCurName());
                Log.i("run",item.getCurRate());
            }
        }else {
            Log.i("run","日期不相等，从网络中获取数据");
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.duxieren.com/shanghaishuping/").get();
                Log.i(TAG,"run:"+doc.title());
                Elements tables = doc.getElementsByTag("table");
                Element table1 = tables.get(0);
                Elements tds = table1.getElementsByTag("td");

                List<RateItem> rateList = new ArrayList<RateItem>();
                for (int i =0;i<tds.size();i+=2){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+1);
                    String url0 = tds.get(i+1).select("a").attr("href");
                    Log.i(TAG,"run: url["+i+"]=" + url0);
                    Log.i(TAG,"run:"+td1.text()+"==>"+td2.text());
                    String str1 = td1.text();
                    String val = td2.text();
                    String ss=str1+val;
                    retList.add(str1+"==>"+val);
                   // retList.add(url0);
                    rateList.add(new RateItem(ss,url0));
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ItemTitle", ss);
                    map.put("ItemDetail", url0);
                    // rateList.add(new RateItem("URL",url0));

                }

                //把数据写入到数据库中
                RateManager manager = new RateManager(this);
                manager.deleteAll();
                manager.addAll(rateList);
                Log.i("run","数据已写入数据库中："+curDateStr);

                //更新记录日期
                SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(DATE_SP_KEY,curDateStr);
                edit.commit();
                Log.i("run","更新日期结束："+curDateStr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Message msg  = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RateManager manager = new RateManager(this);

        switch (position) {

            case 0:

                break;
            case 1:



                break;

            case 2:



                break;





        }
//        Log.i(TAG,"onItemClick: parent="+parent);
//        Log.i(TAG,"onItemClick: view="+view);
//        Log.i(TAG,"onItemClick: position="+position);
//        Log.i(TAG,"onItemClick: id="+id);
//        //RateManager manager = new RateManager(this);
//        RateManager manager=listView.getPosition()
//        Bundle bundle = new Bundle();
//        bundle.putInt("xu", manager.get(position).getId());
//        bundle.putString("name", manager.get(position).getName());
//        bundle.putString("xi", manager.get(position).getXibie());
//        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }

//        parent.getAdapter().getItem(position);
//        Adapter adapter=parent.getAdapter();
//       HashMap<String,String> map=(HashMap<String,String>)listView.getItemAtPosition(position);
//        String titleStr=map.get("ItemTitle");
//        String detailStr =map.get("ItemDetail");
//        Log.i(TAG,"onItemClick: titleStr="+titleStr);
//        Log.i(TAG,"onItemClick: detailStr="+detailStr);
//        RateManager manager = new RateManager(this);
//        List<String> retList = new ArrayList<String>();




    }

}
