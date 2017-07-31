package com.liuguoquan.commom.base;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import com.liuguoquan.commom.R;
import com.mdroid.lib.core.dialog.CenterDialog;
import com.mdroid.lib.core.dialog.IDialog;
import com.mdroid.lib.core.utils.ActivityUtil;
import com.mdroid.lib.imagepick.MediaSelectFragment;
import com.mdroid.lib.imagepick.base.ContainerActivity;
import com.orhanobut.dialogplus.DialogPlus;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Description：增加权限的检查与申请
 */
@RuntimePermissions
public abstract class PermissionFragment<V extends AppBaseView, T extends AppBaseFragmentPresenter<V>>
    extends AppBaseFragment<V, T> {

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    PermissionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
        grantResults);
  }

  //////////////////////////////
  // 相机权限请求 start
  //////////////////////////////
  public void showCameraWithCheck() {
    PermissionFragmentPermissionsDispatcher.showCameraWithCheck(this);
  }

  @NeedsPermission({ Manifest.permission.CAMERA }) public void showCamera() {
    // 需要重写
  }

  @OnShowRationale({ Manifest.permission.CAMERA }) void showRationaleForCamera(
      PermissionRequest request) {
    request.proceed();
  }

  @OnPermissionDenied({ Manifest.permission.CAMERA }) void showDeniedForCamera() {

  }

  @OnNeverAskAgain({ Manifest.permission.CAMERA }) void showNeverAskForCamera() {
    showSettingDialog("拍照");
  }
  //////////////////////////////
  // 相机权限请求 end
  //////////////////////////////

  //////////////////////////////
  // 媒体文件选择请求 start
  //////////////////////////////
  public void showMediaSelectWithCheck(Bundle bundle, int requestCode) {
    PermissionFragmentPermissionsDispatcher.showMediaSelectWithCheck(this, bundle, requestCode);
  }

  @NeedsPermission({ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE })
  public void showMediaSelect(Bundle bundle, int requestCode) {
    ActivityUtil.startActivity(this, ContainerActivity.class, MediaSelectFragment.class, bundle,
        requestCode);
  }

  @OnShowRationale({ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showRationaleForMediaSelect(PermissionRequest request) {
    request.proceed();
  }

  @OnPermissionDenied({ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showDeniedForMediaSelect() {
  }

  @OnNeverAskAgain({ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showNeverAskForMediaSelect() {
    showSettingDialog("相机和存储");
  }
  //////////////////////////////
  // 媒体文件选择请求 end
  //////////////////////////////

  //////////////////////////////
  // 存储权限请求 start
  //////////////////////////////
  public void showWriteStorageWithCheck() {
    PermissionFragmentPermissionsDispatcher.showWriteStorageWithCheck(this);
  }

  @NeedsPermission({ Manifest.permission.WRITE_EXTERNAL_STORAGE }) public void showWriteStorage() {
    // 需要重写
  }

  @OnShowRationale({ Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showRationaleForWriteStorage(PermissionRequest request) {
    request.proceed();
  }

  @OnPermissionDenied({ Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showDeniedForWriteStorage() {

  }

  @OnNeverAskAgain({ Manifest.permission.WRITE_EXTERNAL_STORAGE })
  void showNeverAskForWriteStorage() {
    showSettingDialog("存储");
  }
  //////////////////////////////
  // 存储权限请求 end
  //////////////////////////////

  //读取电话权限
  public void showReadPhoneWithCheck() {
    PermissionFragmentPermissionsDispatcher.showReadPhoneWithCheck(this);
  }

  @NeedsPermission({ Manifest.permission.READ_PHONE_STATE }) public void showReadPhone() {
    // 需要重写
  }

  @OnShowRationale({ Manifest.permission.READ_PHONE_STATE }) void showRationaleForReadPhone(
      PermissionRequest request) {
    request.proceed();
  }

  @OnPermissionDenied({ Manifest.permission.READ_PHONE_STATE }) void showDeniedForReadPhone() {

  }

  @OnNeverAskAgain({ Manifest.permission.READ_PHONE_STATE }) void showNeverAskForReadPhone() {
    showSettingDialog("电话");
  }

  protected void showSettingDialog(String feature) {
    String name = getString(R.string.app_name);
    CenterDialog.create(getActivity(), "提示",
        String.format("在设置-应用-%s-权限中开启%s权限, 以正常使用%s功能", name, feature, name), "取消",
        new IDialog.OnClickListener() {
          @Override public void onClick(DialogPlus dialog, View view) {
            dialog.dismiss();
          }
        }, "去设置", new IDialog.OnClickListener() {
          @Override public void onClick(DialogPlus dialog, View view) {
            dialog.dismiss();
            try {
              Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
              intent.setData(
                  Uri.parse(String.format("package:%s", getActivity().getPackageName())));
              getActivity().startActivity(intent);
            } catch (Exception e) {
              Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
              getActivity().startActivity(intent);
            }
          }
        }).show();
  }
}
