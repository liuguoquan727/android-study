package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuguoquan on 16/9/19.
 */
public class Oval extends View {

  public Oval(Context context) {
    this(context, null);
  }

  public Oval(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * drawOval(float left, float top, float right, float bottom, Paint paint)
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
   * paint>绘制风格
   */

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置颜色
    paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
    //绘制椭圆
    //    canvas.drawOval(100, 100, 500, 300, paint);  //api21
    canvas.drawOval(new RectF(100, 100, 500, 300), paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(20);
    //绘制空心椭圆
    //    canvas.drawOval(100, 400, 600, 800, paint); //api21
    canvas.drawOval(new RectF(100, 400, 600, 800), paint);
  }
}
