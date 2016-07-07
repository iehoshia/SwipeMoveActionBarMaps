package com.example.iehoshia.swipemoveactionbar;

/**
 * Created by Iehoshia on 05/07/2016.
 */
public class Tweet {

    public String message;
    public String timestamp;

    public Tweet() {
        // Default constructor required for calls to DataSnapshot.getValue(Tweet.class)
    }

    public Tweet(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage(){
        return message;
    }

    public String getTimestamp(){
        return timestamp;
    }



}
