package com.eshare.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eshare.Utils.HttpUtil;
import com.eshare.annotation.ApiService;
import com.eshare.model.weather.WeatherBean;

/**
 * 天气服务类
 *
 * @author liangyh
 *
 * @date 2018/7/25
 */
@ApiService
public class WeatherApiService {

    /**
     * 远程调用天气API
     *
     * @return
     */
    public WeatherBean callRemoteWeatherApi(String city) {
        String url = "https://www.sojson.com/open/api/weather/json.shtml?city="+city;
        String result = HttpUtil.doGet(url);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        return JSON.toJavaObject(jsonObject, WeatherBean.class);
    }


}
