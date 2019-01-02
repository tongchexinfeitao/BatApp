package com.bw.student.mvp.ui.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.student.R;
import com.bw.student.application.Contants;
import com.bw.student.mvp.base.BaseActivity;
import com.bw.student.mvp.contract.MainContract;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.presenter.MainPresenterImpl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView {


    @BindView(R.id.edid_main)
    EditText mEdidMain;
    @BindView(R.id.edit_address_main)
    TextView mEditAddressMain;
    @BindView(R.id.img_queryadd)
    CardView mImgQueryadd;
    @BindView(R.id.edit_name_main)
    EditText mEditNameMain;
    @BindView(R.id.edit_phone_main)
    EditText mEditPhoneMain;
    @BindView(R.id.submite)
    Button mSubmite;
    private ListView mListView;
    private PopupWindow popupWindow;
    private ArrayList<String> list = new ArrayList<>();
    private String string;

    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        mListView = view.findViewById(R.id.mlist_view);
        popupWindow = new PopupWindow(view, dip2px(this, 220), dip2px(this, 200));
        initPopupWindou(popupWindow);

        mPresenter.findAllMarketDepartmentToNet(this, Contants.IMEI);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEditAddressMain.setText(list.get(position));
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }


    @OnClick({R.id.img_queryadd, R.id.submite,R.id.quxiao})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_queryadd:
                popupWindow.showAsDropDown(mImgQueryadd);

                break;

            case R.id.quxiao:
                finish();
                break;

            case R.id.submite:
                String mainContain = mEdidMain.getText().toString();
                String mAddressMain = mEditAddressMain.getText().toString();
                String mPhoneMain = mEditPhoneMain.getText().toString();
                String mNameMain = mEditNameMain.getText().toString();

                if (!TextUtils.isEmpty(mainContain) && mainContain.trim() != "") {
                    if (!TextUtils.isEmpty(mPhoneMain) && mPhoneMain.length() == 11 && mPhoneMain.trim() != "") {
                        if (!TextUtils.isEmpty(mAddressMain) && mAddressMain.trim() != "") {
                            if (!TextUtils.isEmpty(mNameMain) && mNameMain.trim() != "") {

                                HashMap<String, String> map = new HashMap<>();
                                map.put("content",mainContain);
                                map.put("consultUserName",mNameMain);
                                map.put("consultUserPhone",mPhoneMain);
                                map.put("mdName",mAddressMain);
                                mPresenter.addConsultToNet(Contants.IMEI,map); 
                            }else {
                                Toast.makeText(this, "姓名不可为空", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "地址不可为空", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    // TODO: 2018/11/9 弹框
    private void initPopupWindou(PopupWindow popupWindow) {
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

    }

    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void findAllMarketDepartmentSuccess(List<Department> result) {
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i).getDepName());
        }

        mListView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));
    }

    @Override
    public void findAllMarketDepartmentFault(String errorMsg) {

    }

    @Override
    public void addConsultToNet(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
