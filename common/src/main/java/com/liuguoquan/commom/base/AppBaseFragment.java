package com.liuguoquan.commom.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.liuguoquan.commom.R;
import com.liuguoquan.commom.utils.CommonUtils;
import com.mdroid.app.TranslucentStatusCompat;
import com.mdroid.lib.core.base.BaseFragment;
import com.mdroid.lib.core.base.BaseView;
import com.mdroid.lib.core.eventbus.EventBusEvent;
import com.mdroid.lib.core.utils.UIUtil;

/**
 * Description：
 */
public abstract class AppBaseFragment<V extends AppBaseView, T extends AppBaseFragmentPresenter<V>>
    extends BaseFragment<V, T> implements BaseView<T>, EventBusEvent.INotify {
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

  protected void requestBaseInit(String title) {
    TranslucentStatusCompat.requestTranslucentStatus(getActivity());
    getToolBarShadow().setVisibility(View.GONE);
    Toolbar toolBar = getToolBar();
    getStatusBar().setBackgroundResource(R.color.main_color_normal);
    toolBar.setBackgroundResource(R.color.main_color_normal);
    TextView tvTitle = UIUtil.setCenterTitle(toolBar, title);
    CommonUtils.updateTitleText(tvTitle);
    toolBar.setNavigationIcon(R.drawable.ic_back);
    toolBar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        getActivity().onBackPressed();
      }
    });
  }

  protected void requestBaseInitNoBack(String title) {
    TranslucentStatusCompat.requestTranslucentStatus(getActivity());
    getToolBarShadow().setVisibility(View.GONE);
    Toolbar toolBar = getToolBar();
    getStatusBar().setBackgroundResource(R.color.main_color_normal);
    toolBar.setBackgroundResource(R.color.main_color_normal);
    TextView tvTitle = UIUtil.setCenterTitle(toolBar, title);
    CommonUtils.updateTitleText(tvTitle);
  }
}
