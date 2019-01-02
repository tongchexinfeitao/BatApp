package com.bw.student.mvp.presenter;

import com.bw.student.mvp.base.BaseResponse;
import com.bw.student.mvp.contract.QuestionContract;
import com.bw.student.mvp.model.bean.ShowBean;
import com.bw.student.mvp.model.net.NetManager;
import com.bw.student.mvp.model.net.ObjectLoader;
import com.bw.student.mvp.model.net.OnSuccessAndFaultListener;
import com.bw.student.mvp.model.net.OnSuccessAndFaultSub;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.presenter
 * @time 2018/12/7 12:51
 */
public class QuestionPresenterImpl extends QuestionContract.QuestionPresenter{


    @Override
    public void getproblemAnswerShow(String imei, int page, int count, LifecycleProvider<FragmentEvent> provider) {
        new ObjectLoader().fragmentObserve(NetManager.getInstance().apiService.problemAnswerShow(imei,page,count),provider).subscribe(new OnSuccessAndFaultSub<BaseResponse<List<ShowBean>>>(new OnSuccessAndFaultListener<List<ShowBean>>() {
            @Override
            public void onSuccess(List<ShowBean> result) {
                getView().problemAnswerShowSuccess(result);
            }

            @Override
            public void onFault(String errorMsg) {
                    getView().problemAnswerShowError(errorMsg);
            }
        }));
    }
}
