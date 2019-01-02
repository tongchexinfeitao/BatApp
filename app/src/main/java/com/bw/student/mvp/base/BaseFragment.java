package com.bw.student.mvp.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.student.application.Contants;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.base
 * @time 2018/11/29 18:53
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView {

    private Unbinder unbinder;
    protected P mPresenter;
    protected SharedPreferences user;
    protected View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(protetedId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        Contants.IMEI = user.getString("imei",null);

        if (mPresenter == null) {
            mPresenter = initPresenter();
        }

        if (mPresenter != null) {
            mPresenter.AttachView(this);
        }

        initData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mPresenter == null) {
                return;
            }
        }
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

        if (mPresenter.isAttachView()) {
            mPresenter.Destory();
        }
    }

    protected abstract int protetedId();

    protected abstract P initPresenter();

    protected abstract void initData();

}
