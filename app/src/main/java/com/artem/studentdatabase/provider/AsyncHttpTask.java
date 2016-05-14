package com.artem.studentdatabase.provider;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by denis on 19.03.2016.
 */
public class AsyncHttpTask extends AsyncTask<HttpRequestObject, String, String> {
    @Override
    protected String doInBackground(HttpRequestObject... requestObjects) {

        HttpRequestObject requestObject = requestObjects[0];
        switch (requestObject.method) {
            case "GET":
                return doGet(requestObject.url);
            case "POST":
                return doPost(requestObject.url, requestObject.json);
            case "PUT":
                return doPut(requestObject.url, requestObject.json);
            default:
                return "";
        }
    }

    private String doGet(String urlString) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            return responseStrBuilder.toString();
        } catch (IOException e) {
            return "";
        } finally {
            urlConnection.disconnect();
        }
    }

    private String doPost(String urlString, String payload) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/json");
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(payload.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();
            return "";
        } catch (IOException e) {
            return "";
        } finally {
            urlConnection.disconnect();
        }
    }

    private String doPut(String urlString, String payload) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/json");
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(payload.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();
            return  "";
        } catch (IOException e) {
            return "";
        } finally {
            urlConnection.disconnect();
        }
    }
}
