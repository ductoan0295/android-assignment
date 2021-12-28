package com.example.androidassignment.JSONHelper;


import java.io.InputStream;
import java.util.Scanner;

public class JSONReader {
    public String getJSONStringFromInputStream(InputStream inputStream) {
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }
}
