package com.bw.student.mvp.presenter;
import android.content.Context;

import com.bw.student.mvp.base.BaseResponse;
import com.bw.student.mvp.contract.SplashContract;
import com.bw.student.mvp.model.api.ApiService;
import com.bw.student.mvp.model.bean.UpdateBean;
import com.bw.student.mvp.model.net.NetManager;
import com.bw.student.mvp.model.net.ObjectLoader;
import com.bw.student.mvp.model.net.OnSuccessAndFaultListener;
import com.bw.student.mvp.model.net.OnSuccessAndFaultSub;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 *
 *
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.presenter
 * @time 2018/12/5 20:26
 */
public class SplashPresenterImpl extends SplashContract.SplashPresenter {

    @Override
    public void campusStyleShow(Context context, LifecycleProvider<FragmentEvent> provider, String deviceId) {
        ApiService apiService = NetManager.getInstance().apiService;
        Observable<BaseResponse> campusStyleShow = apiService.campusStyleShow(deviceId);

        new ObjectLoader().fragmentObserve(campusStyleShow,provider)
        .subscribe(new OnSuccessAndFaultSub<BaseResponse>(new OnSuccessAndFaultListener<String>() {
            @Override
            public void onSuccess(String result) {
                getView().campusStyleShowSuccess(result);
            }

            @Override
            public void onFault(String errorMsg) {
                getView().campusStyleShowError(errorMsg);
            }
        }));


    }

    @Override
    public void checkNewVersion(int versionCode, LifecycleProvider<FragmentEvent> provider) {
        ApiService apiService = NetManager.getInstance().apiService;
        Observable<UpdateBean> updateBeanObservable = apiService.checkNewVersion(versionCode);
        new ObjectLoader().fragmentObserve(updateBeanObservable,provider)
                .subscribe(new Consumer<UpdateBean>() {
                    @Override
                    public void accept(UpdateBean updateBean) throws Exception {
                        getView().checkNewVersionSuccess(updateBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().checkNewVersionError("检查失败");
                    }
                });
    }




}
