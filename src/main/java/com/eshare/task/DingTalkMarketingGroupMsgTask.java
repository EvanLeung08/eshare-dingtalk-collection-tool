package com.eshare.task;

import com.eshare.service.impl.DingTalkMarketingGroupMsgTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 营销组钉钉消息任务
 *
 * @author liangyh
 * @date 2018/7/25
 */

@Component
@PropertySource("classpath:application.properties")
public class DingTalkMarketingGroupMsgTask {

    @Value("${dingtalk.marketing.message.push.url}")
    private String MARKETING_API_URL;
    @Autowired
    DingTalkMarketingGroupMsgTaskService dingTalkMarketingGroupMsgTaskService;

    private int count = 0;

    @Scheduled(cron = "${job.dingtalk.marketing.schedule}")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
        dingTalkMarketingGroupMsgTaskService.sendMessage(MARKETING_API_URL);
    }
}
