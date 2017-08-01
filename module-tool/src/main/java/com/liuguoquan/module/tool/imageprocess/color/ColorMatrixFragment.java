package com.liuguoquan.module.tool.imageprocess.color;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.rxjava.PausedHandlerScheduler;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 16:13.
 */

public class ColorMatrixFragment extends AppBaseFragment {

  @BindView(R2.id.image) ImageView mImage;
  @BindView(R2.id.group) GridLayout mGroup;

  private Bitmap mBitmap;

  private EditText[] mEditTexts = new EditText[20];
  private int mEtWidth;
  private int mEtHeight;

  private float[] mColorMatrix = new float[20];

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_color_matrix;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "颜色矩阵";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());

    mGroup.post(new Runnable() {
      @Override public void run() {
        // 获取宽高信息
        mEtWidth = mGroup.getWidth() / 5;
        mEtHeight = mGroup.getHeight() / 4;
        addEts();
        initMatrix();
      }
    });

    Observable.just("123")
        .subscribeOn(Schedulers.io())
        .map(new Function<String, Bitmap>() {
          @Override public Bitmap apply(@NonNull String s) throws Exception {
            return BitmapFactory.decodeResource(getResources(), R.drawable.test1);
          }
        })
        .observeOn(PausedHandlerScheduler.from(getHandler()))
        .compose(mLifecycleProvider.<Bitmap>bindUntilEvent(FragmentEvent.DESTROY))
        .subscribe(new Consumer<Bitmap>() {
          @Override public void accept(@NonNull Bitmap bitmap) throws Exception {
            mBitmap = bitmap;
            mImage.setImageBitmap(mBitmap);
          }
        });
  }

  @OnClick({ R2.id.change, R2.id.reset }) public void onClick(View v) {
    int i = v.getId();
    if (i == R.id.change) {
      submit();
    } else if (i == R.id.reset) {
      reset();
    }
  }

  private void reset() {
    initMatrix();
    getMatrix();
    setImageMatrix();
  }

  private void submit() {
    getMatrix();
    setImageMatrix();
  }

  //获取矩阵值
  private void getMatrix() {
    for (int i = 0; i < 20; i++) {
      mColorMatrix[i] = Float.valueOf(mEditTexts[i].getText().toString()).floatValue();
    }
  }

  // 将矩阵值设置到图像
  private void setImageMatrix() {
    Bitmap bmp =
        Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
    colorMatrix.set(mColorMatrix);

    Canvas canvas = new Canvas(bmp);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    canvas.drawBitmap(mBitmap, 0, 0, paint);
    mImage.setImageBitmap(bmp);
  }

  // 添加EditText
  private void addEts() {
    for (int i = 0; i < 20; i++) {
      EditText editText = new EditText(getActivity());
      mEditTexts[i] = editText;
      mGroup.addView(editText, mEtWidth, mEtHeight);
    }
  }

  // 初始化颜色矩阵为初始状态
  private void initMatrix() {
    for (int i = 0; i < 20; i++) {
      if (i % 6 == 0) {
        mEditTexts[i].setText(String.valueOf(1));
      } else {
        mEditTexts[i].setText(String.valueOf(0));
      }
    }
  }
}
