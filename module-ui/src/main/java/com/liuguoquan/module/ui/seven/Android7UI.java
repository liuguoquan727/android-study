package com.liuguoquan.module.ui.seven;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.PermissionFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.io.File;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:41.
 */

public class Android7UI extends PermissionFragment {

  private Uri mImageUri;
  private Uri outputUri;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_android7;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "Android7";
  }

  @Override protected void initData(Bundle savedInstanceState) {
    showMediaSelectWithCheck(null, 0);
  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @Override public void showMediaSelect(Bundle bundle, int requestCode) {
  }

  @OnClick({ R2.id.capture, R2.id.crop }) public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.capture) {
      newCapture();
    } else if (i == R.id.crop) {
      newCrop();
    }
  }

  public void capture() {
    File file = new File(Environment.getExternalStorageDirectory(),
        "/temp/" + System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    Uri imageUri = Uri.fromFile(file);
    Intent intent = new Intent();
    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
    startActivityForResult(intent, 1006);
  }

  public void newCapture() {
    File file = new File(Environment.getExternalStorageDirectory(),
        "/temp/" + System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    mImageUri = FileProvider.getUriForFile(getActivity(), "com.liuguoquan.module.ui.provider",
        file);//通过FileProvider创建一个content类型的Uri
    Log.d("lgq", mImageUri.toString());
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍取的照片保存到指定URI
    startActivityForResult(intent, 1006);
  }

  private void crop() {
    File file = new File(Environment.getExternalStorageDirectory(),
        "/temp/" + System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    Uri outputUri = Uri.fromFile(file);
    Uri imageUri = Uri.fromFile(new File("/storage/emulated/0/Image/1474357626750.jpg"));
    Intent intent = new Intent("com.android.camera.action.CROP");
    intent.setDataAndType(imageUri, "image/*");
    intent.putExtra("crop", "true");
    intent.putExtra("aspectX", 1);
    intent.putExtra("aspectY", 1);
    intent.putExtra("scale", true);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    intent.putExtra("noFaceDetection", true); // no face detection
    startActivityForResult(intent, 1008);
  }

  public void newCrop() {
    File file = new File(Environment.getExternalStorageDirectory(),
        "/temp/" + System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    //输出路径只能用Uri.fromFile，不然报错
    //outputUri = FileProvider.getUriForFile(this, "com.liuguoquan.module.ui.provider",file);
    outputUri = Uri.fromFile(file);
    Uri imageUri = FileProvider.getUriForFile(getActivity(), "com.michael.materialdesign.provider",
        new File("/storage/emulated/0/temp/1476865100115.jpg"));//通过FileProvider创建一个content类型的Uri
    //grantUriPermission(getPackageName(),imageUri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
    Intent intent = new Intent("com.android.camera.action.CROP");
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    intent.setDataAndType(mImageUri, "image/*");
    intent.putExtra("crop", "true");
    intent.putExtra("aspectX", 1);
    intent.putExtra("aspectY", 1);
    intent.putExtra("scale", true);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    intent.putExtra("noFaceDetection", true); // no face detection
    startActivityForResult(intent, 1008);
  }

  private void pick() {
    Intent intent = getPickIntentWithGallery();
    startActivityForResult(intent, 1009);
  }

  /**
   * 获取选择照片的Intent
   */
  public Intent getPickIntentWithGallery() {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
    intent.setType("image/*");//从所有图片中进行选择
    return intent;
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1006) {
      getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, mImageUri));
      if (data == null) {
        Log.d("lgq", "data == null");
      }
    }
  }
}
