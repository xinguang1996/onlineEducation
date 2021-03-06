package com.gxg.dao;

import com.gxg.entities.Message;

import java.util.List;

/**
 * 消息通知数据库相关接口
 * @author 郭欣光
 * @date 2019/1/22 14:37
 */
public interface MessageDao {

    /**
     * 根据是否阅读查询消息通知数量
     * @param isRead 是否阅读
     * @return 消息通知数量
     * @author 郭欣光
     */
    int getCountByIsRead(String isRead);

    /**
     * 根据邮箱及是否阅读查询消息通知数量
     * @param email 邮箱
     * @param isRead 是否阅读
     * @return 消息通知数量
     * @author 郭欣光
     */
    int getCountByEmailAndIsRead(String email, String isRead);

    /**
     * 根据邮箱获取消息数量
     * @param email 邮箱
     * @return 消息数量
     * @author 郭欣光
     */
    int getCountByEmail(String email);

    /**
     * 根据邮箱查询消息
     * @param email 邮箱
     * @return 消息
     * @author 郭欣光
     */
    List<Message> getMessageByEmail(String email);

    /**
     * 根据邮箱个limit获取消息
     * @param email 邮箱
     * @param startLimit 第一个limit
     * @param endLimit 第二个limit
     * @return 消息列表
     * @author 郭欣光
     */
    List<Message> getMessageByEmailAndLimit(String email, int startLimit, int endLimit);

    /**
     * 根据邮箱 是否阅读以及limit获取消息
     * @param email 邮箱
     * @param isRead 是否阅读
     * @param startLimit 第一个limit
     * @param endLimit 第二个limit
     * @return 消息列表
     * @author 郭欣光
     */
    List<Message> getMessageByEmailAndIsReadAndLimit(String email,String isRead, int startLimit, int endLimit);

    /**
     * 根据邮箱删除消息通知
     * @param email 邮箱
     * @return 数据库改变行数
     * @author 郭欣光
     */
    int deleteMessageByEmail(String email);

    /**
     * 根据ID获取消息数量
     * @param id 消息ID
     * @return 消息数量
     * @author 郭欣光
     */
    int getCountById(String id);

    /**
     * 创建消息通知
     * @param message 消息通知信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    int createMessage(Message message);

    /**
     * 根据是否发送查询消息数量
     * @param isSend 是否发送
     * @return 消息数量
     * @author 郭欣光
     */
    int getCountByIsSend(String isSend);

    /**
     * 根据是否发送查询消息
     * @param isSend 是否发送
     * @return 消息列表
     * @author 郭欣光
     */
    List<Message> getMessageByIsSend(String isSend);

    /**
     * 更新消息通知信息
     * @param message 消息通知信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    int updateMessage(Message message);

    /**
     * 根据ID获得消息通知
     * @param id ID
     * @return 消息通知信息
     * @author 郭欣光
     */
    Message getMessageById(String id);

    /**
     * 删除消息通知
     * @param message 消息通知信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    int deleteMessage(Message message);
}
