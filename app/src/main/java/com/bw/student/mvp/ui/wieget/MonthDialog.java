//package com.bw.student.mvp.ui.wieget;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.LinearLayout;
//
//import com.bw.student.R;
//
//
///**
// * @author Administrator QQ:1228717266
// * @name BwStudent
// * @class name：com.bw.student.mvp.ui.wieget
// * @time 2018/12/7 23:57
// */
//public class MyChooseMonthDialog extends AlertDialog implements DatePicker.OnDateChangedListener, View.OnClickListener {
//    private static final String YEAR = "YEAR";
//    private static final String MONTH = "MONTH";
//    private static final String DAY = "DAY";
//
//    private final DatePicker mDatePicker;
//    private final MyChooseMonthDialog.OnDateSetListener mCallBack;
//
//
//    /**
//     * The callback used to indicate the user is done filling in the date.
//     */
//    public interface OnDateSetListener {
//        void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth);
//    }
//
//    /**
//     * @param context     The context the dialog is to run in.
//     * @param callBack    How the parent is notified that the date is set.
//     * @param year        The initial year of the dialog.
//     * @param monthOfYear The initial month of the dialog.
//     * @param dayOfMonth  The initial day of the dialog.
//     */
//    public MyChooseMonthDialog(Context context, MyChooseMonthDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
//        this(context, 0, callBack, year, monthOfYear, dayOfMonth);
//    }
//
//    public MyChooseMonthDialog(Context context, int theme, MyChooseMonthDialog.OnDateSetListener callBack, int year, int monthOfYear,
//                               int dayOfMonth) {
//        this(context, 0, callBack, year, monthOfYear, dayOfMonth, true);
//    }
//
//    /**
//     * @param context     The context the dialog is to run in.
//     * @param theme       the theme to apply to this dialog
//     * @param callBack    How the parent is notified that the date is set.
//     * @param year        The initial year of the dialog.
//     * @param monthOfYear The initial month of the dialog.
//     * @param dayOfMonth  The initial day of the dialog.
//     */
//    public MyChooseMonthDialog(Context context, int theme, MyChooseMonthDialog.OnDateSetListener callBack, int year, int monthOfYear,
//                               int dayOfMonth, boolean isDayVisible) {
//        super(context, theme);
//        mCallBack = callBack;
//        Context themeContext = getContext();
//        setIcon(0);
//        LayoutInflater inflater = (LayoutInflater) themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(R.layout.datepicker_dialog, null);
//        Button dd_btn_sure = (Button) view.findViewById(R.id.dd_btn_sure);
//        dd_btn_sure.setOnClickListener(this);
////        setView(view);
//        mDatePicker = (DatePicker) view.findViewById(R.id.datePickerStart);
//        mDatePicker.init(year, monthOfYear, dayOfMonth, this);
//        mDatePicker.setMaxDate(System.currentTimeMillis());
//        setMyStyle(mDatePicker);
//        // 如果要隐藏当前日期，则使用下面方法。
//        if (!isDayVisible) {
//            hidDay(mDatePicker);
//        }
//    }
//
//    private void setMyStyle(DatePicker mDatePicker) {
//        LinearLayout llFirst = (LinearLayout) mDatePicker.getChildAt(0);
//        LinearLayout llSecond = (LinearLayout) llFirst.getChildAt(0);
//        llSecond.setPadding(0, 0,0, 0);
//        llSecond.setBackgroundResource(android.R.color.transparent);//设置datepickerdialog的背景
//
////        for (int i = 0; i < llSecond.getChildCount(); i++) {
////            NumberPicker picker = (NumberPicker) llSecond.getChildAt(i); // Numberpickers in llSecond
////            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
////            for (Field pf : pickerFields) {
////                //更改分割线的颜色
////                if (pf.getName().equals("mSelectionDivider")) {
////                    pf.setAccessible(true);
////                    try {
//////                        pf.set(picker, ContextCompat.getDrawable(MyApplication.getInstance(), R.color.investor_ilp_red));
////                    } catch (IllegalAccessException e) {
////                        e.printStackTrace();
////                    }
////                    break;
////                }
////            }
////        }
//    }
//
//    private void hidDay(DatePicker mDatePicker) {
//        if(mDatePicker!=null){
//            ((ViewGroup)((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
//        }
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.dd_btn_sure:
//                tryNotifyDateSet();
//                this.dismiss();
//                break;
//        }
//    }
//
//    @Override
//    public void onDateChanged(DatePicker view, int year, int month, int day) {
//        if (view.getId() == R.id.datePickerStart)
//            mDatePicker.init(year, month, day, this);
//        // updateTitle(year, month, day);
//    }
//
//    /**
//     * 获得开始日期的DatePicker
//     *
//     * @return The calendar view.
//     */
//    public DatePicker getDatePickerStart() {
//        return mDatePicker;
//    }
//
//    /**
//     * Sets the start date.
//     *
//     * @param year        The date year.
//     * @param monthOfYear The date month.
//     * @param dayOfMonth  The date day of month.
//     */
//    public void updateStartDate(int year, int monthOfYear, int dayOfMonth) {
//        mDatePicker.updateDate(year, monthOfYear, dayOfMonth);
//    }
//
//
//    private void tryNotifyDateSet() {
//        if (mCallBack != null) {
//            mDatePicker.clearFocus();
//            mCallBack.onDateSet(mDatePicker, mDatePicker.getYear(), mDatePicker.getMonth(),
//                    mDatePicker.getDayOfMonth());
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        // tryNotifyDateSet();
//        super.onStop();
//    }
//
//    @Override
//    public Bundle onSaveInstanceState() {
//        Bundle state = super.onSaveInstanceState();
//        state.putInt(YEAR, mDatePicker.getYear());
//        state.putInt(MONTH, mDatePicker.getMonth());
//        state.putInt(DAY, mDatePicker.getDayOfMonth());
//        return state;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        int year = savedInstanceState.getInt(YEAR);
//        int month = savedInstanceState.getInt(MONTH);
//        int day = savedInstanceState.getInt(DAY);
//        mDatePicker.init(year,month,day, this);
//    }
//}
//}
