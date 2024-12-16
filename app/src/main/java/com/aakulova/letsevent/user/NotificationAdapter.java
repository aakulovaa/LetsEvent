package com.aakulova.letsevent.user;

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

public class NotificationAdapter extends ArrayAdapter<NoticeListData> {
    /**
     * Constructor for NotificationAdapter.
     *
     * @param context       the current context.
     * @param notificationList the list of notifications.
     */
    public NotificationAdapter(@NonNull Context context, ArrayList<NoticeListData> notificationList) {
        super(context, R.layout.notise_item, notificationList);
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
        NoticeListData notification = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notise_item, parent, false);
        }

        ImageView noticeImage = convertView.findViewById(R.id.imageView);
        TextView nameTitle = convertView.findViewById(R.id.nameTextView);
        TextView descTitle = convertView.findViewById(R.id.descTextView);

        // Загрузка изображения с помощью Glide
        if (notification != null) {
//            Glide.with(getContext())
//                    .load(notification.getImage())
//                    .into(noticeImage);

            nameTitle.setText(notification.getName());
            descTitle.setText(notification.getDesc());

            // Установка обработчика клика на весь элемент списка
//            convertView.setOnClickListener(v -> {
//                Intent intent = new Intent(getContext(), NoticesActivity.class);
//                intent.putExtra("name", notification.getName());
//                intent.putExtra("desc", notification.getDesc());
//                //intent.putExtra("image", notification.getImage());
//                getContext().startActivity(intent);
//            });
        }

        return convertView;
    }
}