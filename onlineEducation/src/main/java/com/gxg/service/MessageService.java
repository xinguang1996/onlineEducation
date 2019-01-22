package com.gxg.service;

import com.gxg.entities.Message;
import com.gxg.entities.User;
import org.json.JSONObject;

import java.util.List;

/**
 * 消息通知相关业务处理
 * @author 郭欣光
 * @date 2019/1/22 11:22
 */
public interface MessageService {

    /**
     * 获取用户未读信息数量
     * @param user 用户信息
     * @return 未读消息数量
     * @author 郭欣光
     */
    int getUnreadMessageCount(User user);

    /**
     * 获取用户消息通知列表
     * @param user 用户信息
     * @param messageType 消息类型
     * @param messagePage 页码
     * @return 消息列表相关信息
     * @author 郭欣光
     */
    JSONObject getMessageList(User user, String messageType, String messagePage);
}