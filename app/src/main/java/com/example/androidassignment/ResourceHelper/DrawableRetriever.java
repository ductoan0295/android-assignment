package com.example.androidassignment.ResourceHelper;

import com.example.androidassignment.R;

import java.lang.reflect.Field;

public class DrawableRetriever {
    public int getIDByName(String drawableName) {
        try {
            Class<R.drawable> res = R.drawable.class;
            Field field = res.getField(drawableName);
            return field.getInt(null);
        } catch (Exception e) {
            return -1;
        }
    }
}
