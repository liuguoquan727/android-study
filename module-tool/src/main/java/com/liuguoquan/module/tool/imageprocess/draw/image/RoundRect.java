package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆角矩形
 * Created by liuguoquan on 16/9/19.
 */
public class RoundRect extends View {
  public RoundRect(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RoundRect(Context context) {
    super(context);
  }

  /**
   * drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint
   * paint)
   * drawRoundRect(RectF rect, float rx, float ry, Paint paint)
   *
   * 参数列表：
   *
   * left>图形left的x坐标
   *
   * top>图形top的y坐标
   *
   * right>图形right的x坐标
   *
   * bottom>图形bottom的y坐标
   *
   * rx>x方向的圆角半径
   *
   * ry>y方向的圆角半径
   *
   * paint>绘制风格
   */

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置颜色
    paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
    //绘制圆角矩形
    canvas.drawRoundRect(100, 100, 300, 300, 30, 30, paint);
    //上面代码等同于
    //RectF rel=new RectF(100,100,300,300);
    //canvas.drawRoundRect(rel,30,30,paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(20);
    //绘制空心圆角矩形
    canvas.drawRoundRect(100, 400, 600, 800, 30, 30, paint);
  }
}
