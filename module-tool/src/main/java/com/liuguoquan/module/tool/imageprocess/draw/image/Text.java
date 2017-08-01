package com.liuguoquan.module.tool.imageprocess.draw.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuguoquan on 16/9/19.
 */
public class Text extends View {

  public Text(Context context) {
    super(context);
  }

  public Text(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * drawText(String text, float x, float y, Paint paint)
   *
   * 参数列表：
   *
   * text>文本
   *
   * x>文本origin的x坐标
   *
   * y>文本baseline的y坐标
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
    paint.setTextSize(100);
    //绘制文本
    canvas.drawText("jEh", 80, 150, paint);
  }
}
