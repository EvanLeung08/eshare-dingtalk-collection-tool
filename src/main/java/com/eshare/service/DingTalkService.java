package com.eshare.service;

import com.alibaba.fastjson.JSON;
import com.eshare.Utils.FileUtil;
import com.eshare.Utils.HttpUtil;
import com.eshare.model.dingtalk.At;
import com.eshare.model.dingtalk.DingtalkMessageBean;
import com.eshare.model.dingtalk.Markdown;
import com.eshare.model.weather.Forecast;
import com.eshare.model.weather.WeatherBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 钉钉信息服务
 *
 * @author liangyh
 * @date 2018/7/25
 */
@Slf4j
@Service
public class DingTalkService {
    /**
     * weather模板路径
     */
    private final static String WEATHER_TEMPLATE_PATH = "classpath:templates/weatherTemplate";

    @Autowired
    WeatherService weatherService;

    /**
     * 发送天气信息
     */
    public void sendWeatherMessage() {
        DingtalkMessageBean messageBean = buildWeatherMessageBean();
        //自定义钉钉机器人测试URL
        String dingTalkUrl = "";
        sendMessage(dingTalkUrl,JSON.toJSONString(messageBean));
    }

    /**
     * 构造天气信息实体
     *
     * @return
     */
    private DingtalkMessageBean buildWeatherMessageBean() {
        DingtalkMessageBean messageBean = new DingtalkMessageBean();
        //初始化Markdown
        Markdown markdown = new Markdown();
        String content = FileUtil.readFileFromResource(WEATHER_TEMPLATE_PATH);
        content = parseWeatherDataAndFillUpMarkdown(content);
        markdown.setTitle("每天日报收集提醒");
        markdown.setText(content);
        messageBean.setMarkdown(markdown);
        //初始化At
        At at = new At();
        at.setAtMobiles(Collections.emptyList());
        at.setIsAtAll(true);
        //装配消息
        messageBean.setMsgtype("markdown");
        messageBean.setMarkdown(markdown);
        messageBean.setAt(at);
        return messageBean;
    }

    /**
     * 解析天气数据并填充Markdown内容
     *
     * @param content
     * @return
     */
    public String parseWeatherDataAndFillUpMarkdown(String content) {
        WeatherBean weatherBean = weatherService.callRemoteWeatherApi("广州");
        StringBuilder sb = new StringBuilder();
        if (weatherBean != null) {
            if (weatherBean.getData() != null && !weatherBean.getData().getForecast().isEmpty()) {
                List<Forecast> forecasts = weatherBean.getData().getForecast();
                for (Forecast forecast : forecasts) {
                    String weatherInfo = String.format("> 【%s:】 %s，%s，%s，%s，%s，%s", forecast.getDate(), forecast.getFl(), forecast.getFx(), forecast.getHigh(), forecast.getLow(), forecast.getNotice(), forecast.getType());
                    sb.append(weatherInfo).append("\n").append("<br/>").append("\n\n");
                }
                content = content.replace("${forecast}", sb.toString()).replace("${publishTime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return content;
    }


    /**
     * 发钉钉消息
     *
     * @param url     钉钉机器人发送URL
     * @param message 发送信息
     */
    public void sendMessage(String url, String message) {
        HttpUtil.doPost(url, message);
    }


}
