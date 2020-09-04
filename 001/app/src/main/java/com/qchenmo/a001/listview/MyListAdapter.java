package com.qchenmo.a001.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qchenmo.a001.R;

public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    MyListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
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
        public TextView tvTitle,tvData,tvContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView==null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.iv);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvData = convertView.findViewById(R.id.tv_data);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //给控件赋值
        viewHolder.tvTitle.setText("这是标题");
        viewHolder.tvData.setText("这是修改后的标题");
        viewHolder.tvContent.setText("这是内容（新）");
        Glide.with(mContext).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3218962647,58233085&fm=26&gp=0.jpg").into(viewHolder.imageView);


        return convertView;
    }
}
