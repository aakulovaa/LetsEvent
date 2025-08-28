package com.aakulova.letsevent.chat;

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

public class MessageAdapter extends ArrayAdapter<MessageListData> {
    /**
     * Constructor for DialogAdapter.
     *
     * @param context       the current context.
     * @param messageListData the list of notifications.
     */
    public MessageAdapter(@NonNull Context context, ArrayList<MessageListData> messageListData) {
        super(context, R.layout.message_list_item, messageListData);
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
        MessageListData message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_list_item, parent, false);
        }

        TextView sendingUser = convertView.findViewById(R.id.message_user);
        TextView messageTime = convertView.findViewById(R.id.message_time);
        TextView messageText = convertView.findViewById(R.id.message_text);

        if (message != null) {

            sendingUser.setText(message.getSendingUser());
            messageTime.setText(message.getTime());
            messageText.setText(message.getMessage());

        }

        return convertView;
    }
}