package com.example.myapplication.Utils;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpProvider {

    //    private static final String BASE_URL = "http://35.185.20.65/api/";
    private static final String BASE_URL = "http://192.168.0.16:8000/api/";
//    private static final String BASE_URL = "http://192.168.2.195:8000/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler)
    {
        client.addHeader("Authorization","Token " + "bee7a589326566344043a5940472415178759df2");
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        client.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");
        client.post(context,getAbsoluteUrl(url), entity , null, responseHandler);
    }

    public static void get(Context context, String url, RequestParams params, JsonHttpResponseHandler jsonHttpResponseHandler)
    {
        client.addHeader("Authorization","Token " + "bee7a589326566344043a5940472415178759df2");
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        client.setMaxRetriesAndTimeout(2, 5);
        client.get(context,getAbsoluteUrl(url), params , jsonHttpResponseHandler);
    }

    public static void postRegister(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler)
    {
        client.addHeader("Content-Type", "application/x-www-form-urlencoded");
        client.post(context,getAbsoluteUrl(url), entity , null, responseHandler);
    }

    public static void post(Context ctx, String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler ){
        /* Fixed Login problem where second login attempt after logout changes content type of data */
        /* Problem - Can't Login after logout from previous account */
        /* Cause - Content type is changed or has been added other type due to other http call (read_inventory) */
        /* Solution - Create new AssyncHttp Instance to ensure no previous content type or header has been set */
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(ctx,getAbsoluteUrl(url),entity,contentType,responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static String getBaseURL(){
        return BASE_URL.replace("api/","");
    }



}
