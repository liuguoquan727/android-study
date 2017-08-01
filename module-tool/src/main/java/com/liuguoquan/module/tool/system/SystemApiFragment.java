package com.liuguoquan.module.tool.system;

import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.Utils;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

import static com.blankj.utilcode.util.AppUtils.getAppSignature;

/**
 * Created by liuguoquan on 2017/7/25.
 */

public class SystemApiFragment extends AppBaseFragment {

  @BindView(R2.id.root) TextView mRoot;
  @BindView(R2.id.package_name) TextView mPackageName;
  @BindView(R2.id.app_settings) TextView mAppSettings;
  @BindView(R2.id.app_name) TextView mAppName;
  @BindView(R2.id.app_icon) ImageView mAppIcon;
  @BindView(R2.id.app_path) TextView mAppPath;
  @BindView(R2.id.app_version) TextView mAppVersion;
  @BindView(R2.id.app_code) TextView mAppCode;
  @BindView(R2.id.app_system_app) TextView mAppSystemApp;
  @BindView(R2.id.app_debug) TextView mAppDebug;
  @BindView(R2.id.app_signature) TextView mAppSignature;
  @BindView(R2.id.app_sha) TextView mAppSha1;
  @BindView(R2.id.app_md5) TextView mAppMd5;

  @Override public void initData(Bundle savedInstanceState) {
    Utils.init(getActivity());
  }

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override public int getContentView() {
    return R.layout.module_tool_fragment_system_api;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "系统Api学习";
  }

  @Override public void initView(View view) {
    requestBaseInit(getPageTitle());
    init();
  }

  private void init() {
    //mRoot.setText("是否Root: " + AppUtils.isAppRoot());
    mPackageName.setText("App包名: " + AppUtils.getAppPackageName());
    mAppName.setText("App名称: " + AppUtils.getAppName());
    mAppIcon.setImageDrawable(AppUtils.getAppIcon());
    mAppPath.setText("App路径: " + AppUtils.getAppPath());
    mAppVersion.setText("App版本: " + AppUtils.getAppVersionName());
    mAppCode.setText("App版本码: " + AppUtils.getAppVersionCode());
    mAppSystemApp.setText("是否是系统App: " + AppUtils.isSystemApp());
    mAppDebug.setText("是否是Debug版: " + AppUtils.isAppDebug());
    mAppSignature.setText("App签名: " + getAppSignature());
    mAppSha1.setText("应用签名的SHA1值: " + AppUtils.getAppSignatureSHA1());
    Signature[] signatures = AppUtils.getAppSignature();
    String md5 = EncryptUtils.encryptMD5ToString(signatures[0].toByteArray())
        .replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    mAppMd5.setText("应用签名的SHA1值: " + md5);
  }

  @OnClick({ R2.id.app_settings, R2.id.app_all_info }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.app_settings) {
      AppUtils.getAppDetailsSettings();
    } else if (id == R.id.app_all_info) {
      ActivityUtil.startActivity(this, AllAppInfoFragment.class);
    }
  }
}
