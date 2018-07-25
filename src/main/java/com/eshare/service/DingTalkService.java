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
 * @email liangyuhua@ppmoney.com
 * @date 2018/7/25
 */
@Slf4j
@Service
public class DingTalkService {

    @Autowired
    WeatherService weatherService;

    /**
     * 发送天气信息
     */
    public void sendWeatherMessage() {
        DingtalkMessageBean messageBean = buildWeatherMessageBean();
        sendMessage(JSON.toJSONString(messageBean));
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
        String content = FileUtil.readFileFromResource("classpath:templates/weatherTemplate");
        content = parseWeatherDataAndFillUpMarkdown(content);
        markdown.setTitle("每天日报手机提醒");
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
     * @param content
     * @return
     */
    public String parseWeatherDataAndFillUpMarkdown(String content) {
        WeatherBean weatherBean = weatherService.callRemoteWeatherApi("广州");
        if (weatherBean != null) {
            if (weatherBean.getData() != null && !weatherBean.getData().getForecast().isEmpty()) {
                List<Forecast> forecasts = weatherBean.getData().getForecast();
                Forecast todayForecast = forecasts.get(0);
                Forecast tomorrowForecast = forecasts.get(1);
                if (todayForecast != null) {
                    content = content.replace("${today.forecast}", String.format("【今天:】 %s,%s，%s，%s，%s，%s，%s", todayForecast.getDate(), todayForecast.getFl(), todayForecast.getFx(), todayForecast.getHigh(), todayForecast.getLow(), todayForecast.getNotice(), todayForecast.getType()));
                }
                if (tomorrowForecast != null) {
                    content = content.replace("${tomorrow.forecast}", String.format("【明天:】 %s,%s，%s，%s，%s，%s，%s", tomorrowForecast.getDate(), tomorrowForecast.getFl(), tomorrowForecast.getFx(), tomorrowForecast.getHigh(), tomorrowForecast.getLow(), tomorrowForecast.getNotice(), tomorrowForecast.getType()));
                }
                content = content.replace("${publishTime}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
            }
        }
        return content;
    }


    /**
     * 发钉钉消息
     * @param message
     */
    public void sendMessage(String message) {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=15bf528a66b4753e5b09e90eb9f27941b27ccba39946f848d6937bb7b455711c";
        //String url="https://oapi.dingtalk.com/robot/send?access_token=97a0b3dd5dd05366c83420fc1837ec63bcce6b1c3978cdfc7e2d5d122581f0f1";
        HttpUtil.doPost(url, message);
    }


}
