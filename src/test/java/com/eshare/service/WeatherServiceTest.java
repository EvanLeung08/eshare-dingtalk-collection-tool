package com.eshare.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eshare.model.weather.WeatherBean;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sun.nio.cs.UTF_32;

import java.io.IOException;
import java.net.URI;

/**
 * User: liangyh
 * Date: 2018/7/25
 * email: liangyuhua@
 */
public class WeatherServiceTest {



    @Test
    public void callWeatherApi(){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse reponse = null;
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city=广州";
        URIBuilder builder ;
        URI uri ;
        try {
            builder =  new URIBuilder(url);
            httpClient = HttpClients.createDefault();
            uri = builder.setCharset(new UTF_32()).build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
            reponse = httpClient.execute(httpGet);
            System.out.println(reponse.getStatusLine().getStatusCode() + "\n");
            HttpEntity httpEntity = reponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            JSONObject  jsonObject = (JSONObject) JSONObject.parse(responseContent);
            WeatherBean weatherData = JSON.toJavaObject(jsonObject, WeatherBean.class);
            System.out.println(responseContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {

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
    }

}