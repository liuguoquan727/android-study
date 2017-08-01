package com.liuguoquan.module.tool.imageprocess.color;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
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
 * Description: 像素处理效果
 *
 * Created by liuguoquan on 2017/8/1 16:23.
 */

public class PixelEffectFragment extends AppBaseFragment {

  @BindView(R2.id.image) ImageView mImage;
  private Bitmap mBitmap;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_pixeleffect;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "像素处理效果";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());

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

  @OnClick({
      R2.id.origin, R2.id.gray, R2.id.reverse, R2.id.desaturate, R2.id.high_saturation,
      R2.id.negative, R2.id.old, R2.id.relief
  }) public void onClick(View v) {
    int i = v.getId();
    if (i == R.id.origin) {
      mImage.setImageBitmap(mBitmap);
    } else if (i == R.id.gray) {
      mImage.setImageBitmap(ImageHelper.handleImageGrayEffect(mBitmap));
    } else if (i == R.id.reverse) {
      mImage.setImageBitmap(ImageHelper.handleImageReverseEffect(mBitmap));
    } else if (i == R.id.desaturate) {
      mImage.setImageBitmap(ImageHelper.handleImageDesaturateEffect(mBitmap));
    } else if (i == R.id.high_saturation) {
      mImage.setImageBitmap(ImageHelper.handleImageHighSaturationEffect(mBitmap));
    } else if (i == R.id.negative) {
      mImage.setImageBitmap(ImageHelper.handleImageNegative(mBitmap));
    } else if (i == R.id.old) {
      mImage.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(mBitmap));
    } else if (i == R.id.relief) {
      mImage.setImageBitmap(ImageHelper.handleImagePixelsRelief(mBitmap));
    }
  }
}
