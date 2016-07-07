package com.example.iehoshia.swipemoveactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirstFragment extends Fragment {

    private static final String TAG = "FirstFragment";
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText etMessage;
    Button btnSend;
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> lista;
    ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");
        lista = new ArrayList<>();
        tweetAdapter = new TweetAdapter(getActivity(),lista);



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Tweet tweet= postSnapshot.getValue(Tweet.class);
                    lista.add(tweet);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public static FirstFragment newInstance() {
        FirstFragment fragmentFirst = new FirstFragment();
        return fragmentFirst;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        etMessage = (EditText) view.findViewById(R.id.etMessage);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new OnClickListenerSend());
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(tweetAdapter);


        return view;
    }

    private class OnClickListenerSend implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String sMessage;
            sMessage = etMessage.getText().toString();


            Map<String, String> post = new HashMap<>();
            post.put("author", sMessage);
            post.put("message",sMessage);
            myRef.push().setValue(post);

        }
    }
}
