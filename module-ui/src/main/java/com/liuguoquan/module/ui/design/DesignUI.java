package com.liuguoquan.module.ui.design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:21.
 */

public class DesignUI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_design;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "design包";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.collapsingToolbarLayout, R2.id.collapsingToolbarLayout_view, R2.id.coordinatorLayout,
      R2.id.tablayout, R2.id.palette, R2.id.textInputLayout, R2.id.toolbar, R2.id.fab,
      R2.id.snackbar, R2.id.behavior, R2.id.bottom_sheet_behavior
  }) public void onClick(View v) {
    switch (v.getId()) {

      case R.id.collapsingToolbarLayout:
        start(ScrollingActivity.class);
        break;

      case R.id.collapsingToolbarLayout_view:
        start(CollapseActivity.class);
        break;

      case R.id.coordinatorLayout:
        start(CoordinatorLayoutActivity.class);
        break;

      case R.id.behavior:
        start(BehaviorActivity.class);
        break;

      case R.id.tablayout:
        start(TabLayoutActivity.class);
        break;

      case R.id.palette:
        start(PaletteActivity.class);
        break;

      case R.id.textInputLayout:
        start(TextInputActivity.class);
        break;

      case R.id.toolbar:
        start(ToolbarActivity.class);
        break;

      case R.id.fab:
        start(FabActivity.class);
        break;

      case R.id.snackbar:
        start(FabActivity.class);
        break;

      case R.id.bottom_sheet_behavior:
        start(BottomSheetBehaviorActivity.class);
        break;
    }
  }

  private void start(Class<?> clz) {
    Intent intent = new Intent(getActivity(), clz);
    ActivityUtil.startActivity(DesignUI.this, intent);
  }
}
