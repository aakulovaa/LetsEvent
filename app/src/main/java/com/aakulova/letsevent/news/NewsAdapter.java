package com.aakulova.letsevent.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aakulova.letsevent.R;

import java.util.ArrayList;
public class NewsAdapter extends ArrayAdapter<NewsListData> {

    public NewsAdapter(@NonNull Context context, ArrayList<NewsListData> newsList) {
        super(context, R.layout.news_item, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsListData newsListData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        ImageView userImage = convertView.findViewById(R.id.user_photo);
        TextView userLogo = convertView.findViewById(R.id.user_logo);
        ImageView newsImage = convertView.findViewById(R.id.imageView);
        TextView newsName = convertView.findViewById(R.id.nameTextView);
        TextView newsDesc = convertView.findViewById(R.id.descTextView);

        if (newsListData != null) {
            userImage.setImageResource(newsListData.getImageUser());
            newsImage.setImageResource(newsListData.getImageNews());
            userLogo.setText(newsListData.getUserLog());
            newsName.setText(newsListData.getNameNews());
            newsDesc.setText(newsListData.getDescNews());
        }

        return convertView;
    }
}
