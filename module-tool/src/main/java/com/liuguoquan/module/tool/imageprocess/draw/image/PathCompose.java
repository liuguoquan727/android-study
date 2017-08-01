package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Path组合
 * Created by liuguoquan on 16/9/20.
 */
public class PathCompose extends View {
  private Paint paint;

  public PathCompose(Context context) {
    super(context);
  }

  public PathCompose(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * op(Path path, Path.Op op) 方法，用于将两个Path路径进行组合之后的效果设置，靠op方法可以快速组合生成一些复杂的图形效果，例如月牙形
   *
   * Path.Op有如下几种参数
   *
   * Path.Op.DIFFERENCE：减去Path2后Path1剩下的部分
   *
   * Path.Op.INTERSECT：保留Path1与Path2共同的部分
   *
   * Path.Op.REVERSE_DIFFERENCE：减去Path1后Path2剩下的部分
   *
   * Path.Op.UNION：保留全部Path1和Path2
   *
   * Path.Op.XOR：包含Path1与Path2但不包括两者相交的部分
   */

  @Override protected void onDraw(Canvas canvas) {
    //设置绘制风格
    setViewPaint();
    //设置填充风格，方便观察效果
    paint.setStyle(Paint.Style.FILL);
    //构建path
    Path path = new Path();
    path.addCircle(150, 150, 100, Path.Direction.CW);
    Path path2 = new Path();
    path2.addCircle(300, 150, 100, Path.Direction.CW);
    path.op(path2, Path.Op.UNION);
    //Path.Op.UNION
    canvas.drawPath(path, paint);

    //清除路径
    path.reset();
    path2.reset();
    path.addCircle(150, 400, 100, Path.Direction.CW);
    path2.addCircle(300, 400, 100, Path.Direction.CW);
    path.op(path2, Path.Op.REVERSE_DIFFERENCE);
    //Path.Op.REVERSE_DIFFERENCE
    canvas.drawPath(path, paint);

    //清除路径
    path.reset();
    path2.reset();
    path.addCircle(150, 650, 100, Path.Direction.CW);
    path2.addCircle(300, 650, 100, Path.Direction.CW);
    path.op(path2, Path.Op.INTERSECT);
    //Path.Op.INTERSECT
    canvas.drawPath(path, paint);

    //清除路径
    path.reset();
    path2.reset();
    path.addCircle(150, 900, 100, Path.Direction.CW);
    path2.addCircle(300, 900, 100, Path.Direction.CW);
    path.op(path2, Path.Op.DIFFERENCE);
    //Path.Op.DIFFERENCE
    canvas.drawPath(path, paint);

    //清除路径
    path.reset();
    path2.reset();
    path.addCircle(150, 1150, 100, Path.Direction.CW);
    path2.addCircle(300, 1150, 100, Path.Direction.CW);
    path.op(path2, Path.Op.XOR);
    //Path.Op.XOR
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
