package com.liuguoquan.module.ui.v4;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.v4.ui.ViewDragHelperUI;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:09.
 */

public class V4UI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_v4;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "V4包";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.view_drag_helper }) public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.view_drag_helper) {
      ActivityUtil.startActivity(this, ViewDragHelperUI.class);
    }
  }
}
