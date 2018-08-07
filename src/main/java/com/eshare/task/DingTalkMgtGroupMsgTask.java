package com.eshare.task;

import com.eshare.service.impl.DingTalkMgtGroupMsgTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 管理组钉钉消息任务
 *
 * @author liangyh
 *
 * @date 2018/7/25
 */

@Component
@PropertySource("classpath:application.properties")
public class DingTalkMgtGroupMsgTask {
    /**
     * MGT群机器人URL
     */
    @Value("${dingtalk.mgt.message.push.url}")
    private  String MGT_API_URL;
    @Autowired
    DingTalkMgtGroupMsgTaskService dingTalkMgtGroupMsgTaskService;

    private int count = 0;

    @Scheduled(cron = "${job.dingtalk.mgtGroup.schedule}")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
        dingTalkMgtGroupMsgTaskService.sendMessage(MGT_API_URL);
    }
}
