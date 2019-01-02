package com.bw.student.mvp.contract;

import com.bw.student.mvp.base.BasePresenter;
import com.bw.student.mvp.base.BaseView;
import com.bw.student.mvp.model.bean.Department;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.contract
 * @time 2018/12/7 14:47
 */
public interface MainContract {
    public interface MainView extends BaseView {
        void findAllMarketDepartmentSuccess(List<Department> result);

        void findAllMarketDepartmentFault(String errorMsg);

        void addConsultToNet(String message);
    }


     abstract class MainPresenter extends BasePresenter<MainView> {


        public abstract void findAllMarketDepartmentToNet(LifecycleProvider<ActivityEvent> mainActivity, String imei);

        public abstract void addConsultToNet(String imei, HashMap<String,String> map);
    }
}
