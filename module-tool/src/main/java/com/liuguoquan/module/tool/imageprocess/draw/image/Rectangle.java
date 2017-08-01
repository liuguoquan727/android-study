package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制矩形
 * Created by liuguoquan on 16/9/19.
 */
public class Rectangle extends View {
  public Rectangle(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public Rectangle(Context context) {
    super(context);
  }

  private void init() {
  }

  /**
   * drawRect(float left, float top, float right, float bottom, Paint paint)
   * drawRect(RectF rect, Paint paint)
   *
   * 参数列表：
   *
   * left>矩形left的x坐标
   *
   * top>矩形top的y坐标
   *
   * right>矩形right的x坐标
   *
   * bottom>矩形bottom的y坐标
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
    //绘制正方形
    canvas.drawRect(100, 100, 300, 300, paint);
    //上面代码等同于
    //RectF rel=new RectF(100,100,300,300);
    //canvas.drawRect(rel, paint);

    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(20);
    //绘制空心矩形
    canvas.drawRect(100, 400, 600, 800, paint);
  }
}
