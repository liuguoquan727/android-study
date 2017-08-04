package com.liuguoquan.module.tool.imageprocess;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.color.ColorMatrixFragment;
import com.liuguoquan.module.tool.imageprocess.color.ColourFragment;
import com.liuguoquan.module.tool.imageprocess.color.PixelEffectFragment;
import com.liuguoquan.module.tool.imageprocess.draw.DrawFragment;
import com.liuguoquan.module.tool.imageprocess.effect.EffectFragment;
import com.liuguoquan.module.tool.imageprocess.flipboard.FlipBoardUI;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 14:17.
 */

public class ImageProcessFragment extends AppBaseFragment {

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_fragement_image_process;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "图像处理";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.draw, R2.id.primary_color, R2.id.color_matrix, R2.id.pixel_effect, R2.id.image_effect,
      R2.id.flipboard
  }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.draw) {
      ActivityUtil.startActivity(this, DrawFragment.class);
    } else if (id == R.id.primary_color) {
      ActivityUtil.startActivity(this, ColourFragment.class);
    } else if (id == R.id.color_matrix) {
      ActivityUtil.startActivity(this, ColorMatrixFragment.class);
    } else if (id == R.id.pixel_effect) {
      ActivityUtil.startActivity(this, PixelEffectFragment.class);
    } else if (id == R.id.image_effect) {
      ActivityUtil.startActivity(this, EffectFragment.class);
    } else if (id == R.id.flipboard) {
      ActivityUtil.startActivity(this, FlipBoardUI.class);
    }
  }
}
