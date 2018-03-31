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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity extends AppCompatActivity {
    private String loc=new String();
    private String post_key=null;
    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private int i=0;
    private Long fnow, fserverTime;
    private String fmessageTime;
    public static  final String message="message";
    private String question;
    public String questionSaver;
    LinearLayout linearLayout;
    LinearLayout childLinear;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMessage = (EditText) findViewById(R.id.editMessageE);
       String s= getIntent().getStringExtra("ques");
        linearLayout=(LinearLayout) findViewById(R.id.activity_main);



        textView= (TextView)findViewById(R.id.textView);

        loc = getIntent().getStringExtra("loc");
        post_key= getIntent().getStringExtra("post_string");

        if(loc.equals("main")) {
            textView.setMaxHeight(0);
           //linearLayout.removeView(textView);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum");
        } else
        {


            textView.setTextSize(26);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum").child(post_key).child("Replies");
        }


        textView.setText(s);



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





                        //  Intent openComment = new Intent(MainActivity.this , QuestionCommentActivity.class);
                        //openComment.putExtra("POST_KEY",post_key);
                        DatabaseReference mref=mDatabase.child(post_key).child("Question");
                        ValueEventListener valueEventListener=new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                question=dataSnapshot.getValue().toString();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        };
                        mref.addValueEventListener(valueEventListener);

                        //openComment.putExtra(questionSaver,question);
                        if(loc.equals("main")) {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            intent.putExtra("loc", "form");
                            intent.putExtra("post_string", post_key);
                            intent.putExtra("ques", question);
                            //intent.putExtra("intentDetail","answer");
                            startActivity(intent);
                        }
                    }
                });

                viewHolder.setQuestion(model.getQuestion());

            }
        };
        mMessageList.setAdapter(FBRA);
    }


}