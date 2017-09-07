package yvonneyey.bignews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yvonne on 2017/9/7.
 */

public final class QueryURL {
    public static final String LOG_TAG=QueryURL.class.getName();
    private QueryURL(){
    }

    /**
     *构建URL
     */
    private static URL creatUrl(String stringURL){
        URL url=null;
        try {
            url=new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse="";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readFromStream(inputStream);
            }else {
                Log.e(LOG_TAG,"Error response code:"+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    /**
     * 读取流处理
     */
    private static String readFromStream(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputStreamReader);
            try {
                String line=reader.readLine();
                while (line!=null){
                    stringBuilder.append(line);
                    line=reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param jsonResponse
     * @return 新闻数组
     */

    public static ArrayList<News> extraNews(String jsonResponse){
        Date date;
        ArrayList<News> newses=new ArrayList<>();
        if(TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        try {
            JSONObject root=new JSONObject(jsonResponse);
            JSONObject response=root.getJSONObject("response");
            JSONArray results=response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject news=results.getJSONObject(i);
                String webTitle=news.getString("webTitle");
                date= (Date) news.get("webPublicationDate");
                String url=news.getString("webUrl");
                newses.add(new News(webTitle,date,url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newses;

    }

    /**
     *
     * @param url
     * 调用方法获取新闻数组
     * @return 新闻数组
     */
    public static ArrayList<News> fetchNewsData(String url){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url1=creatUrl(url);
        String response="";
        try {
            response=makeHttpRequest(url1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<News> newses=extraNews(response);
        return newses;

    }
}
