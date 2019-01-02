package com.bw.student.mvp.base;

import android.content.Context;

import com.bw.student.mvp.model.api.ApiService;
import com.bw.student.mvp.model.net.NetManager;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.base
 * @time 2018/11/29 21:13
 */
public interface BaseModel<T> {


     void subscribe(Context context, Observable observable, ObservableTransformer<T,T> transformer,boolean isDialog, boolean cancelable);


}
