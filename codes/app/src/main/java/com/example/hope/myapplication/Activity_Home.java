package com.example.hope.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.zyh.bigduang.Activity_Info;
import com.example.zyh.bigduang.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_Home extends Activity {
    private ListView listView;
    private ImageView mImageView;
    ViewGroup group;
    private List<ImageView> mImageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        List<Movietheatre> list = new ArrayList<Movietheatre>(); // 获取数据

        Movietheatre test = new Movietheatre();
        test.setTheatreDistance(2.33);
        test.setTheatreId(0);
        test.setTheatreMark(4.4);
        test.setTheatrePrice(32.0);
        test.setTheatreName("金逸影城");

        Movietheatre test1 = new Movietheatre();
        test1.setTheatreDistance(2.33);
        test1.setTheatreId(1);
        test1.setTheatreMark(4.4);
        test1.setTheatrePrice(32.0);
        test1.setTheatreName("飞扬影城");

        Movietheatre test2 = new Movietheatre();
        test2.setTheatreDistance(2.33);
        test2.setTheatreId(2);
        test2.setTheatreMark(4.4);
        test2.setTheatrePrice(32.0);
        test2.setTheatreName("映联万和");

        list.add(test);
        list.add(test1);
        list.add(test2);


        TheatreAdapter adapter = new TheatreAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);


        group = (ViewGroup) findViewById(R.id.addlayout);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.bird);
        group.addView(imageView);
        mImageViews = new ArrayList<>();
        mImageViews.add(imageView);

        ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView1.setImageResource(R.drawable.bird);

        group.addView(imageView1);
        mImageViews.add(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView2.setImageResource(R.drawable.bird);

        group.addView(imageView2);
        mImageViews.add(imageView2);

        Button buser = (Button)findViewById(R.id.user_info);
        Button bmovie = (Button)findViewById(R.id.movie_info);
        Button bcinema = (Button)findViewById(R.id.cinema_info);
        buser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_Info.class);
                startActivity(intent);
            }
        });

        bmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_Movieinfo.class);
                startActivity(intent);
            }
        });

        bcinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Home.this, Activity_Cinemainfo.class);
                startActivity(intent);
            }
        });

        // debug ars
        mImageView = imageView;
        new Thread(networkTask).start();
        new Thread(pullMovie).start();

        //
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
//            mEmailView.setError(val);
            mImageViews.get(msg.what).setImageBitmap((Bitmap) msg.obj);
        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            List<String> urls = new ArrayList<>();
            urls.add("http://115.28.84.73:8080/BigDuang/img/second");
            urls.add("http://115.28.84.73:8080/BigDuang/img/first");
            urls.add("http://115.28.84.73:8080/BigDuang/img/forth");
            for (int i = 0; i < 3; ++i) {
                final Bitmap bitmap =
                        getHttpBitmap(urls.get(i));
//从网上取图片
//                mImageViews.get(i).post(new Runnable() {//另外一种更简洁的发送消息给ui线程的方法。
//
//                    @Override
//                    public void run() {//run()方法会在ui线程执行
//                         mImageViews.get(i).setImageBitmap(bitmap);
//                    }
//                });
                handler.obtainMessage(i,bitmap).sendToTarget();
            }
    //        handler.obtainMessage(1,bitmap).sendToTarget();

        }
    };


    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            Log.d("network", url);
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    Runnable pullMovie = new Runnable() {

        @Override
        public void run() {
            String url = 
            HttpGet httpget = new HttpGet()
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
