package com.bw.student.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.student.R;
import com.bw.student.application.Contants;
import com.bw.student.mvp.base.BaseFragment;
import com.bw.student.mvp.contract.MessageContract;
import com.bw.student.mvp.model.bean.Condition;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.presenter.MessagePresenterImpl;
import com.bw.student.mvp.ui.adapter.MessageAdapter;
import com.bw.student.mvp.ui.adapter.TextAdapter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static java.util.Calendar.YEAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment<MessagePresenterImpl> implements MessageContract.MessageView, OnDateSetListener {


    @BindView(R.id.message_time)
    TextView mMessageTime;
    @BindView(R.id.pull_time)
    LinearLayout mPullTime;
    @BindView(R.id.list_view)
    RecyclerView mListView;
    @BindView(R.id.message_recy)
    RecyclerView mMessageRecy;
    @BindView(R.id.wuwang)
    ImageView mWuWang;
    private ArrayList<String> mDepartment;
    private int page = 1;
    private int count = 5;
    private int yyyy;
    private int mm;
    private LinearLayoutManager manager;
    private List<Condition> conditionList = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private HashMap<String, String> map;
    private LinearLayoutManager layoutManager;
    private Calendar instance;
    private DatePicker dp;
    private String time;
    private String dates;
    private int lastVisibleItemPosition;
    private int anInt;

    @Override
    protected int protetedId() {
        return R.layout.fragment_message;
    }

    @Override
    protected MessagePresenterImpl initPresenter() {
        return new MessagePresenterImpl();
    }


    @SuppressLint("WrongConstant")
    @Override
    protected void initData() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(layoutManager);
        mDepartment = new ArrayList<>();


        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mMessageRecy.setLayoutManager(manager);

        map = new HashMap<>();

        messageAdapter = new MessageAdapter(getActivity(), conditionList);
        mMessageRecy.setAdapter(messageAdapter);


        mPresenter.findAllMarketDepartmentToNet(getActivity(), this, Contants.IMEI);
        Date date = new Date();
        // TODO: 2018/12/6 获取当前时间
        instance = Calendar.getInstance();
        instance.setTime(date);
        yyyy = instance.get(YEAR);
        mm = instance.get(Calendar.MONTH);
        dates = yyyy + "-" + (mm + 1);
        mMessageTime.setText(yyyy + "年" + (mm + 1) + "月");

        mPresenter.findAllMarketDepartmentToNet(getActivity(), this, Contants.IMEI);

        mMessageRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == manager.getItemCount() - 1) {
                    count += 5;

                    map.put("mdId", String.valueOf(anInt));
                    map.put("employmentTime", dates);
                    map.put("page", String.valueOf(page));
                    map.put("count", String.valueOf(count));
                    mPresenter.findStudentsByCondition(getActivity(), MessageFragment.this, map, Contants.IMEI);
                }
            }
        });
    }

    @Override
    public void findAllMarketDepartmentSuccess(final List<Department> result) {
        TextAdapter textAdapter = new TextAdapter(result, getActivity());

        mListView.setAdapter(textAdapter);

        textAdapter.setCallBack(new TextAdapter.CallBack() {
            @Override
            public void onCallBack(int position) {
                anInt = position;
                map.put("mdId", String.valueOf(anInt));
                map.put("employmentTime", dates);
                map.put("page", String.valueOf(page));
                map.put("count", String.valueOf(count));
                mPresenter.findStudentsByCondition(getActivity(), MessageFragment.this, map, Contants.IMEI);
            }
        });


    }

    @Override
    public void findAllMarketDepartmentFault(String errorMsg) {
    }

    @Override
    public void findStudentsByConditions(List<Condition> result) {
        mMessageRecy.setVisibility(View.VISIBLE);
        mWuWang.setVisibility(View.GONE);
        conditionList.clear();
        conditionList.addAll(result);
        messageAdapter.notifyDataSetChanged();

    }

    @Override
    public void findStudentsByConditionsError(String message) {
        mMessageRecy.setVisibility(View.GONE);
        mWuWang.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.pull_time)
    public void onClick(View v) {
        switch (v.getId()) {
            default:


                break;
            case R.id.pull_time:
                Calendar cal1 = Calendar.getInstance();
                cal1.set(YEAR, 2018);
                cal1.set(Calendar.MONTH, 0);
                cal1.set(Calendar.DATE, 1);
                TimePickerDialog dialogYearMonth = new TimePickerDialog.Builder()
                        .setYearText("年")
                        .setMonthText("月")
                        .setMinMillseconds(cal1.getTimeInMillis())
                        .setMaxMillseconds(getEndDayOfYear().getTime())
                        .setCurrentMillseconds(System.currentTimeMillis())
                        .setType(Type.YEAR_MONTH)
                        .setTitleStringId("请选择")
                        .setCallBack(this)
                        .build();
                dialogYearMonth.show(getActivity().getSupportFragmentManager(), "YEAR_MONTH");
                break;
        }
    }

    public void display() {
        int i = mm + 1;
        map.put("mdId", String.valueOf(anInt));
        map.put("employmentTime", dates);
        map.put("page", String.valueOf(page));
        map.put("count", String.valueOf(count));
        mPresenter.findStudentsByCondition(getActivity(), this, map, Contants.IMEI);
        mMessageTime.setText(time);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");//精确到分钟
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM");
        time = format.format(date);
        dates = formats.format(date);
        display();
    }


    public static java.util.Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }


    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }


    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(YEAR));
    }


}
