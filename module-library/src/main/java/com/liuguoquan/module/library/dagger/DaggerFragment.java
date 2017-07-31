package com.liuguoquan.module.library.dagger;

import android.os.Bundle;
import android.view.View;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.library.R;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import javax.inject.Inject;

/**
 * Dagger2基本使用
 *
 * 1. {@link Watch}
 * 2. {@link WatchModule}
 * 3. {@link DFragmentComponent}
 * 4. {@link DaggerFragment DaggerDFragmentComponent.create().inject(this)}
 *
 * Created by liuguoquan on 2017/7/27.
 */

public class DaggerFragment extends AppBaseFragment {

  //标记需要注入的属性
  @Inject Watch watch;

  @Override protected Status getCurrentStatus() {
    return Status.STATUS_NORMAL;
  }

  @Override protected int getContentView() {
    return R.layout.module_library_fragment_dagger;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "Dagger";
  }

  @Override protected void initData(Bundle savedInstanceState) {
    //注册
    DaggerDFragmentComponent.create().inject(this);
  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    watch.work();
  }
}
