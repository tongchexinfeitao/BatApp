package com.bw.student.mvp.base;

import android.text.TextUtils;

/**
 * 基类的封装
 *
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.base
 * @time 2018/11/29 19:46
 */
public class BaseResponse<T> {


    private String status;
    private String message;
    private String stylePath;
    public T result;

    public boolean isSuccess(){
        return TextUtils.equals(status,"0000");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStylePath() {
        return stylePath;
    }

    public void setStylePath(String stylePath) {
        this.stylePath = stylePath;
    }
}
