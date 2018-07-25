/**
  * Copyright 2018 bejson.com 
  */
package com.eshare.model.weather;
import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2018-07-25 16:26:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private List<Forecast> forecast;
    private String ganmao;
    private int pm10;
    private int pm25;
    private String quality;
    private String shidu;
    private String wendu;
    private Yesterday yesterday;
    public void setForecast(List<Forecast> forecast) {
         this.forecast = forecast;
     }
     public List<Forecast> getForecast() {
         return forecast;
     }

    public void setGanmao(String ganmao) {
         this.ganmao = ganmao;
     }
     public String getGanmao() {
         return ganmao;
     }

    public void setPm10(int pm10) {
         this.pm10 = pm10;
     }
     public int getPm10() {
         return pm10;
     }

    public void setPm25(int pm25) {
         this.pm25 = pm25;
     }
     public int getPm25() {
         return pm25;
     }

    public void setQuality(String quality) {
         this.quality = quality;
     }
     public String getQuality() {
         return quality;
     }

    public void setWendu(String wendu) {
         this.wendu = wendu;
     }
     public String getWendu() {
         return wendu;
     }

    public void setYesterday(Yesterday yesterday) {
         this.yesterday = yesterday;
     }
     public Yesterday getYesterday() {
         return yesterday;
     }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }
}