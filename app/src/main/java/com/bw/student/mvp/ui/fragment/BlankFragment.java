package com.bw.student.mvp.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.bw.student.R;
import com.bw.student.application.MyApplication;
import com.bw.student.mvp.base.BaseFragment;
import com.bw.student.mvp.contract.SplashContract;
import com.bw.student.mvp.model.net.DataClean;
import com.bw.student.mvp.model.net.NetworkUtils;
import com.bw.student.mvp.model.utils.RsaCoder;
import com.bw.student.mvp.presenter.SplashPresenterImpl;
import com.bw.student.mvp.ui.activity.SplashActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import xyz.zpayh.hdimage.HDImageView;
import xyz.zpayh.hdimage.state.ScaleType;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment<SplashPresenterImpl> implements SplashContract.SplashView {

    @BindView(R.id.slash_activity)
    HDImageView mSlashActivity;
    private int REQUEST_PHONE_STATE = 0;
    private String deviceId;

    @Override
    protected int protetedId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected SplashPresenterImpl initPresenter() {
        return new SplashPresenterImpl();
    }

    @Override
    protected void initData() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            toast("需要动态获取权限");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE);
        }else {
            TelephonyManager systemService = (TelephonyManager) MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = systemService.getDeviceId();
        }




        if (!TextUtils.isEmpty(deviceId)) {
            String publicKey = null;
            try {
                publicKey = RsaCoder.encryptByPublicKey(deviceId);
                user.edit().putString("imei",publicKey).commit();
                    Logger.e(publicKey);
                if (!TextUtils.isEmpty(publicKey)) {
                    mPresenter.campusStyleShow(getActivity(), this, publicKey);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "无法获取手机识别码", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void campusStyleShowSuccess(String result) {
        Logger.e(result);

        if (NetworkUtils.isNetworkAvailable(MyApplication.getContext())){
            DataClean.clearAllCache(MyApplication.getContext());
        }
        mSlashActivity.setImageURI(result);
        mSlashActivity.setMaxScale(3);
        mSlashActivity.setScaleType(ScaleType.CENTER_CROP);

    }

    @Override
    public void campusStyleShowError(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();

        Logger.e(errorMsg);
    }
}
