package com.bw.student.mvp.contract;

import com.bw.student.mvp.base.BasePresenter;
import com.bw.student.mvp.base.BaseView;
import com.bw.student.mvp.model.bean.ShowBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.contract
 * @time 2018/12/7 12:51
 */
public interface QuestionContract {


    public interface QuestionView extends BaseView {
        void problemAnswerShowSuccess(List<ShowBean> result);

        void problemAnswerShowError(String errorMsg);
    }

    public abstract class QuestionPresenter extends BasePresenter<QuestionView> {
        public abstract void getproblemAnswerShow(String imei, int page, int count, LifecycleProvider<FragmentEvent> provider);
    }
}
