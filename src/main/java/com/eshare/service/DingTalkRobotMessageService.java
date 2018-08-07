package com.eshare.service;

/**
 * 钉钉机器人消息服务接口
 *
 * @author liangyh
 * @email
 * @date 2018/7/26
 */
@FunctionalInterface
public interface DingTalkRobotMessageService {

    void sendMessage(String url, String message);
}
