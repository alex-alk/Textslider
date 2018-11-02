package com.slider.alex.textslider;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Spin extends BaseAdapter
{
    ArrayList<Integer> colors;
    Context context;

    public Spin(Context context)
    {
        this.context=context;
        colors=new ArrayList<Integer>();
        int retrieve []=context.getResources().getIntArray(R.array.androidColors);
        for(int re:retrieve)
        {
            colors.add(re);
        }
    }
    @Override
    public int getCount()
    {
        return colors.size();
    }
    @Override
    public Object getItem(int arg0)
    {
        return colors.get(arg0);
    }
    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.spinner_item, null);
        TextView txv=view.findViewById(R.id.spint);
        txv.setBackgroundColor(colors.get(pos));
        String space = " " + " " + " " + " " + " " + " ";
        txv.setText(space);
        return view;
    }

}