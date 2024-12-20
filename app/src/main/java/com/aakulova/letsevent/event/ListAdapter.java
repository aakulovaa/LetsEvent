package com.aakulova.letsevent.event;

import android.content.Context;
import android.content.Intent;
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
import java.util.Objects;

/**
 * Пользовательский адаптер для отображения списка событий.
 */
public class ListAdapter extends ArrayAdapter<ListData> {
    /**
     * Constructor for ListAdapter.
     *
     * @param context       the current context.
     * @param dataArrayList the list of event data.
     */
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.event_item, dataArrayList);
    }

    /**
     * Gets the view for each item in the list.
     *
     * @param position    the position of the item within the adapter's data set.
     * @param convertView the old view to reuse, if possible.
     * @param parent      the parent view that this view will eventually be attached to.
     * @return the view for the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }

        ImageView listImage = convertView.findViewById(R.id.imageView);
        TextView listName = convertView.findViewById(R.id.nameTextView);
//        TextView listDate = convertView.findViewById(R.id.dateTextView);

        listImage.setImageResource(Objects.requireNonNull(listData).image);
        listName.setText(listData.name);
//        listDate.setText(listData.date);

        // Установка обработчика клика на весь элемент списка
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EventActivity.class);
            intent.putExtra("name", listData.name);
//            intent.putExtra("date", listData.date);
            intent.putExtra("desc", listData.desc);
            intent.putExtra("addr", listData.address);
            intent.putExtra("countPeople", listData.countPeople);
            intent.putExtra("image", listData.image);
            getContext().startActivity(intent);
        });

        return convertView;
    }
}