package com.bw.student.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bw.student.R;
import com.bw.student.mvp.base.BaseActivity;
import com.bw.student.mvp.presenter.SplashPresenterImpl;
import com.bw.student.mvp.ui.fragment.BlankFragment;
import com.bw.student.mvp.ui.fragment.MessageFragment;
import com.bw.student.mvp.ui.fragment.QuestionFragment;
import java.util.ArrayList;
import butterknife.BindView;

public class SplashActivity extends BaseActivity {


    private static final String CHARM = "charm";
    private static final String MESSAGE = "message";
    @BindView(R.id.qie_fragment)
    FrameLayout mQieFragment;
    @BindView(R.id.btn_rb1)
    RadioButton mBtnRb1;
    @BindView(R.id.btn_rb2)
    RadioButton mBtnRb2;
    @BindView(R.id.btn_rb3)
    RadioButton mBtnRb3;
    @BindView(R.id.btn_rg)
    RadioGroup mBtnRg;
    private Fragment currentFragment;
    private FragmentManager manager;
    private ArrayList<String> list;
    private static final String EMPLOYMENT = "employment";

    @Override
    protected int initView() {
        return R.layout.activity_splash;
    }

    @SuppressLint({"ServiceCast", "MissingPermission"})
    @Override
    protected void initData() {

        manager = getSupportFragmentManager();


        if (mBtnRb1.isChecked()) {
            addFragment(CHARM);
        }
        list = new ArrayList<>();
        list.add(CHARM);
        list.add(MESSAGE);
        list.add(EMPLOYMENT);

        mBtnRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    default:
                        break;
                    case R.id.btn_rb1:
                        addFragment(CHARM);
                        break;
                    case R.id.btn_rb2:
                        addFragment(MESSAGE);
                        break;
                    case R.id.btn_rb3:
                        addFragment(EMPLOYMENT);
                        break;
                }
            }
        });



    }

    @Override
    protected SplashPresenterImpl getPresenter() {
        return new SplashPresenterImpl();
    }

    private void addFragment(String fTag) {
        //判断这个标签是否存在Fragment对象,如果存在则返回，不存在返回null
        Fragment fragment = manager.findFragmentByTag(fTag);
        //如果这个fragment不存于栈中
        FragmentTransaction transaction = manager.beginTransaction();
        if (null == fragment) {
            //初始化Fragment事物
            //根据RaioButton点击的Button传入的tag，实例化，添加显示不同的Fragment
            switch (fTag) {
                case "charm":
                    fragment = new BlankFragment();
                    break;
                case "message":
                    fragment = new MessageFragment();
                    break;
                case "employment":
                    fragment = new QuestionFragment();
                    break;
            }

            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.qie_fragment, fragment, fTag);
            transaction.commit();
            currentFragment = fragment;
        } else {
            //如果添加的Fragment已经存在,fragment不为空，直接显示fragment
            transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            transaction.show(fragment);
            currentFragment = fragment;
            transaction.commitAllowingStateLoss();
        }
    }

}
