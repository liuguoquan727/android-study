package com.liuguoquan.module.tool.imageprocess.flipboard;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description: FlipBoard翻页效果
 *
 * Created by liuguoquan on 2017/8/4 16:19.
 */

public class FlipBoardUI extends AppBaseFragment {

  @BindView(R2.id.view1) FrameLayout mView1;
  @BindView(R2.id.view2) FrameLayout mView2;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_flipboard;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "Flipboard翻页效果";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());

    Sample14FlipboardView sample14FlipboardView = new Sample14FlipboardView(getActivity());
    mView1.addView(sample14FlipboardView);
    Practice14FlipboardView practice14FlipboardView = new Practice14FlipboardView(getActivity());
    mView2.addView(practice14FlipboardView);
  }
}
