package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制各种弧形
 * Created by liuguoquan on 16/9/19.
 */
public class PathArc extends View {

  private Paint paint;

  public PathArc(Context context) {
    super(context);
  }

  public PathArc(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle,
   * boolean forceMoveTo)构建一个弧线到Path
   *
   * left,top,right,bottom：因为是弧线所以要构建一个矩形区域
   *
   * left：矩形left的x坐标
   *
   * top：矩形top的y坐标
   *
   * right：矩形right的x坐标
   *
   * bottom：矩形bottom的y坐标
   *
   * startAngle：圆弧起始角度，单位为度 （0~360）
   *
   * sweepAngle：圆弧扫过的角度，顺时针方向，单位为度 （0~360）
   *
   * forceMoveTo：false代表弧线起点与上个绘制的最后一个点连接，true代表不连接。
   */

  @Override protected void onDraw(Canvas canvas) {
    //设置绘制风格
    setViewPaint();

    Path path = new Path();
    //添加各种角度弧线
    path.arcTo(100, 100, 300, 300, 0, 90, true);

    path.arcTo(100, 400, 300, 600, 0, 180, true);

    path.arcTo(100, 700, 300, 900, 270, 180, true);

    //最后一个参数填false，看效果
    path.arcTo(100, 1000, 300, 1200, 0, 180, false);
    //绘制
    canvas.drawPath(path, paint);
  }

  public void setViewPaint() {
    //绘制风格
    paint = new Paint();
    //去锯齿
    paint.setAntiAlias(true);
    //设置绘制颜色
    paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
    //为了方便看Path的路径效果
    //设置绘制风格为空心
    paint.setStyle(Paint.Style.STROKE);
    //设置空心边框的宽度
    paint.setStrokeWidth(10);
  }
}
