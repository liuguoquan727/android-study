package com.liuguoquan.module.ui.v4.ui;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * [Android ViewDragHelper完全解析 自定义ViewGroup神器](http://blog.csdn.net/lmj623565791/article/details/46858663)
 * Created by liuguoquan on 2016/10/25.
 */

public class VDHLayout extends LinearLayout {

  private ViewDragHelper mHelper;

  //演示普通移动
  private View mDragView;
  //拖动后自动返回原位置
  private View mAutoBackView;
  //边界检测
  private View mEdgeTrackerView;

  private Point mAutoBackOriginPos = new Point();

  public VDHLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
    initView(context);
  }

  public VDHLayout(Context context) {
    this(context, null);
  }

  public VDHLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void initView(Context context) {
    mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

      //tryCaptureView如何返回ture则表示可以捕获该view
      @Override public boolean tryCaptureView(View child, int pointerId) {

        //mEdgeTrackerView禁止直接移动
        return mDragView == child || mAutoBackView == child;
      }

      @Override public int clampViewPositionHorizontal(View child, int left, int dx) {

        return left;
      }

      @Override public int clampViewPositionVertical(View child, int top, int dy) {
        return top;
      }

      //手指释放的时候回调
      @Override public void onViewReleased(View releasedChild, float xvel, float yvel) {

        //mAutoBackView手指释放时可以自动回去
        if (releasedChild == mAutoBackView) {
          //调用settleCapturedViewAt回到初始的位置
          mHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
          invalidate();
        }
      }

      //在边界拖动时回调
      @Override public void onEdgeDragStarted(int edgeFlags, int pointerId) {
        mHelper.captureChildView(mEdgeTrackerView, pointerId);
      }

      //用Button测试，或者给TextView添加了clickable = true 时重写
      @Override public int getViewHorizontalDragRange(View child) {
        return getMeasuredWidth() - child.getMeasuredWidth();
      }

      //用Button测试，或者给TextView添加了clickable = true 时重写
      @Override public int getViewVerticalDragRange(View child) {
        return getMeasuredWidth() - child.getMeasuredWidth();
      }
    });
    mHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    return mHelper.shouldInterceptTouchEvent(ev);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    mHelper.processTouchEvent(event);
    return true;
  }

  @Override public void computeScroll() {

    if (mHelper.continueSettling(true)) {
      invalidate();
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    mAutoBackOriginPos.x = mAutoBackView.getLeft();
    mAutoBackOriginPos.y = mAutoBackView.getTop();
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    mDragView = getChildAt(0);
    mAutoBackView = getChildAt(1);
    mEdgeTrackerView = getChildAt(2);
  }
}
