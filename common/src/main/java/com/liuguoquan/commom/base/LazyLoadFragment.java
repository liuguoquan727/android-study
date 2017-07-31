package com.liuguoquan.commom.base;

import android.support.annotation.CallSuper;
import android.view.View;
import com.mdroid.lib.core.base.Status;

/**
 * Description：fragment懒加载时继承的基类
 */
public abstract class LazyLoadFragment<V extends AppBaseView, T extends AppBaseFragmentPresenter<V>>
    extends AppBaseFragment<V, T> {

  private boolean mIsVisible;
  private boolean mIsViewLoaded;

  @CallSuper @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    mIsVisible = isVisibleToUser;
    if (mIsVisible && !mIsViewLoaded && mContentView != null) {
      lazyInitView(mContentView);
      mIsViewLoaded = true;
    }
  }

  @Override protected void initView(View parent) {
    if (!mIsViewLoaded && mIsVisible && mContentView != null) {
      lazyInitView(parent);
      mIsViewLoaded = true;
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mIsViewLoaded = false;
  }

  protected abstract void lazyInitView(View parent);

  @Override protected Status getCurrentStatus() {
    return Status.STATUS_LOADING;
  }
}
