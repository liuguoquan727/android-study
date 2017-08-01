package com.liuguoquan.module.ui.design.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * com.michael.materialdesign.ui.design.behavior.FooterBehavior
 * [](http://blog.csdn.net/tiankong1206/article/details/48394393)
 * Created by liuguoquan on 2016/10/28.
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

  private int sinceDirectionChange;

  public FooterBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  //判断滑动的方向 垂直时生效
  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
  }

  //根据滑动的距离显示的隐藏 View
  @Override public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
      View target, int dx, int dy, int[] consumed) {
    if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
      child.animate().cancel();
      sinceDirectionChange = 0;
    }
    sinceDirectionChange += dy;

    if (sinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE) {
      hide(child);
    } else if (sinceDirectionChange < 0 && child.getVisibility() == View.GONE) {
      show(child);
    }

    //if (dy > 0 && child.getVisibility() == View.VISIBLE) {
    //  hide(child);
    //  return;
    //}
    //
    //if (dy < 0 && child.getVisibility() == View.GONE) {
    //  show(child);
    //  return;
    //}

  }

  private void show(final View child) {
    ViewPropertyAnimator animator = child.animate();
    animator.translationY(0);
    animator.setInterpolator(new FastOutSlowInInterpolator());
    animator.setDuration(200);
    animator.setListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animator) {

      }

      @Override public void onAnimationEnd(Animator animator) {
        child.setVisibility(View.VISIBLE);
      }

      @Override public void onAnimationCancel(Animator animator) {
        hide(child);
      }

      @Override public void onAnimationRepeat(Animator animator) {

      }
    });
    animator.start();
  }

  private void hide(final View child) {
    ViewPropertyAnimator animator = child.animate();
    animator.translationY(child.getHeight());
    animator.setInterpolator(new FastOutSlowInInterpolator());
    animator.setDuration(200);
    animator.setListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animator) {

      }

      @Override public void onAnimationEnd(Animator animator) {
        child.setVisibility(View.GONE);
      }

      @Override public void onAnimationCancel(Animator animator) {
        show(child);
      }

      @Override public void onAnimationRepeat(Animator animator) {

      }
    });
    animator.start();
  }
}
