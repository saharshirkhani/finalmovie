package com.example.movietime;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    ArrayList<List_data> listdata;
    Context context;
    int resource;
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<List_data> listdata) {
        this.listdata=listdata;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.grid_list,null,true);
        }
        List_data list_data = listdata.get(position);
        ImageView img=(ImageView)convertView.findViewById(R.id.image_view);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + list_data.getImageurl())
                .into(img);


        return convertView;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}