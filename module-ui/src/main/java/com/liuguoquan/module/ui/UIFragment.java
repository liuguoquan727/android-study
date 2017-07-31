package com.liuguoquan.module.ui;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.router.RouterConstant;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 15:59.
 */

@Route(path = RouterConstant.MODULE_UI_MAIN) public class UIFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_fragment_ui;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "UI";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInitNoBack(getPageTitle());
  }
}
