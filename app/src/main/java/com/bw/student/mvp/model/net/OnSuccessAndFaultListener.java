package com.bw.student.mvp.model.net;

/**
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.model.net
 * @time 2018/11/29 21:38
 */
public interface OnSuccessAndFaultListener<T> {
    void onSuccess(T result);

    void onFault(String errorMsg);
}
