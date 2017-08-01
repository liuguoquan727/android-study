package com.liuguoquan.module.tool.imageprocess.effect;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.effect.ui.FlagBitmapMeshTest;
import com.liuguoquan.module.tool.imageprocess.effect.ui.MatrixTestUI;
import com.liuguoquan.module.tool.imageprocess.effect.ui.PathEffectUI;
import com.liuguoquan.module.tool.imageprocess.effect.ui.ReflectTestUI;
import com.liuguoquan.module.tool.imageprocess.effect.ui.RoundRectShaderUI;
import com.liuguoquan.module.tool.imageprocess.effect.ui.RoundRectXfermodeUI;
import com.liuguoquan.module.tool.imageprocess.effect.ui.XfermodeTestUi;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.utils.ActivityUtil;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 16:30.
 */

public class EffectFragment extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_effect;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "图形特效";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({
      R2.id.matrix, R2.id.xfermode, R2.id.round_image_shader, R2.id.round_image_xfermode,
      R2.id.reflect_image, R2.id.flag_wave, R2.id.path_effect
  }) public void onClick(View v) {

    int i = v.getId();
    if (i == R.id.matrix) {
      ActivityUtil.startActivity(this, MatrixTestUI.class);
    } else if (i == R.id.xfermode) {
      ActivityUtil.startActivity(this, XfermodeTestUi.class);
    } else if (i == R.id.round_image_shader) {
      ActivityUtil.startActivity(this, RoundRectShaderUI.class);
    } else if (i == R.id.round_image_xfermode) {
      ActivityUtil.startActivity(this, RoundRectXfermodeUI.class);
    } else if (i == R.id.reflect_image) {
      ActivityUtil.startActivity(this, ReflectTestUI.class);
    } else if (i == R.id.flag_wave) {
      ActivityUtil.startActivity(this, FlagBitmapMeshTest.class);
    } else if (i == R.id.path_effect) {
      ActivityUtil.startActivity(this, PathEffectUI.class);
    }
  }
}
