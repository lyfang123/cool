package com.uwaytech.qiniu.bo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by moyi on 2016-06-15.
 */
public class QiNiuResourceBody implements Serializable{
    private String id;

    private int code;

    private String desc;

    private String inputKey;

    private String inputBucket;

    private String pipeline;

    private String reqid;

    private QiNiuResourceItem[] items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInputKey() {
        return inputKey;
    }

    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
    }

    public String getInputBucket() {
        return inputBucket;
    }

    public void setInputBucket(String inputBucket) {
        this.inputBucket = inputBucket;
    }

    public String getPipeline() {
        return pipeline;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public QiNiuResourceItem[] getItems() {
        return items;
    }

    public void setItems(QiNiuResourceItem[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "QiNiuResourceBody{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", desc='" + desc + '\'' +
                ", inputKey='" + inputKey + '\'' +
                ", inputBucket='" + inputBucket + '\'' +
                ", pipeline='" + pipeline + '\'' +
                ", reqid='" + reqid + '\'' +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
