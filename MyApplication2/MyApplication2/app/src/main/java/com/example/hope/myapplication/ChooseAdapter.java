package com.example.hope.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Handler;

/**
 * Created by hope on 5/29/16.
 */
public class ChooseAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<ChooseItem> screenList;
    private final String inflater = Context.LAYOUT_INFLATER_SERVICE;
    private LayoutInflater layoutInflater;
    private Handler handler;

    private class ViewHolder {
        TextView time;
        TextView description;
        TextView price;
        Button mybu;
    }

    public ChooseAdapter (Context c, List<ChooseItem> list) {
        if (null != list) {
            screenList= list;
        } else {
            screenList= new ArrayList<ChooseItem>();
        }
        this.context = c;
        layoutInflater = LayoutInflater.from(context);;

        handler = new Handler();

    }

    public void addItem(ChooseItem item) {
        if (item != null) {
            screenList.add(item);
            notifyDataSetChanged();   // 通知适配器数据已改变
        }
    }

    // 添加多个项(自定义方法)

    public void addItem(List<ChooseItem> list) {
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                screenList.add(list.get(i));
            }
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }

    public void removeItem(int position) {
        if (screenList.get(position) != null) {
            screenList.remove(position);
            notifyDataSetChanged();  // 通知适配器数据已改变
        }
    }


    @Override
    public int getCount() {
        return screenList.size();
    }

    @Override
    public Object getItem(int position) {
        return screenList.get(position);
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
            convertView = (RelativeLayout) layoutInflater.inflate(R.layout.screen, null);
            holder = new ViewHolder();

            holder.time = (TextView) convertView.findViewById(R.id.tv_name);
            holder.description = (TextView) convertView.findViewById(R.id.tv_description);
            holder.price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.mybu = (Button) convertView.findViewById(R.id.button1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChooseItem screen = screenList.get(position); // 获取当前项数据
        if (null != screen) {
            holder.time.setText(screen.getChooseItemTime());
            holder.description.setText(screen.getChooseItemEC());
            holder.price.setText("Price: $" + Double.toString(screen.getChooseItemPrice()));
            holder.mybu.setTag(screen.getChooseItemId());
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
