package com.example.rahul.forum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionCommentActivity extends AppCompatActivity {

    private TextView quesText;
    private EditText cmeditMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_comment);

        Bundle extras = this.getIntent().getExtras();
        post_key = extras.getString("post_Key");


        Intent intent = getIntent();

        post_key = intent.getStringExtra("POST_KEY");



        Toast.makeText(this, "Reply", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, post_key, Toast.LENGTH_SHORT).show();

        cmeditMessage = (EditText) findViewById(R.id.cmeditMessageE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum").child(post_key).child("Replies");
        mMessageList = (RecyclerView) findViewById(R.id.cmmessageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);


    }


    public void cmsendButtonClicked(View view) {

        final String messageValue = cmeditMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {


            DatabaseReference replyDb = mDatabase.push();
            replyDb.child("Reply").setValue(messageValue);
            cmeditMessage.setText("");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<fcm, fcmMessageViewHolder> fcmadapter = new FirebaseRecyclerAdapter<fcm, fcmMessageViewHolder>(
                fcm.class,
                R.layout.fcmrow,
                fcmMessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(fcmMessageViewHolder viewHolder, fcm model, int position) {
                viewHolder.setReply(model.getReply());

            }


        };
        mMessageList.setAdapter(fcmadapter );
    }

}




















































































































































































































