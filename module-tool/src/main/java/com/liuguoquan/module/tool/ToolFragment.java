package com.liuguoquan.module.tool;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.imageprocess.ImageProcessFragment;
import com.liuguoquan.module.tool.system.SystemApiFragment;
import com.liuguoquan.module.tool.webview.WebViewUI;
import com.liuguoquan.router.RouterConstant;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 17:54.
 */

@Route(path = RouterConstant.MODULE_TOOL_MAIN) public class ToolFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_main;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "工具";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInitNoBack(getPageTitle());
  }

  @OnClick({ R2.id.image_process, R2.id.system_api, R2.id.webview }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.image_process) {
      ActivityUtil.startActivity(this, ImageProcessFragment.class);
    } else if (id == R.id.system_api) {
      ActivityUtil.startActivity(this, SystemApiFragment.class);
    } else if (id == R.id.webview) {
      ActivityUtil.startActivity(this, WebViewUI.class);
    }
  }
}
