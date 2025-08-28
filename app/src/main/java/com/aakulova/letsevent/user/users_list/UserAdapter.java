package com.aakulova.letsevent.user.users_list;

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
import com.aakulova.letsevent.user.ProfileActivity;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<UsersListData> {
    /**
     * Constructor for NotificationAdapter.
     *
     * @param context       the current context.
     * @param usersListData the list of notifications.
     */
    public UserAdapter(@NonNull Context context, ArrayList<UsersListData> usersListData) {
        super(context, R.layout.user_item, usersListData);
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
        UsersListData users = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }
        ImageView userImage = convertView.findViewById(R.id.imageView);
        TextView nameTitle = convertView.findViewById(R.id.nameTextView);

        if (users != null) {

            nameTitle.setText(users.getName());
            userImage.setImageResource(users.getImage());

            // Установка обработчика клика на весь элемент списка
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), UserAccActivity.class);
                intent.putExtra("name", users.getName());
                intent.putExtra("image", users.getImage());
                getContext().startActivity(intent);
            });
        }

        return convertView;
    }
}