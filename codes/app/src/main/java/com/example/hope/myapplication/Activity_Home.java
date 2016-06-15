package com.example.hope.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.models.Cinema;
import com.example.models.CinemaAdapter;
import com.example.models.DataController;
import com.example.models.Movie;
import com.example.zyh.bigduang.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
    private List<Cinema> list;
    ViewGroup group;
    private List<ImageView> mImageViews;
    CinemaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        DataController.GetInstance().getMovies().clear();
        DataController.GetInstance().getShowtimes().clear();
        DataController.GetInstance().getCinemas().clear();

        list = DataController.GetInstance().getCinemas();
        adapter = new CinemaAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                DataController.GetInstance().setSelectedCinema(list.get(arg2));
                Intent intent = new Intent(Activity_Home.this, Activity_Cinemainfo.class);
                startActivity(intent);
            }
        });

        // movie Information
        group = (ViewGroup) findViewById(R.id.addlayout);
        mImageViews = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setPadding(20,20,20,20);
            final int index = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Movie> ml = DataController.GetInstance().getMovies();
                    DataController.GetInstance().setSelectedMovie(ml.get(index));
                    Intent intent = new Intent(Activity_Home.this, Activity_Movieinfo.class);
                    startActivity(intent);
                }
            });
            group.addView(imageView);
            mImageViews.add(imageView);
        }
        new Thread(pullMovie).start();
    }


    Runnable pullMovie = new Runnable() {

        @Override
        public void run() {
            String url = "http://115.28.84.73:8080/BigDuang/list";
            String result;
            String TAG = "pullmovie";
            try{
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                JSONObject jso = new JSONObject(result);
                JSONArray mArr =  jso.getJSONArray("movies");
                JSONArray cArr = jso.getJSONArray("cinema");
                // transfer data
                DataController.GetInstance().insertCinemaFromJSON(cArr);
                DataController.GetInstance().insertMovieFromJSON(mArr);
                // update to UI
                cinemaHandler.obtainMessage().sendToTarget();
                new Thread(pullImage).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler cinemaHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };

    Runnable pullImage = new Runnable() {

        @Override
        public void run() {
            List<Movie> movies = DataController.GetInstance().getMovies();
            String baseURL = "http://115.28.84.73:8080/BigDuang/img/";
            for (int i = 0; i < movies.size() && i < mImageViews.size(); ++i) {
                final Bitmap bitmap = getHttpBitmap(baseURL + movies.get(i).getImgName());
                movies.get(i).setBitmap(bitmap);
                ImageHandler.obtainMessage(i,bitmap).sendToTarget();
            }
        }
    };

    Handler ImageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            mImageViews.get(msg.what).setImageBitmap((Bitmap) msg.obj);
        }
    };

    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
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
