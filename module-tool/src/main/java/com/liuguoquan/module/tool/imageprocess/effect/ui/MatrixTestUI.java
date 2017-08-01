package com.liuguoquan.module.tool.imageprocess.effect.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.effect.view.ImageMatrixView;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 16:37.
 */

public class MatrixTestUI extends AppBaseFragment {

  @BindView(R2.id.view) ImageMatrixView mImageMatrixView;
  @BindView(R2.id.grid_group) GridLayout mGridGroup;

  private Bitmap mBitmap;
  private int mEtWidth = 0;
  private int mEtHeight = 0;
  private float[] mImageMatrix = new float[9];
  private EditText[] mETs = new EditText[9];

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_effect_matrix;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "矩阵变换";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

    mGridGroup.post(new Runnable() {
      @Override public void run() {
        mEtWidth = mGridGroup.getWidth() / 3;
        mEtHeight = mGridGroup.getHeight() / 3;
        addEts();
        initImageMatrix();
      }
    });
  }

  private void initImageMatrix() {
    for (int i = 0; i < 9; i++) {
      if (i % 4 == 0) {
        mETs[i].setText(String.valueOf(1));
      } else {
        mETs[i].setText(String.valueOf(0));
      }
    }
  }

  private void getImageMatrix() {
    for (int i = 0; i < 9; i++) {
      EditText et = mETs[i];
      mImageMatrix[i] = Float.valueOf(et.getText().toString()).floatValue();
    }
  }

  private void addEts() {
    for (int i = 0; i < 9; i++) {
      EditText et = new EditText(getActivity());
      et.setGravity(Gravity.CENTER);
      mETs[i] = et;
      mGridGroup.addView(et, mEtWidth, mEtHeight);
    }
  }

  @OnClick({ R2.id.change, R2.id.reset }) public void onClick(View v) {
    int i = v.getId();
    if (i == R.id.change) {
      submit();
    } else if (i == R.id.reset) {
      reset();
    }
  }

  private void submit() {
    getImageMatrix();
    Matrix matrix = new Matrix();
    matrix.setValues(mImageMatrix);
    mImageMatrixView.setImageAndMatrix(mBitmap, matrix);
    mImageMatrixView.invalidate();
  }

  private void reset() {
    initImageMatrix();
    getImageMatrix();
    Matrix matrix = new Matrix();
    matrix.setValues(mImageMatrix);
    mImageMatrixView.setImageAndMatrix(mBitmap, matrix);
    mImageMatrixView.invalidate();
  }
}
