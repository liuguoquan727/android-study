package com.liuguoquan.module.ui;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.date.DateUI;
import com.liuguoquan.module.ui.design.DesignUI;
import com.liuguoquan.module.ui.dialog.DialogUI;
import com.liuguoquan.module.ui.notification.NotificationUI;
import com.liuguoquan.module.ui.recyclerview.RecyclerViewUI;
import com.liuguoquan.module.ui.seven.Android7UI;
import com.liuguoquan.module.ui.v4.V4UI;
import com.liuguoquan.router.RouterConstant;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.R;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.DialogActivity;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

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
    return R.layout.module_ui_main;
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

  @OnClick({
      R2.id.androidv4, R2.id.design, R2.id.notification, R2.id.time_date, R2.id.recyclerview,
      R2.id.android24, R2.id.dialog
  }) public void onClick(View v) {
    int i = v.getId();
    if (i == R.id.androidv4) {
      ActivityUtil.startActivity(this, V4UI.class);
    } else if (i == R.id.design) {
      ActivityUtil.startActivity(this, DesignUI.class);
    } else if (i == R.id.notification) {
      ActivityUtil.startActivity(this, NotificationUI.class);
    } else if (i == R.id.time_date) {
      ActivityUtil.startActivity(this, DateUI.class);
    } else if (i == R.id.recyclerview) {
      ActivityUtil.startActivity(this, RecyclerViewUI.class);
    } else if (i == R.id.android24) {
      ActivityUtil.startActivity(this, Android7UI.class);
    } else if (i == R.id.dialog) {
      ActivityUtil.startActivity(this, DialogUI.class);
    }
  }
}
