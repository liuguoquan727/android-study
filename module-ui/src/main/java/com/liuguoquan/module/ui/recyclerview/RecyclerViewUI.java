package com.liuguoquan.module.ui.recyclerview;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.recyclerview.ui.DiffUtilUI;
import com.liuguoquan.module.ui.recyclerview.ui.GridDragSwipeUI;
import com.liuguoquan.module.ui.recyclerview.ui.ListDragSwipeUI;
import com.liuguoquan.module.ui.recyclerview.ui.RecyclerViewSnapUI;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 20:30.
 */

public class RecyclerViewUI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_recyclerview;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "RecyclerView";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.diffutil, R2.id.item_touch_up, R2.id.item_touch_slide, R2.id.snap, R2.id.card,
      R2.id.tantan
  }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.diffutil:
        ActivityUtil.startActivity(this, DiffUtilUI.class);
        break;

      case R.id.item_touch_up:
        ActivityUtil.startActivity(this, ListDragSwipeUI.class);
        break;

      case R.id.item_touch_slide:
        ActivityUtil.startActivity(this, GridDragSwipeUI.class);
        break;

      case R.id.snap:
        ActivityUtil.startActivity(this, RecyclerViewSnapUI.class);
        break;
      case R.id.card:
        break;

      case R.id.tantan:
        break;
    }
  }
}
