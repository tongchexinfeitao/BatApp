package com.bw.student.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bw.student.mvp.base.BaseResponse;
import com.bw.student.mvp.contract.MessageContract;
import com.bw.student.mvp.model.bean.Condition;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.model.net.NetManager;
import com.bw.student.mvp.model.net.ObjectLoader;
import com.bw.student.mvp.model.net.OnSuccessAndFaultListener;
import com.bw.student.mvp.model.net.OnSuccessAndFaultSub;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.presenter
 * @time 2018/12/6 13:31
 */
public class MessagePresenterImpl extends MessageContract.MessagePresenter {


    @Override
    public void findAllMarketDepartmentToNet(Context context, LifecycleProvider<FragmentEvent> eventLifecycleProvider, String imei) {
        new ObjectLoader().fragmentObserve(NetManager.getInstance().apiService.findAllMarketDepartment(imei), eventLifecycleProvider).subscribe(new OnSuccessAndFaultSub<BaseResponse<List<Department>>>(new OnSuccessAndFaultListener<List<Department>>() {
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

    @SuppressLint("CheckResult")
    @Override
    public void findStudentsByCondition(Context context, LifecycleProvider<FragmentEvent> messageFragment, HashMap<String, String> map,String imei) {
        NetManager.getInstance().apiService.findStudentsByCondition(imei,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<Condition>>>() {
                    @Override
                    public void accept(BaseResponse<List<Condition>> listBaseResponse) throws Exception {
                        if (listBaseResponse.result  == null || listBaseResponse.result.size()<1){
                            getView().findStudentsByConditionsError(listBaseResponse.getMessage());
                        }else {
                            getView().findStudentsByConditions(listBaseResponse.result);
                        }
                    }
                });

    }


}
