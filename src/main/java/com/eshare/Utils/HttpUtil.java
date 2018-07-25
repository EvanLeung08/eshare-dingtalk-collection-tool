package com.eshare.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.nio.cs.UTF_32;

import java.io.IOException;
import java.net.URI;

/**
 * HttpUtil
 *
 * @author liangyh
 * @email liangyuhua@ppmoney.com
 * @date 2018/7/25
 */
public class HttpUtil {


    public static String doPost(String url, String message) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse reponse = null;
        // String url = "https://oapi.dingtalk.com/robot/send?access_token=15bf528a66b4753e5b09e90eb9f27941b27ccba39946f848d6937bb7b455711c";
        // String url = "https://oapi.dingtalk.com/robot/send?access_token=cb08f25b9d6276c7e556ac42b7448990882d410266a2f1faa5545e242acbc080";
        URIBuilder builder;
        URI uri;
        String result = "";
        try {
            builder = new URIBuilder(url);
            httpClient = HttpClients.createDefault();
            uri = builder.setCharset(new UTF_32()).build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity entity = new StringEntity(message, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            reponse = httpClient.execute(httpPost);
            System.out.println(reponse.getStatusLine().getStatusCode() + "\n");
            HttpEntity httpEntity = reponse.getEntity();
            result = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(result);
        } catch (Exception ex) {
            if (reponse != null) {
                try {
                    reponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse reponse = null;
        URIBuilder builder;
        URI uri;
        //WeatherBean weatherData = null;
        String result = "";
        try {
            builder = new URIBuilder(url);
            httpClient = HttpClients.createDefault();
            uri = builder.setCharset(new UTF_32()).build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
            reponse = httpClient.execute(httpGet);
            System.out.println(reponse.getStatusLine().getStatusCode() + "\n");
            HttpEntity httpEntity = reponse.getEntity();
            result = EntityUtils.toString(httpEntity, "UTF-8");
            //JSONObject jsonObject = (JSONObject) JSONObject.parse(responseContent);
            //weatherData = JSON.toJavaObject(jsonObject, WeatherBean.class);
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (reponse != null) {
                try {
                    reponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
