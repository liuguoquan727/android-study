package com.liuguoquan.module.ui.design;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;

public class PaletteActivity extends AppCompatActivity {

  @BindView(R2.id.image) ImageView mImage;
  @BindView(R2.id.tool_bar) Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_palette);
    ButterKnife.bind(this);

    //Palette取色器
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test5);
    mImage.setImageBitmap(bitmap);

    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
      @Override public void onGenerated(Palette palette) {

        //这里我们获取的是图片充满活力的黑的色调
        Palette.Swatch swatch = palette.getDominantSwatch();
        //Palette.Swatch swatch2 = palette.getVibrantSwatch();

        //设置Toolbar颜色
        mToolbar.setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          //设置系统状态栏颜色
          getWindow().setStatusBarColor(swatch.getRgb());
        } else {
          Window window = getWindow();
          window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

          ViewGroup contentView =
              (ViewGroup) PaletteActivity.this.findViewById(android.R.id.content);
          int statusBarHeight = getStatusBarHeight(PaletteActivity.this);

          View statusBarView = contentView.getChildAt(0);
          if (statusBarView != null) {
            ViewCompat.setFitsSystemWindows(statusBarView, true);
          }

          statusBarView = new View(PaletteActivity.this);
          ViewGroup.LayoutParams lp =
              new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
          statusBarView.setBackgroundColor(Color.RED);
          contentView.addView(statusBarView, 0, lp);
        }
      }
    });
  }

  public int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }
}
