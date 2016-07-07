package com.example.iehoshia.swipemoveactionbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Iehoshia on 05/07/2016.
 */
public class TweetAdapter extends ArrayAdapter{


    private LayoutInflater inflater;
    private TextView textView;

    public TweetAdapter(Activity activity, ArrayList<Tweet> items) {
        super(activity, R.layout.row, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Tweet tweet = (Tweet) getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        TextView tTitle = (TextView) convertView.findViewById(R.id.titulo);
        tTitle.setText(tweet.message);
        return convertView;

    }
}
