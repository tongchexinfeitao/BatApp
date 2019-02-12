package com.bw.student.mvp.contract;

import android.content.Context;

import com.bw.student.mvp.base.BasePresenter;
import com.bw.student.mvp.base.BaseView;
import com.bw.student.mvp.model.bean.UpdateBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * 契约类
 *
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.contract
 * @time 2018/12/5 20:27
 */
public interface SplashContract {

    // TODO: 2018/12/5 获取Splash图片
    interface SplashView extends BaseView {

        void campusStyleShowSuccess(String result);

        void campusStyleShowError(String errorMsg);

        void checkNewVersionSuccess(UpdateBean updateBean);

        void checkNewVersionError(String errorMsg);

    }


    abstract class SplashPresenter extends BasePresenter<SplashView> {
        public abstract void campusStyleShow(Context context, LifecycleProvider<FragmentEvent> provider, String deviceId);

        public abstract void checkNewVersion(int versionCode,LifecycleProvider<FragmentEvent> provider);
    }
}
