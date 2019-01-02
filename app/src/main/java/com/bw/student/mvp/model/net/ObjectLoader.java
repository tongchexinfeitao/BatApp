package com.bw.student.mvp.model.net;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RX的Observable具体工具类
 *
 * @author zhanglu QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.model.net
 * @time 2018/11/29 19:33
 */
public class ObjectLoader {

    public  <T>Observable<T> activityObserve(Observable<T> observable, LifecycleProvider<ActivityEvent> lifecycleProvider){

        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(lifecycleProvider.<T>bindToLifecycle())//绑定生命周期
                .observeOn(AndroidSchedulers.mainThread());
    }

    public  <T>Observable<T> fragmentObserve(Observable<T> observable, LifecycleProvider<FragmentEvent> lifecycleProvider){

        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(lifecycleProvider.<T>bindToLifecycle())        //绑定生命周期
                .observeOn(AndroidSchedulers.mainThread());
    }


    public <T>Observable<T> viewObserve(Observable<T> observable){
        return  observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .throttleFirst(1, TimeUnit.SECONDS);
    }
}
