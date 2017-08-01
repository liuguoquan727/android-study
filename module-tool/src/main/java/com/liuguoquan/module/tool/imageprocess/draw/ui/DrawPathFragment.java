package com.liuguoquan.module.tool.imageprocess.draw.ui;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 15:20.
 */

public class DrawPathFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_fragment_draw_path;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "绘制路径";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.path_line, R2.id.path_arc, R2.id.path_effect, R2.id.path_compose })
  public void onClick(View v) {
    Bundle bundle = new Bundle();
    int id = v.getId();
    if (id == R.id.path_line) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_PATH_LINE);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.path_arc) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_PATH_ARC);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.path_effect) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_PATH_EFFECT);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.path_compose) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_PATH_COMPOSE);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    }
  }
}
