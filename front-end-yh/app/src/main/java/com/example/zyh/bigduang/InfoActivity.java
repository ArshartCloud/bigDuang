package com.example.zyh.bigduang;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {
    private List<Map<String, Object>> mData;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        mListView = (ListView)findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setItemsCanFocus(false);

    }

    private  void init() {
        mData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < 4; i++) {
            map = new HashMap<String, Object>();
            map.put("movie", "北京遇上西雅图hhhhhhhhhhhhhh");
            map.put("cinema", "珠江新城");
            map.put("time", "2015-09-08");
            map.put("amount", "2张");
            map.put("price", "128元");
            map.put("state", "已完成");
            mData.add(map);
        }
    }

    public final class ViewHolder {
        public TextView movie;
        public TextView cinema;
        public TextView time;
        public TextView amount;
        public TextView price;
        public TextView state;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            //View tmp = mInflater.inflate(R.layout.content_info, null);
            //mListView=(ListView)tmp.findViewById(R.id.lv);
            this.mInflater = LayoutInflater.from(context);
            init();
        }

        @Override
        public  int getCount() {
            return mData.size();
        }

        @Override
        public  Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public  View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView==null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.content_info, null);
                holder.movie=(TextView)convertView.findViewById(R.id.movie);
                holder.cinema=(TextView)convertView.findViewById(R.id.cinema);
                holder.time=(TextView)convertView.findViewById(R.id.time);
                holder.amount=(TextView)convertView.findViewById(R.id.amount);
                holder.price=(TextView)convertView.findViewById(R.id.price);
                holder.state=(TextView)convertView.findViewById(R.id.state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.movie.setText((String)mData.get(position).get("movie"));
            holder.cinema.setText((String)mData.get(position).get("cinema"));
            holder.time.setText((String)mData.get(position).get("time"));
            holder.amount.setText((String)mData.get(position).get("amount"));
            holder.price.setText((String)mData.get(position).get("price"));
            holder.state.setText((String)mData.get(position).get("state"));
            return convertView;
        }
    }

}
