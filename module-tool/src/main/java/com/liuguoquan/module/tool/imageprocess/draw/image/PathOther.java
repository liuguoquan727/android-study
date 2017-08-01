package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Path封装好的效果
 * Created by liuguoquan on 16/9/20.
 */
public class PathOther extends View {
  private Paint paint;

  public PathOther(Context context) {
    super(context);
  }

  public PathOther(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Path同样也给我们封装好了一些路径效果，例如圆形，矩形等等，跟canvas.drawXX比较类似，这里就简单介绍下
   *
   * addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
   * 添加弧线到路径
   *
   * addCircle(float x, float y, float radius, Path.Direction dir) 添加圆到路径
   *
   * addOval(float left, float top, float right, float bottom, Path.Direction dir) 添加椭圆到路径
   *
   * addRect(float left, float top, float right, float bottom, Path.Direction dir) 添加矩形到路径
   *
   * 上面的Path.Direction dir参数用来指定绘制时是顺时针还是逆时针，Path.Direction.CCW和Path.Direction.CW分别代表逆时针和顺时针，
   * 顺时针和逆时针主要的作用在于不同的时针方向也就决定了不同的路径起始点和终点，其他的参数跟canvas.drawXX是一样的
   */

  @Override protected void onDraw(Canvas canvas) {
    //设置绘制风格
    setViewPaint();
    //构建path
    Path path = new Path();
    //添加弧形到path
    path.addArc(100, 100, 300, 300, 0, 270);
    //添加圆形到path
    path.addCircle(200, 500, 100, Path.Direction.CCW);
    //添加矩形到path
    path.addRect(100, 700, 300, 800, Path.Direction.CW);
    //添加椭圆到path
    path.addOval(100, 900, 300, 1000, Path.Direction.CCW);
    //绘制path
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
