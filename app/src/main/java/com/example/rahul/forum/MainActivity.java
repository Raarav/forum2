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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

public class  MainActivity extends AppCompatActivity {

    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private int i=0;
    private Long fnow, fserverTime;
    private String fmessageTime;
    public static  final String message="message";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMessage = (EditText) findViewById(R.id.editMessageE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum");
        mMessageList = (RecyclerView) findViewById(R.id.messageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);


    }

    public void sendButtonClicked(View view) {
        final String messageValue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {
            DatabaseReference newpost;
            newpost = mDatabase.push();
            newpost.child("Question").setValue(messageValue);
            newpost.child("ftime").setValue(ServerValue.TIMESTAMP);
           // mDatabase.child("Question"+i);
          //xs kxn  editMessage.setText("");

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = mDatabase.orderByChild("ftime");

        FirebaseRecyclerAdapter<Message, MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.fcmsinglerow,
                MessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(final MessageViewHolder viewHolder, final Message model, int position) {

                fserverTime = model.getfTime();

                fnow = System.currentTimeMillis();

                try {

                    fmessageTime = String.valueOf(DateUtils.getRelativeTimeSpanString(fserverTime, fnow, 0L, DateUtils.FORMAT_ABBREV_ALL));

                    viewHolder.setfTime(fmessageTime);
                }catch (Exception e){

                }


                final String post_key = getRef(position).getKey();

               viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {



                       Intent openComment = new Intent(MainActivity.this , QuestionCommentActivity.class);
                       openComment.putExtra("POST_KEY",post_key);
                       String msg=editMessage.getText().toString();
                       openComment.putExtra("message",msg);
                       startActivity(openComment);
                   }
               });

                viewHolder.setQuestion(model.getQuestion());

            }
        };
        mMessageList.setAdapter(FBRA);
    }


}