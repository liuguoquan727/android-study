package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制点
 * Created by liuguoquan on 16/9/19.
 */
public class Point extends View {

  public Point(Context context) {
    super(context);
  }

  public Point(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public void init() {

  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置颜色
    paint.setColor(Color.RED);
    paint.setStrokeWidth(5);
    //绘制线
    canvas.drawPoint(300, 500, paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(5);
    //绘制空心圆
    canvas.drawPoint(500, 800, paint);
  }
}
