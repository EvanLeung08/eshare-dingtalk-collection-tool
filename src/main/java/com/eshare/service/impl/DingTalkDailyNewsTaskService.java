package com.eshare.service.impl;

import com.alibaba.fastjson.JSON;
import com.eshare.Utils.FileUtil;
import com.eshare.Utils.MarkdownParser;
import com.eshare.annotation.ApiService;
import com.eshare.api.service.NewsApiService;
import com.eshare.model.dingtalk.At;
import com.eshare.model.dingtalk.DingtalkMessageBean;
import com.eshare.model.dingtalk.Markdown;
import com.eshare.model.news.NewsBean;
import com.eshare.model.news.Toutiao;
import com.eshare.model.weather.Forecast;
import com.eshare.model.weather.WeatherBean;
import com.eshare.service.AbstractDingTalkRobotMessageService;
import com.eshare.service.DingTalkMessage;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 钉钉每日新闻任务服务
 *
 * @author liangyh
 * @date 2018/7/25
 */
@Service
public class DingTalkDailyNewsTaskService extends AbstractDingTalkRobotMessageService {

    /**
     * 新闻模板路径
     */
    private final static String NEWS_TEMPLATE_PATH = "templates/newsTemplate";

    /**
     * 新闻信息
     */
    public void sendMessage(String urls) {
        if(StringUtils.isEmpty(urls)){
            return ;
        }
        String[] urlArray = urls.split( ",");
        for(String url : urlArray) {
            sendMessage(url, () -> JSON.toJSONString(buildNewsBean()));
        }
    }


    /**
     * 构造天气信息实体
     *
     * @return
     */
    protected DingtalkMessageBean buildNewsBean() {
        String title = "每日新闻推送";
        String content = FileUtil.readFileFromResource(NEWS_TEMPLATE_PATH);
        content = parseNewsDataAndFillUpMarkdown(content);
        return MarkdownParser.buildDingtalkMarkdownMessageBean(title,content);
    }


}
