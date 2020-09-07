package com.qchenmo.a001.gridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qchenmo.a001.R;

public class MyGridViewAdapter extends BaseAdapter {

    private Context mContent;
    private LayoutInflater mLayoutInflater;

    public MyGridViewAdapter(Context context){
        this.mContent = context;
        mLayoutInflater = LayoutInflater.from(context)
;
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_grid_item,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.tv_iv_grid);
            holder.textView = convertView.findViewById(R.id.tv_gv_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText("中二病");
        Glide.with(mContent).load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2599841796,386695404&fm=15&gp=0.jpg").into(holder.imageView);

        return convertView;
    }
}
