package com.eshare.task;

import com.eshare.service.DingTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 钉钉消息任务
 *
 * @author liangyh
 * @email liangyuhua@ppmoney.com
 * @date 2018/7/25
 */

@Component
@PropertySource("classpath:application.properties")
public class DingTalkMsgTask {

    @Autowired
    DingTalkService dingTalkService;

    private int count = 0;

    @Scheduled(cron = "${job.dingtalk.schedule}")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));

        dingTalkService.sendWeatherMessage();
    }
}
