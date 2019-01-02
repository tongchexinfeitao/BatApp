package com.bw.student.mvp.presenter;

import com.bw.student.mvp.base.BaseResponse;
import com.bw.student.mvp.contract.MainContract;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.model.net.NetManager;
import com.bw.student.mvp.model.net.ObjectLoader;
import com.bw.student.mvp.model.net.OnSuccessAndFaultListener;
import com.bw.student.mvp.model.net.OnSuccessAndFaultSub;
import com.bw.student.mvp.ui.activity.MainActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.presenter
 * @time 2018/12/7 14:47
 */
public class MainPresenterImpl extends MainContract.MainPresenter {
    @Override
    public void findAllMarketDepartmentToNet(LifecycleProvider<ActivityEvent> mainActivity, String imei) {
        new ObjectLoader().activityObserve(NetManager.getInstance().apiService.findAllMarketDepartment(imei), mainActivity).subscribe(new OnSuccessAndFaultSub<BaseResponse<List<Department>>>(new OnSuccessAndFaultListener<List<Department>>() {
            @Override
            public void onSuccess(List<Department> result) {
                getView().findAllMarketDepartmentSuccess(result);
            }

            @Override
            public void onFault(String errorMsg) {
                getView().findAllMarketDepartmentFault(errorMsg);
            }
        }));
    }

    @Override
    public void addConsultToNet(String imei, HashMap<String, String> map) {
        NetManager.getInstance().apiService.addConsult(imei,map).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        if (baseResponse.isSuccess()){
                            getView().addConsultToNet(baseResponse.getMessage());
                        }else {
                            getView().findAllMarketDepartmentFault(baseResponse.getMessage());
                        }
                    }
                });
    }
}
