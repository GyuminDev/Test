package com.example.simgyumin.hw_gyumin01;

/**
 * Created by Sim Gyumin on 2017-03-30.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class MyAdapter extends BaseAdapter {

    private Context context;
    private Drawable[] drawables;
    private String[] names;

    //생성자를 이용하여 Activity, App icon과 App name을 가져옴.
    public MyAdapter(Context context, Drawable[] drawables, String[] names) {
        this.context = context;
        this.drawables = drawables;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview);
        TextView textView = (TextView)convertView.findViewById(R.id.textview);

        imageView.setImageDrawable(drawables[position]);
        textView.setText(names[position]);

        return convertView;
    }
}

