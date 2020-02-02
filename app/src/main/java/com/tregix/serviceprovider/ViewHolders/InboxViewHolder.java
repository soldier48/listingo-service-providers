package com.tregix.serviceprovider.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class InboxViewHolder extends RecyclerView.ViewHolder {
    TextView messageTextView;
    ImageView messageImageView;
    TextView messengerTextView;
    CircleImageView messengerImageView;

    public InboxViewHolder(View v) {
        super(v);
        messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
        messageImageView = (ImageView) itemView.findViewById(R.id.messageImageView);
        messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
        messengerImageView = (CircleImageView) itemView.findViewById(R.id.messengerImageView);
    }
}