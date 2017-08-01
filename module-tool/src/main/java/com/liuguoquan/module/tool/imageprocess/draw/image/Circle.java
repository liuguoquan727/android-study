package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆形
 * Created by liuguoquan on 16/9/19.
 */
public class Circle extends View {

  public Circle(Context context) {
    this(context, null);
    init();
  }

  public Circle(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public void init() {

  }

  /**
   * drawCircle(float cx, float cy, float radius, Paint paint)
   *
   * 参数列表：
   *
   * cx>圆心的x坐标
   *
   * cy>圆心的y坐标
   *
   * radius>圆的半径
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
    //绘制普通圆
    canvas.drawCircle(200, 200, 100, paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(20);
    //绘制空心圆
    canvas.drawCircle(200, 500, 90, paint);
  }
}
