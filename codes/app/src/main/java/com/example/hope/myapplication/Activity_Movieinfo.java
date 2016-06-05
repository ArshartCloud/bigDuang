package com.example.hope.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

public class Activity_Movieinfo extends Activity {
    private ListView listView;
    private CinemaAdapter adapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieinfo);
        List<Cinema> list = DataController.GetInstance().getCinemas(); // 获取数据
        // 电影信息
        ImageView movieImage = (ImageView)findViewById(R.id.imageView2);
        TextView movieName = (TextView)findViewById(R.id.textView11);
        TextView grade = (TextView)findViewById(R.id.grade);
        TextView describe = (TextView)findViewById(R.id.grade);

        Movie movie = DataController.GetInstance().getSelectedMovie();
        movieImage.setImageBitmap(movie.getBitmap());
        movieName.setText(movie.getName());


        // 影院信息
        adapter = new CinemaAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(Activity_Movieinfo.this, Activity_Cinemainfo.class);
                startActivity(intent);
            }
        });

        new Thread(pullCinema).start();

    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            String TAG = "chandler";
            adapter.notifyDataSetChanged();
        }
    };


    Runnable pullCinema = new Runnable() {

    @Override
        public void run() {
            String baseurl = "http://115.28.84.73:8080/BigDuang/cinema/";
            String result = "null";
            String TAG = "pullCinema";
            int movieID = DataController.GetInstance().getSelectedMovie().getId();
            Log.i(TAG, "run: ");
            try{
                String url = baseurl + String.valueOf(movieID);
                Log.i(TAG, url);
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                Log.i(TAG, result);
                JSONObject jso = new JSONObject(result);
                JSONArray jArr = jso.getJSONArray("cinemas");
                Log.i(TAG, jArr.toString());
                DataController.GetInstance().insertCinemaFromJSON(jArr);
                Log.i(TAG, "run: ");
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
