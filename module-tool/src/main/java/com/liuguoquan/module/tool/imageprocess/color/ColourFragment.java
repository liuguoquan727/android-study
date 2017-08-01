package com.liuguoquan.module.tool.imageprocess.color;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import butterknife.BindView;
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
 * Description:色彩矩阵
 *
 * Created by liuguoquan on 2017/8/1 15:59.
 */

public class ColourFragment extends AppBaseFragment implements SeekBar.OnSeekBarChangeListener {

  public static final int MAX_VALUE = 255;
  public static final int MID_VALUE = 127;

  @BindView(R2.id.image) ImageView mImage;
  @BindView(R2.id.hue) SeekBar mHue;
  @BindView(R2.id.saturation) SeekBar mSaturation;
  @BindView(R2.id.lum) SeekBar mLum;

  private float mHueValue;
  private float mSaturationValue;
  private float mLumValue;

  private Bitmap mBitmap;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_colour;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "色彩矩阵";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    mHue.setOnSeekBarChangeListener(this);
    mSaturation.setOnSeekBarChangeListener(this);
    mLum.setOnSeekBarChangeListener(this);
    mHue.setMax(MAX_VALUE);
    mHue.setProgress(MID_VALUE);
    mSaturation.setMax(MAX_VALUE);
    mSaturation.setProgress(MID_VALUE);
    mLum.setMax(MAX_VALUE);
    mLum.setProgress(MID_VALUE);
    Observable.just("123")
        .subscribeOn(Schedulers.io())
        .map(new Function<String, Bitmap>() {
          @Override public Bitmap apply(@NonNull String s) throws Exception {
            return BitmapFactory.decodeResource(getResources(), R.drawable.test3);
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

  @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    int i = seekBar.getId();
    if (i == R.id.hue) {
      mHueValue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
    } else if (i == R.id.saturation) {
      mSaturationValue = progress * 1.0f / MID_VALUE; //0~2
    } else if (i == R.id.lum) {
      mLumValue = progress * 1.0f / MID_VALUE; //0~2
    }
    if (mBitmap == null) {
      return;
    }
    mImage.setImageBitmap(
        ImageHelper.handleImageEffect(mBitmap, mHueValue, mSaturationValue, mLumValue));
  }

  @Override public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override public void onStopTrackingTouch(SeekBar seekBar) {

  }
}
