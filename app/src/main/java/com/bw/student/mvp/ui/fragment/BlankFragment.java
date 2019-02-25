package com.bw.student.mvp.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.bw.student.R;
import com.bw.student.application.MyApplication;
import com.bw.student.mvp.base.BaseFragment;
import com.bw.student.mvp.contract.SplashContract;
import com.bw.student.mvp.model.bean.UpdateBean;
import com.bw.student.mvp.model.net.DataClean;
import com.bw.student.mvp.model.net.NetworkUtils;
import com.bw.student.mvp.model.utils.AppDownloadManager;
import com.bw.student.mvp.model.utils.RsaCoder;
import com.bw.student.mvp.presenter.SplashPresenterImpl;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import xyz.zpayh.hdimage.HDImageView;
import xyz.zpayh.hdimage.state.ScaleType;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment<SplashPresenterImpl> implements SplashContract.SplashView {

    @BindView(R.id.slash_activity)
    HDImageView mSlashActivity;
    private static final int REQUEST_PHONE_STATE = 0;
    private String deviceId;
    private long id;
    private AppDownloadManager appDownloadManager;

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
        ArrayList<Object> objects = new ArrayList<>();
//        mBtnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(id);
//                Cursor cursor = downloadManager.query(query);
//                if(cursor != null && cursor.moveToFirst()) {
//                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
//                    int status = cursor.getInt(columnIndex);
//                    int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
//                    int reason = cursor.getInt(columnReason);
//                    Log.e("tag","status == "+status +"  reason == "+reason);
//
//                    Toast.makeText(getActivity(),"status == "+status +"  reason == "+reason,Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            toast("需要动态获取权限");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE);
        } else {
            TelephonyManager systemService = (TelephonyManager) MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = systemService.getDeviceId();
        }


        if (!TextUtils.isEmpty(deviceId)) {
            String publicKey = null;
            try {
                publicKey = RsaCoder.encryptByPublicKey(deviceId);
                user.edit().putString("imei", publicKey).commit();
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
        mPresenter.checkNewVersion(getVersionCode(), this);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager systemService = (TelephonyManager) MyApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
                    deviceId = systemService.getDeviceId();
                    if (!TextUtils.isEmpty(deviceId)) {
                        String publicKey = null;
                        try {
                            publicKey = RsaCoder.encryptByPublicKey(deviceId);
                            user.edit().putString("imei", publicKey).commit();
                            Logger.e(publicKey);
                            if (!TextUtils.isEmpty(publicKey)) {
                                mPresenter.campusStyleShow(getActivity(), this, publicKey);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                } else {
                    Toast.makeText(getActivity(), "无法获取手机识别码,请允许读取设备信息权限", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void campusStyleShowSuccess(String result) {
        Logger.e(result);

        if (NetworkUtils.isNetworkAvailable(MyApplication.getContext())) {
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

    @Override
    public void checkNewVersionSuccess(final UpdateBean updateBean) {
        if (updateBean != null && updateBean.getFlag() == 2) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("检测到有新版本")
                    .setCancelable(false)
                    .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            appDownloadManager.downloadApk(updateBean.getUrl(), getActivity().getResources().getString(R.string.app_name), "正在更新");
                            dialogInterface.dismiss();
                            Toast.makeText(getActivity(), "在通知栏关注应用下载进度", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();

        }
    }

    @Override
    public void checkNewVersionError(String errorMsg) {

    }

    @Override
    public void onResume() {
        if (appDownloadManager == null) {
            appDownloadManager = new AppDownloadManager(getActivity());
        }
        super.onResume();
        appDownloadManager.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appDownloadManager.onPause();
    }

    public void update(String url) {
        final DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(getActivity().getResources().getString(R.string.app_name));
        request.setDescription("正在更新");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "my1.apk");
        id = downloadManager.enqueue(request);
        getActivity().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equalsIgnoreCase(downloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
                    Cursor cursor = downloadManager.query(query);
                    if (cursor != null && cursor.moveToNext()) {
                        long aLong = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                        if (aLong == DownloadManager.STATUS_SUCCESSFUL) {
                            installApk();
                        }
                    }
                }
            }
        }, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private void installApk() {
        File apkFile =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "my1.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        startActivity(intent);
    }


    //拿到当前版本号
    private int getVersionCode() {
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
