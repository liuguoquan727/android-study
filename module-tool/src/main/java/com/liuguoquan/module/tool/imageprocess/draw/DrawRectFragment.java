package com.liuguoquan.module.tool.imageprocess.draw;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.draw.ui.DrawBasicFragment;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 15:41.
 */

public class DrawRectFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_fragment_draw_rect;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "绘制矩形";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.rect, R2.id.round_rect }) public void onClick(View v) {
    Bundle bundle = new Bundle();
    int id = v.getId();
    if (id == R.id.rect) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_RECT);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.round_rect) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_ROUND_RECT);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    }
  }
}
