package com.uwaytech.qiniu.service;

import com.qiniu.util.Auth;
import com.uwaytech.qiniu.bo.QiNiuResourceBody;

/**
 * Created by moyi on 2016-06-06.
 */
public interface QiNiuService {
    /**
     * 获取7牛认证对象
     * @return
     */
    Auth getAuth();

    /**
     * 获取默认上传token
     * @return
     */
    String getUpToken();

    String getOpenBucketToken();

	/**
     * 获取下载token
     * @return
     */
    String downloadToken(String key);

    /**
     * 视频持久化处理
     * @param key 7牛文件名字
     * @param transformName    转换后文件名
     * @param fops 操作命令
     */
    void videoPfops(String key, String transformName, String fops);

    /**
     * <p>重载videoPfops(String key, String transformName, String fops) 方法</p>
     * <p>使用默认操作命令</p>
     * @param key 7牛文件名
     * @param transformName 转换后文件名
     */
    void videoPfops(String key, String transformName);

    void videoPfops(String key);

    /**
     * 文档预览处理
     * @param key 7牛文件名
     * @param transformName 转换以后的名字
     * @param fops 操作命令
     */
    void documentPfops(String key, String transformName, String fops);
    void documentPfops(String key, String transformName);

    /**
     * 使用默认命令转换为PDF,方便预览
     * @param key 文件名
     */
    void documentPfops(String key);

    void documentPfopsJpg(String key, int indexPage, String fops);

    /**
     * 补偿机制
     * <p>扫描数据库或者redis，持久化操作失败，或者未做持久化的数据的持久化操作</p>
     */
    void execute();

    QiNiuResourceBody getPfopsResponse(String id);

    void getFileInfo(String key);
}