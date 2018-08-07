package com.eshare.service.impl;

import com.alibaba.fastjson.JSON;
import com.eshare.service.AbstractDingTalkRobotMessageService;
import com.eshare.service.DingTalkMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 营销群钉钉信息服务
 *
 * @author liangyh
 * @date 2018/7/25
 */
@Service
public class DingTalkMarketingGroupMsgTaskService extends AbstractDingTalkRobotMessageService {
    /**
     * 发送信息
     */
    public void sendMessage(String urls) {
        if(StringUtils.isEmpty(urls)){
            return ;
        }
        String[] urlArray = urls.split( ",");
        for(String url : urlArray) {
            sendMessage(url, () -> JSON.toJSONString(buildWeatherMessageBean()));
        }
    }




}
