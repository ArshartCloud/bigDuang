package com.example.hope.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.models.DataController;
import com.example.models.Movie;
import com.example.hope.myapplication.Cinema;
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
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieinfo);
        List<Cinema> list = new ArrayList<Cinema>(); // 获取数据
        // 影院信息
        for (int i = 0; i < 10; i++) {
            Cinema test = new Cinema();
            test.setcinemaName("金逸影城");
            test.setaddress("番禺区");
            test.setcity("广州");
            list.add(test);
        }
        CinemaAdapter adapter = new CinemaAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(Activity_Movieinfo.this, Activity_Cinemainfo.class);
                startActivity(intent);
            }
        });



    }



    Runnable pullMovie = new Runnable() {

        @Override
        public void run() {
            String baseURL = "http://115.28.84.73:8080/BigDuang/Cinema";
            String result = "null";
            String TAG = "pullCinema";
            Log.i(TAG, "run: ");
            try{
                HttpGet httpGet = new HttpGet(baseURL+DataController.GetInstance().getSelectedMovie().getId());
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
                    c.setcinemaId(jo.getInt("id"));
                    c.setcinemaName(jo.getString("name"));
                    c.setcity(jo.getString("city"));
                    c.setaddress(jo.getString("address"));
                    DataController.GetInstance().getCinemas().add(c);
                }
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
