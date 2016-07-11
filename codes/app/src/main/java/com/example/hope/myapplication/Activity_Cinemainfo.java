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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.models.Cinema;
import com.example.models.DataController;
import com.example.models.Movie;
import com.example.models.Showtime;
import com.example.models.ShowtimeAdapter;
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

public class Activity_Cinemainfo extends Activity {
    private ListView listView;
    private List<ImageView> mImageViews;
    ShowtimeAdapter adapter;
    List<Showtime> list;
    ViewGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemainfo);
        DataController.GetInstance().getMovies().clear();
        DataController.GetInstance().getShowtimes().clear();
        DataController.GetInstance().getCinemas().clear();

        // cinema Info
        TextView cinemaName = (TextView)findViewById(R.id.textView3);
        TextView address = (TextView)findViewById(R.id.addressView);

        Cinema cinema = DataController.GetInstance().getSelectedCinema();
        cinemaName.setText(cinema.getName());
        address.setText(cinema.getAddress());

        // showtime Info
        list = DataController.GetInstance().getShowtimes(); // 获取数据

        adapter = new ShowtimeAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                DataController.GetInstance().setSelectedShowtime(list.get(arg2));
                Intent intent = new Intent(Activity_Cinemainfo.this, Activity_Seat.class);
                startActivity(intent);
            }
        });

        // movie Info
        group = (ViewGroup) findViewById(R.id.addlayout);
        mImageViews = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setPadding(20,20,20,20);
            final int index = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Movie> ml = DataController.GetInstance().getMovies();
                    Log.i("233","6666");
                    DataController.GetInstance().setSelectedMovie(ml.get(index));
                    new Thread(pullShowtime).start();
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
            String baseurl = "http://115.28.84.73:8080/BigDuang/movie/";
            String result = "null";
            String TAG = "pullMovie";
            int cinemaID = DataController.GetInstance().getSelectedCinema().getId();
            try{
                String url = baseurl + String.valueOf(cinemaID);
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                JSONObject jso = new JSONObject(result);
                JSONArray jArr = jso.getJSONArray("movies");
                DataController.GetInstance().insertMovieFromJSON(jArr);
                new Thread(pullImage).start();
                Movie selectedMovie = DataController.GetInstance().getSelectedMovie();
                if (selectedMovie == null ) {//|| !DataController.GetInstance().getMovies().contains(selectedMovie)
                    DataController.GetInstance().setSelectedMovie(DataController.GetInstance().getMovies().get(0));
                }
                new Thread(pullShowtime).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler movieHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
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
                movies.get(i).setBitmap(bitmap);
                movieHandler.obtainMessage(i,bitmap).sendToTarget();
            }
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

    Runnable pullShowtime = new Runnable() {

        @Override
        public void run() {
            String baseurl = "http://115.28.84.73:8080/BigDuang/showtime/";
            String result = "null";
            String TAG = "pullShowtime";
            int movieID = DataController.GetInstance().getSelectedMovie().getId();
            int cinemaID = DataController.GetInstance().getSelectedCinema().getId();
            try{
                String url = baseurl + String.valueOf(cinemaID) + '/' + String.valueOf(movieID);
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                JSONObject jso = new JSONObject(result);
                JSONArray jArr = jso.getJSONArray("showtimes");
                DataController.GetInstance().insertShowtimeFromJSON(jArr);
                showtimeHandler.obtainMessage(1).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler showtimeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
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

    // forbid back
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Cinemainfo.this, Activity_Home.class);
        startActivity(intent);
        return;
    }
}
