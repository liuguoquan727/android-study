package com.liuguoquan.module.tool.imageprocess.draw;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.draw.ui.DrawArcFragment;
import com.liuguoquan.module.tool.imageprocess.draw.ui.DrawBasicFragment;
import com.liuguoquan.module.tool.imageprocess.draw.ui.DrawPathFragment;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 14:25.
 */

public class DrawFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_draw;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "绘图";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.draw_arc, R2.id.draw_bitmap, R2.id.draw_path, R2.id.draw_rect, R2.id.draw_point,
      R2.id.draw_line, R2.id.draw_circle, R2.id.draw_oval, R2.id.draw_text
  }) public void onClick(View v) {
    Bundle bundle = new Bundle();
    int id = v.getId();
    if (id == R.id.draw_arc) {
      ActivityUtil.startActivity(this, DrawArcFragment.class);
    } else if (id == R.id.draw_bitmap) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_BITMAP);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.draw_path) {
      ActivityUtil.startActivity(this, DrawPathFragment.class);
    } else if (id == R.id.draw_rect) {
      ActivityUtil.startActivity(this, DrawRectFragment.class);
    } else if (id == R.id.draw_point) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_POINT);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.draw_line) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_LINE);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.draw_circle) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_CIRCLE);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.draw_oval) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_OVAL);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    } else if (id == R.id.draw_text) {
      bundle.putInt(DrawBasicFragment.TYPE, DrawBasicFragment.TYPE_TEXT);
      ActivityUtil.startActivity(this, DrawBasicFragment.class, bundle);
    }
  }
}
