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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.models.Cinema;
import com.example.models.DataController;
import com.example.models.Movie;
import com.example.zyh.bigduang.Activity_Info;
import com.example.zyh.bigduang.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
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

        // 影院信息
        for (int i = 0; i < 5; i++) {
            Movietheatre test = new Movietheatre();
            test.setTheatreDistance(2.33);
            test.setTheatreId(0);
            test.setTheatreMark(4.4);
            test.setTheatrePrice(32.0);
            test.setTheatreName("金逸影城");
            list.add(test);
        }
        TheatreAdapter adapter = new TheatreAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(Activity_Home.this, Activity_Cinemainfo.class);
                startActivity(intent);
            }
        });

        // 电影信息
        group = (ViewGroup) findViewById(R.id.addlayout);
        mImageViews = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(R.drawable.bird);

            imageView.setPadding(20,20,20,20);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Home.this, Activity_Movieinfo.class);
                    startActivity(intent);
                }
            });
            group.addView(imageView);
            mImageViews.add(imageView);
        }

        // debug ars
        // mImageView = imageView;

        new Thread(pullMovie).start();

        //
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            // UI界面的更新等相关操作
//            mEmailView.setError(val);
            mImageViews.get(msg.what).setImageBitmap((Bitmap) msg.obj);
        }
    };

    Runnable pullImage = new Runnable() {

        @Override
        public void run() {
            List<Movie> movies = DataController.GetInstance().getMovies();
            String baseURL = "http://115.28.84.73:8080/BigDuang/img/";
            for (int i = 0; i < movies.size() && i < mImageViews.size(); ++i) {
                final Bitmap bitmap = getHttpBitmap(baseURL + movies.get(i).getImgName());

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
            String url = "http://115.28.84.73:8080/BigDuang/list";
            String result = "null";
            String TAG = "pullmovie";
            Log.i(TAG, "run: ");
            try{
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                JSONObject jso = new JSONObject(result);
                JSONArray mArr =  jso.getJSONArray("movies");
                JSONArray cArr = jso.getJSONArray("cinema");
                DataController.GetInstance().getMovies().clear();
                for (int i = 0; i < mArr.length(); ++i) {

                    JSONObject jo = mArr.getJSONObject(i);
                    Movie m = new Movie();
                    m.setId(jo.getInt("id"));
                    m.setName(jo.getString("name"));
                    m.setImgName(jo.getString("img"));
                    DataController.GetInstance().getMovies().add(m);
                }
                DataController.GetInstance().getMovies().clear();
                for (int i = 0; i < cArr.length(); ++i) {

                    JSONObject jo = cArr.getJSONObject(i);
                    Cinema c = new Cinema();
                    c.setId(jo.getInt("id"));
                    c.setName(jo.getString("name"));
                    c.setCity(jo.getString("city"));
                    c.setAddress(jo.getString("address"));
                    DataController.GetInstance().getCinemas().add(c);
                }
                new Thread(pullImage).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
