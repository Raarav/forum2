package com.example.rahul.forum;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Rahul on 3/30/2018.
 */

public class fcmMessageViewHolder extends RecyclerView.ViewHolder {

    View cmView;

    public fcmMessageViewHolder(View itemView) {
        super(itemView);
        cmView = itemView;
    }

    public void setReply(String Reply) {
        TextView message_content = (TextView) cmView.findViewById(R.id.cmmessageText);
        message_content.setText(Reply);

    }
}
