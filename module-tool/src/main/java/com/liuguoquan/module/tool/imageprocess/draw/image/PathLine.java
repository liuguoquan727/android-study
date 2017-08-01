package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuguoquan on 16/9/19.
 */
public class PathLine extends View {
  private Paint paint;

  public PathLine(Context context) {
    super(context);
  }

  public PathLine(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Path类的主要方法如下：

   moveTo：设置路径起始点
   lineTo：添加直线到路径
   arcTo：添加弧线到路径
   rMoveTo：设置路径起始点，参数相对于当前绘制点
   rLineTo：添加直线到路径，参数相对于当前绘制点
   rArcTo：添加弧线到路径，参数相对于当前绘制点
   close：闭合路径
   addArc：添加一个圆弧到路径
   addCircle：添加一个圆到路径
   addOval：添加一个椭圆到路径
   addRect：添加一个矩形到路径
   reset：重置路径
   offset：对路径进行偏移
   op：两个路径组合操作
   */

  /**
   * moveTo(float x, float y)：移动Path的绘制起点
   *
   * x：View的x坐标
   *
   * y：View的y坐标
   *
   * lineTo(float x, float y) ：构建从绘制的上一个点到xy的一条直线
   *
   * x：View的x坐标
   *
   * y：View的y坐标
   *
   * close()：闭合路径，对Path终点与起点进行直线连接
   */

  @Override protected void onDraw(Canvas canvas) {
    //设置绘制风格
    setViewPaint();
    //构建Path路径
    Path path = new Path();
    //设置path的起点位置
    path.moveTo(200, 200);
    //path路径连接至某点
    path.lineTo(100, 400);
    path.lineTo(300, 400);
    //path路径的最后一个点与起点连接,如果不写这句三角形就会变成LEXUS的车标了：）
    path.close();
    //绘制三角形
    canvas.drawPath(path, paint);

    //如果这里不设置moveTo那么path将沿着上面那个三角形路径最后一点进行继续绘制
    path.moveTo(100, 800);
    path.lineTo(200, 500);
    path.lineTo(300, 800);
    path.lineTo(400, 500);
    path.lineTo(500, 800);
    //绘制M形
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
