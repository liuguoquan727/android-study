package com.liuguoquan.module.tool.imageprocess.effect.ui;

import android.os.Bundle;
import android.view.View;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 16:40.
 */

public class ReflectTestUI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_effect_reflect;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "倒影效果";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }
}
