package com.liuguoquan.module.library;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.library.rxjava.RxJavaFragment;
import com.liuguoquan.router.RouterConstant;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 17:18.
 */

@Route(path = RouterConstant.MODULE_LIBRARY_MAIN) public class OpenSourceFragment
    extends AppBaseFragment {

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_library_fragment_ui;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "开源库";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInitNoBack(getPageTitle());
  }

  @OnClick({ R2.id.rxjava2 }) public void onClick(View view) {
    int id = view.getId();
    if (id == R.id.rxjava2) {
      ActivityUtil.startActivity(this, RxJavaFragment.class);
      return;
    }
  }
}
