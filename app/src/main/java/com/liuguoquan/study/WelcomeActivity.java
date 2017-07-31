package com.liuguoquan.study;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.liuguoquan.commom.base.DBKeys;
import com.mdroid.DBUtils;
import com.mdroid.lib.core.dialog.CenterDialog;
import com.mdroid.lib.core.dialog.IDialog;
import com.mdroid.lib.core.utils.ActivityUtil;
import com.mdroid.lib.core.utils.Toost;
import com.orhanobut.dialogplus.DialogPlus;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.RuntimePermissions;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 16:09.
 */

@RuntimePermissions public class WelcomeActivity extends AppCompatActivity {

  private Disposable mTimerSubscription;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    showRationaleForPermission();
  }

  private void showRationaleForPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && DBUtils.read(DBKeys.PERMISSION_CHECK_FLAG,
        true)) {
      DBUtils.write(DBKeys.PERMISSION_CHECK_FLAG, false);
      new CenterDialog.Builder(this).noncancelable()
          .footer()
          .contentLayoutRes(R.layout.dialog_request_permission)
          .build()
          .setPositive("好的", new IDialog.OnClickListener() {
            @Override public void onClick(DialogPlus dialog, View view) {
              dialog.dismiss();
              WelcomeActivityPermissionsDispatcher.preStartWithCheck(WelcomeActivity.this);
            }
          })
          .show();
    } else {
      preStart();
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    WelcomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
        grantResults);
  }

  @NeedsPermission({
      Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,
      Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
  }) void preStart() {
    mTimerSubscription = Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override public void accept(@io.reactivex.annotations.NonNull Long aLong)
              throws Exception {
            ActivityUtil.startActivity(WelcomeActivity.this,
                new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
          }
        });
  }

  @OnPermissionDenied({
      Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,
      Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
  }) void showDeniedForPermission() {
    deniedPermission();
  }

  @OnNeverAskAgain({
      Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,
      Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
  }) void showNeverAskForPermission() {
    deniedPermission();
  }

  private void deniedPermission() {
    preStart();
    StringBuilder permission = new StringBuilder();
    if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
      permission.append("位置权限");
    }
    if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.CAMERA)) {
      if (permission.length() > 0) permission.append(", ");
      permission.append("拍照权限");
    }
    if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
      if (permission.length() > 0) permission.append(", ");
      permission.append("存储权限");
    }
    if (!PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
      if (permission.length() > 0) permission.append(", ");
      permission.append("电话权限");
    }
    Toost.message(String.format("很遗憾, 部分功能的使用依赖于%s权限", permission));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mTimerSubscription != null && !mTimerSubscription.isDisposed()) {
      mTimerSubscription.dispose();
    }
  }
}
