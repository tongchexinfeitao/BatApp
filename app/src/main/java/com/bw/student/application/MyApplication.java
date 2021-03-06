package com.bw.student.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.application
 * @time 2018/11/29 19:57
 */
public class MyApplication extends MultiDexApplication {

//    private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();

        setContext(this);
        //        LeakCanary解决内存泄漏的问题
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        refWatcher = LeakCanary.install(this);

        Fresco.initialize(this);

    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
}
