package com.bw.student.mvp.model.bean;

/**
 * Created by mumu on 2019/2/11.
 */

public class UpdateBean {

    /**
     * flag : 2
     * message : 查询成功
     * url : http://www.baidu.com
     * status : 0000
     */

    private int flag;
    private String message;
    private String url;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
