package com.eshare.Utils;

import com.eshare.model.dingtalk.At;
import com.eshare.model.dingtalk.DingtalkMessageBean;
import com.eshare.model.dingtalk.Markdown;

import java.util.Collections;

/**
 * MarkdownParser
 *
 * @author liangyh
 * @date 2018/7/30
 */
public class MarkdownParser {


    /**
     * 构造钉钉markdown推送实体
     *
     * @return
     */
    public static DingtalkMessageBean buildDingtalkMarkdownMessageBean(String title,String content) {
        DingtalkMessageBean messageBean = new DingtalkMessageBean();
        //初始化Markdown
        Markdown markdown = new Markdown();
        markdown.setTitle(title);
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


}
