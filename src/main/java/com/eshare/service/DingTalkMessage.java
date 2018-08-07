package com.eshare.service;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * DingTalkMessage
 *
 * @author liangyh
 * @email
 * @date 2018/7/26
 */
@FunctionalInterface
public interface DingTalkMessage<T> {

    abstract String builderDingTalkMessage();

}
