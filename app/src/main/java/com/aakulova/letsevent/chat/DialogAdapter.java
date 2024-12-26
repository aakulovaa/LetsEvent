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

public class DialogAdapter extends ArrayAdapter<DialogListData> {
    /**
     * Constructor for DialogAdapter.
     *
     * @param context       the current context.
     * @param dialogListData the list of notifications.
     */
    public DialogAdapter(@NonNull Context context, ArrayList<DialogListData> dialogListData) {
        super(context, R.layout.dialog_item, dialogListData);
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
        DialogListData dialog = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, parent, false);
        }

        ImageView userImage = convertView.findViewById(R.id.imageView);
        TextView nameTitle = convertView.findViewById(R.id.nameTextView);
        TextView messageTitle = convertView.findViewById(R.id.messageTextView);

        if (dialog != null) {

            nameTitle.setText(dialog.getName());
            messageTitle.setText(dialog.getMessage());
            userImage.setImageResource(dialog.getImage());

            // Установка обработчика клика на весь элемент списка
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), DialogActivity.class);
                intent.putExtra("name", dialog.getName());
                intent.putExtra("message", dialog.getMessage());
                intent.putExtra("image", dialog.getImage());
                getContext().startActivity(intent);
            });
        }

        return convertView;
    }
}