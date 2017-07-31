package com.liuguoquan.commom.base;

import com.mdroid.lib.core.base.BaseActivity;
import com.mdroid.lib.core.base.BaseView;
import com.mdroid.lib.core.eventbus.EventBusEvent;

/**
 * Description：
 */
public abstract class AppBaseActivity<V extends AppBaseView, T extends AppBaseActivityPresenter<V>>
    extends BaseActivity<V, T> implements BaseView<T>, EventBusEvent.INotify {
  /**
   * 数据等加载指示器，默认空实现
   *
   * @param isActive 是否正在处理
   */
  @Override public void setLoadingIndicator(boolean isActive) {
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  protected void showProcessDialog() {
  }

  protected void dismissProcessDialog() {
  }

  @Override public void onNotify(EventBusEvent event) {

  }
}
