package com.uwaytech.qiniu.bo;

import java.io.Serializable;

/**
 * Created by moyi on 2016-06-17.
 */
public class QiNiuResourceItem implements Serializable{
    private String cmd;

    private int code;

    private String desc;

    private String error;

    private String hash;

    private String key;

    private int returnOld;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getReturnOld() {
        return returnOld;
    }

    public void setReturnOld(int returnOld) {
        this.returnOld = returnOld;
    }

    @Override
    public String toString() {
        return "QiNiuResourceItem{" +
                "cmd='" + cmd + '\'' +
                ", code=" + code +
                ", desc='" + desc + '\'' +
                ", error='" + error + '\'' +
                ", hash='" + hash + '\'' +
                ", key='" + key + '\'' +
                ", returnOld=" + returnOld +
                '}';
    }
}
