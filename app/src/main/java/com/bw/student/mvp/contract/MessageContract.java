package com.bw.student.mvp.contract;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.bw.student.mvp.base.BasePresenter;
import com.bw.student.mvp.base.BaseView;
import com.bw.student.mvp.model.bean.Condition;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.ui.fragment.MessageFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.contract
 * @time 2018/12/6 13:32
 */
public interface MessageContract {

    // TODO: 2018/12/5 获取Splash图片
    interface MessageView extends BaseView {


        void findAllMarketDepartmentSuccess(List<Department> result);

        void findAllMarketDepartmentFault(String errorMsg);

        void findStudentsByConditions(List<Condition> result);

        void findStudentsByConditionsError(String message);
    }

    abstract class MessagePresenter extends BasePresenter<MessageView> {


        public abstract void findAllMarketDepartmentToNet(Context context, LifecycleProvider<FragmentEvent> eventLifecycleProvider, String imei);

        public abstract void findStudentsByCondition(Context context, LifecycleProvider<FragmentEvent> messageFragment, HashMap<String,String> map, String imei);
    }
}
