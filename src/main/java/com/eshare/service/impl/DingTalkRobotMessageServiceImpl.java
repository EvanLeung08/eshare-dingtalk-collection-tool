package com.eshare.service.impl;

import com.eshare.Utils.HttpUtil;
import com.eshare.service.DingTalkRobotMessageService;

/**
 * 钉钉机器人消息服务接口
 *
 * @author liangyh
 * @email
 * @date 2018/7/26
 */
public class DingTalkRobotMessageServiceImpl implements DingTalkRobotMessageService {

    /**
     * 发钉钉消息
     * @param url     钉钉机器人发送URL
     * @param message 发送信息
     */
    @Override
    public void sendMessage(String url, String message) {
        HttpUtil.doPost(url, message);
    }

}
