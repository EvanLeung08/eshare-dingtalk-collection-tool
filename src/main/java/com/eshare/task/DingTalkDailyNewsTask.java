package com.eshare.task;

import com.eshare.service.impl.DingTalkDailyNewsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每日新闻推送任务
 *
 * @author liangyh
 * @date 2018/7/25
 */

@Component
@PropertySource("classpath:application.properties")
public class DingTalkDailyNewsTask {
    @Value("${dingtalk.news.push.urls}")
    private String newsUrls;

    @Autowired
    DingTalkDailyNewsTaskService dingTalkDailyNewsTaskService;

    private int count = 0;

    @Scheduled(cron = "${job.dingtalk.news.schedule}")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
        dingTalkDailyNewsTaskService.sendMessage(newsUrls);
    }
}
