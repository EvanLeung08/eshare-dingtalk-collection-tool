package com.eshare.service;

import com.eshare.Utils.FileUtil;
import com.eshare.Utils.MarkdownParser;
import com.eshare.api.service.NewsApiService;
import com.eshare.api.service.WeatherApiService;
import com.eshare.model.dingtalk.At;
import com.eshare.model.dingtalk.DingtalkMessageBean;
import com.eshare.model.dingtalk.Markdown;
import com.eshare.model.news.NewsBean;
import com.eshare.model.news.Toutiao;
import com.eshare.model.weather.Forecast;
import com.eshare.model.weather.WeatherBean;
import com.eshare.service.impl.DingTalkRobotMessageServiceImpl;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 抽象钉钉机器人消息服务接口
 *
 * @author liangyh
 * @email
 * @date 2018/7/26
 */
public abstract class AbstractDingTalkRobotMessageService extends DingTalkRobotMessageServiceImpl {
    /**
     * weather模板路径
     */
    private final static String WEATHER_TEMPLATE_PATH = "templates/weatherTemplate";

    @Autowired
    protected WeatherApiService weatherApiService;
    @Autowired
    protected NewsApiService newsApiService;

    /**
     * 发钉钉消息
     *
     * @param url             钉钉机器人发送URL
     * @param dingTalkMessage 钉钉消息接口
     */
    public void sendMessage(String url, DingTalkMessage dingTalkMessage) {
        sendMessage(url, dingTalkMessage.builderDingTalkMessage());
    }


    /**
     * 构造天气信息实体
     *
     * @return
     */
    protected DingtalkMessageBean buildWeatherMessageBean() {
        String city = "广州";
        String title = "每天日报收集提醒";
        String content = FileUtil.readFileFromResource(WEATHER_TEMPLATE_PATH);
        content = parseWeatherDataAndFillUpMarkdown(city, content);
        return MarkdownParser.buildDingtalkMarkdownMessageBean(title, content);
    }

    /**
     * 解析天气数据并填充Markdown内容
     *
     * @param content
     * @return
     */
    protected String parseWeatherDataAndFillUpMarkdown(String city, String content) {
        WeatherBean weatherBean = weatherApiService.callRemoteWeatherApi(city);
        StringBuilder sb = new StringBuilder();
        if (weatherBean != null) {
            if (weatherBean.getData() == null || weatherBean.getData().getForecast().isEmpty()) {
                return content;
            }
            List<Forecast> forecasts = weatherBean.getData().getForecast();
            for (Forecast forecast : forecasts) {
                String weatherInfo = String.format("> 【%s:】 %s，%s，%s，%s，%s，%s", forecast.getDate(), forecast.getFl(), forecast.getFx(), forecast.getHigh(), forecast.getLow(), forecast.getNotice(), forecast.getType());
                sb.append(weatherInfo).append("\n\n").append("<br/>").append("\n\n");
            }
        }
        return content.replace("${forecast}", sb.toString()).replace("${publishTime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * 解析新闻数据并填充Markdown内容
     *
     * @param content
     * @return
     */
    protected String parseNewsDataAndFillUpMarkdown(String content) {
        NewsBean newsBean = newsApiService.callRemoteNewsApi();
        StringBuilder sb = new StringBuilder();
        if (newsBean != null) {
            if (newsBean.getData() == null || newsBean.getData().getToutiao().isEmpty()) {
                return content;
            }
            List<Toutiao> news = newsBean.getData().getToutiao();
            for (Toutiao tt : news) {
                String newsInfo = String.format("> [%s](%s)", tt.getTitle(), tt.getLink());
                sb.append(newsInfo).append("\n\n").append("<br/>").append("\n\n");
            }
        }
        return content.replace("${news}", sb.toString()).replace("${publishTime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

}
