package com.bw.student.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.student.R;
import com.bw.student.application.Contants;
import com.bw.student.mvp.base.BaseFragment;
import com.bw.student.mvp.contract.QuestionContract;
import com.bw.student.mvp.model.bean.ShowBean;
import com.bw.student.mvp.presenter.QuestionPresenterImpl;
import com.bw.student.mvp.ui.activity.MainActivity;
import com.bw.student.mvp.ui.adapter.QuestionAdater;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment<QuestionPresenterImpl> implements QuestionContract.QuestionView {

    @BindView(R.id.question_recy)
    RecyclerView mQuestionRecy;
    @BindView(R.id.edid_question)
    ImageView mEdidQuestion;

    private int page = 1;
    private int count = 5;
    private List<ShowBean> showBeanList = new ArrayList<>();
    private QuestionAdater adater;
    private int lastVisibleItemPosition;

    @Override
    protected int protetedId() {
        return R.layout.fragment_question;
    }

    @Override
    protected QuestionPresenterImpl initPresenter() {
        return new QuestionPresenterImpl();
    }

    @Override
    protected void initData() {

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mQuestionRecy.setLayoutManager(manager);

        adater = new QuestionAdater(getActivity(), showBeanList);
        mQuestionRecy.setAdapter(adater);


        mQuestionRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == manager.getItemCount()-1) {
                    count += 5;
                    mPresenter.getproblemAnswerShow(Contants.IMEI, page, count, QuestionFragment.this);
                }
            }
        });
        mPresenter.getproblemAnswerShow(Contants.IMEI, page, count, this);
        mEdidQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
    }

    @Override
    public void problemAnswerShowSuccess(List<ShowBean> result) {
        showBeanList.clear();
        showBeanList.addAll(result);

        adater.notifyDataSetChanged();

    }

    @Override
    public void problemAnswerShowError(String errorMsg) {
        Logger.e(errorMsg);
    }


}
