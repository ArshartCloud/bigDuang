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
public class TheatreAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<Movietheatre> theatreList;
    private final String inflater = Context.LAYOUT_INFLATER_SERVICE;
    private LayoutInflater layoutInflater;
    private Handler handler;

    private class ViewHolder {
        TextView name;
        TextView mark;
        TextView distance;
        TextView price;
    }

    public TheatreAdapter (Context c, List<Movietheatre> list) {
        if (null != list) {
            theatreList= list;
        } else {
            theatreList= new ArrayList<Movietheatre>();
        }
        this.context = c;
        layoutInflater = LayoutInflater.from(context);;

        handler = new Handler();

    }

    public void addItem(Movietheatre item) {
        if (item != null) {
            theatreList.add(item);
            notifyDataSetChanged();   // 通知适配器数据已改变
        }
    }

    // 添加多个项(自定义方法)

    public void addItem(List<Movietheatre> list) {
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                theatreList.add(list.get(i));
            }
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }

    public void removeItem(int position) {
        if (theatreList.get(position) != null) {
            theatreList.remove(position);
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }


    @Override
    public int getCount() {
        return theatreList.size();
    }

    @Override
    public Object getItem(int position) {
        return theatreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return theatreList.get(position).getTheatreId();
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
            holder.mark = (TextView) convertView.findViewById(R.id.mark);
            holder.distance = (TextView) convertView.findViewById(R.id.distance);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Movietheatre thea = theatreList.get(position); // 获取当前项数据
        if (null != thea) {
            holder.name.setText(thea.getTheatreName());
            holder.mark.setText("Mark: " + Double.toString(thea.getTheartreMark()) + "/5");
            holder.distance.setText(Double.toString(thea.getTheatreDistance()));
            holder.price.setText("Price: $" + Double.toString(thea.getTheatrePrice()));

        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
