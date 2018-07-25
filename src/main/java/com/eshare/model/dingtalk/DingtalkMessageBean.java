/**
  * Copyright 2018 bejson.com 
  */
package com.eshare.model.dingtalk;

/**
 * Auto-generated: 2018-07-25 17:13:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DingtalkMessageBean {

    private String msgtype;
    private Markdown markdown;
    private At at;
    public void setMsgtype(String msgtype) {
         this.msgtype = msgtype;
     }
     public String getMsgtype() {
         return msgtype;
     }

    public void setMarkdown(Markdown markdown) {
         this.markdown = markdown;
     }
     public Markdown getMarkdown() {
         return markdown;
     }

    public void setAt(At at) {
         this.at = at;
     }
     public At getAt() {
         return at;
     }

}