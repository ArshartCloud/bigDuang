package com.example.hope.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zyh.bigduang.R;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Handler;

/**
 * Created by hope on 5/29/16.
 */
public class CinemaAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<Cinema> cinemaList;
    private final String inflater = Context.LAYOUT_INFLATER_SERVICE;
    private LayoutInflater layoutInflater;
    private Handler handler;

    private class ViewHolder {
        TextView name;
        TextView city;
        TextView address;
    }

    public CinemaAdapter (Context c, List<Cinema> list) {
        if (null != list) {
            cinemaList= list;
        } else {
            cinemaList= new ArrayList<Cinema>();
        }
        this.context = c;
        layoutInflater = LayoutInflater.from(context);;

        handler = new Handler();

    }

    public void addItem(Cinema item) {
        if (item != null) {
            cinemaList.add(item);
            notifyDataSetChanged();   // 通知适配器数据已改变
        }
    }

    // 添加多个项(自定义方法)

    public void addItem(List<Cinema> list) {
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                cinemaList.add(list.get(i));
            }
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }

    public void removeItem(int position) {
        if (cinemaList.get(position) != null) {
            cinemaList.remove(position);
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }


    @Override
    public int getCount() {
        return cinemaList.size();
    }

    @Override
    public Object getItem(int position) {
        return cinemaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return cinemaList.get(position).getcinemaId();
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            // 装载布局文件 app_item.xml
            convertView = (RelativeLayout) layoutInflater.inflate(R.layout.theatre, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.city = (TextView) convertView.findViewById(R.id.city);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cinema thea = cinemaList.get(position); // 获取当前项数据
        if (null != thea) {
            holder.name.setText(thea.getcinemaName());
            holder.city.setText("所在城市: " + thea.getcity());
            holder.address.setText(thea.getaddress());
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
