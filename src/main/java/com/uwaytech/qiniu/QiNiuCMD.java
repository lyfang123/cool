package com.uwaytech.qiniu;

/**
 * Created by moyi on 2016-06-20.
 */
public enum QiNiuCMD {
    //默认转换成mp4格式，普清
    VIDEO_GENERAL("普清", "avthumb/mp4/vb/1.25m/s/640x480"),
    //转换成mp4格式，标清
    VIDEO_NORM("标清", "avthumb/mp4/vb/3m/s/800x480"),
    //文档预览转换
    DOC_PDF("文件PDF预览", "yifangyun_preview/v2/format/pdf");

    QiNiuCMD(String name, String cmd) {
        this.cmd = cmd;
        this.name = name;
    }


    private String name;
    private String cmd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
