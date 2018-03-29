package com.example.rahul.forum;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Rahul on 3/29/2018.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    View mView;
    LinearLayout mQuesLayout;

    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;


    }


    public void setQuestion(String content) {
        TextView message_content = (TextView) mView.findViewById(R.id.messageText);
        message_content.setText(content);

    }


}
