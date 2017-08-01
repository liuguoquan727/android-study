package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制线
 * Created by liuguoquan on 16/9/19.
 */
public class BitmapDemo extends View {

  public BitmapDemo(Context context) {
    this(context, null);
  }

  public BitmapDemo(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //创建图片
    Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
    //生成图片内容
    Canvas canvas1 = new Canvas(bitmap);
    Paint paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置颜色
    paint.setColor(Color.RED);
    canvas1.drawOval(100, 100, 200, 300, paint);
    //将图片绘制出来
    canvas.drawBitmap(bitmap, 0, 0, null);
  }
}
