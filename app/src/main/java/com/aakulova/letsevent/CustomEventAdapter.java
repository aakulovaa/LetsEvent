package com.aakulova.letsevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomEventAdapter extends BaseAdapter {

    Context context;
    int[] listImages;
    String[] listNames;
    String[] listDates;
    int goToEvent;
    LayoutInflater inflater;

    public CustomEventAdapter(Context ctx, int[] images, String[] names, String[] dates, int goToEvent){
        this.context = ctx;
        this.listImages = images;
        this.listNames = names;
        this.listDates = dates;
        this.goToEvent = goToEvent;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.event_item, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView dateTextView = view.findViewById(R.id.dateTextView);
        ImageButton button = view.findViewById(R.id.imageView2);
        imageView.setImageResource(listImages[i]);
        nameTextView.setText(listNames[i]);
        dateTextView.setText(listDates[i]);
        button.setImageResource(goToEvent);
        return view;
    }
}
