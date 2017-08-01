package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制线
 * Created by liuguoquan on 16/9/19.
 */
public class Line extends View {

  public Line(Context context) {
    this(context, null);
  }

  public Line(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置颜色
    paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
    //绘制线
    canvas.drawLine(100, 100, 300, 300, paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(5);
    //绘制空心圆
    canvas.drawLine(200, 200, 500, 600, paint);
  }
}
