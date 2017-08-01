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
public class Arc extends View {
  public Arc(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
   *
   * 参数列表：
   *
   * oval>指定圆弧的外轮廓矩形区域
   *
   * startAngle>圆弧起始角度，单位为度
   *
   * sweepAngle>圆弧扫过的角度，顺时针方向，单位为度
   *
   * useCenter>如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形
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
    RectF rel = new RectF(100, 100, 300, 300);
    //实心圆弧
    canvas.drawArc(rel, 0, 270, false, paint);
    //实心圆弧 将圆心包含在内
    RectF rel2 = new RectF(100, 400, 300, 600);
    canvas.drawArc(rel2, 0, 270, true, paint);
    //设置空心Style
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(20);

    RectF rel3 = new RectF(100, 700, 300, 900);
    canvas.drawArc(rel3, 0, 270, false, paint);

    RectF rel4 = new RectF(100, 1000, 300, 1200);
    canvas.drawArc(rel4, 0, 270, true, paint);
  }
}
