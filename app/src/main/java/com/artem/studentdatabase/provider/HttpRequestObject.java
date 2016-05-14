package com.artem.studentdatabase.provider;

/**
 * Created by denis on 20.03.2016.
 */
public class HttpRequestObject {
    public String url;
    public String json;
    public String method;

    public HttpRequestObject(String url, String json, String method) {
        this.url = url;
        this.json = json;
        this.method = method;
    }
}
