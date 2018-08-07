package com.eshare.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eshare.Utils.HttpUtil;
import com.eshare.annotation.ApiService;
import com.eshare.model.news.NewsBean;
import com.eshare.model.weather.WeatherBean;
import org.springframework.stereotype.Service;

/**
 * 天气服务类
 *
 * @author liangyh
 *
 * @date 2018/7/25
 */
@ApiService
public class NewsApiService {

    /**
     * 远程调用新闻API
     *
     * @return
     */
    public NewsBean callRemoteNewsApi() {
        String url = "https://www.apiopen.top/journalismApi";
        String result = HttpUtil.doGet(url);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        return JSON.toJavaObject(jsonObject, NewsBean.class);
    }


}
