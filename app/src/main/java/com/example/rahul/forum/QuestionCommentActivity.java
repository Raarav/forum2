package com.example.rahul.forum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

public class QuestionCommentActivity extends AppCompatActivity {

    private TextView quesText;
    private EditText cmeditMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private String post_key;
    private Long now, serverTime;
    private String messageTime;
    private TextView fcmmessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_comment);

        Bundle extras = this.getIntent().getExtras();
        post_key = extras.getString("post_Key");


        Intent intent = getIntent();

        post_key = intent.getStringExtra("POST_KEY");
       // String message=intent.getStringExtra("message");
        String message = getIntent().getExtras().getString("message");




        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, post_key, Toast.LENGTH_SHORT).show();

        cmeditMessage = (EditText) findViewById(R.id.cmeditMessageE);
        fcmmessageText = (TextView) findViewById(R.id.messageText1);
        quesText=(TextView) findViewById(R.id.messageText);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum").child(post_key).child("Replies");
        mMessageList = (RecyclerView) findViewById(R.id.cmmessageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);

        fcmmessageText.setText(message);


    }


    public void cmsendButtonClicked(View view) {

        final String messageValue = cmeditMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {


            DatabaseReference replyDb = mDatabase.push();
            replyDb.child("Reply").setValue(messageValue);
            replyDb.child("time").setValue(ServerValue.TIMESTAMP);
            cmeditMessage.setText("");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = mDatabase.orderByChild("time");

        FirebaseRecyclerAdapter<fcm, fcmMessageViewHolder> fcmadapter = new FirebaseRecyclerAdapter<fcm, fcmMessageViewHolder>(
                fcm.class,
                R.layout.fcmrow,
                fcmMessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(fcmMessageViewHolder viewHolder, fcm model, int position) {
                viewHolder.setReply(model.getReply());

                serverTime = model.getTime();

                now = System.currentTimeMillis();

                try {

                    messageTime = String.valueOf(DateUtils.getRelativeTimeSpanString(serverTime, now, 0L, DateUtils.FORMAT_ABBREV_ALL));

                    viewHolder.setTime(messageTime);
                }catch (Exception e){

                }

            }


        };
        mMessageList.setAdapter(fcmadapter );
    }

}




















































































































































































































