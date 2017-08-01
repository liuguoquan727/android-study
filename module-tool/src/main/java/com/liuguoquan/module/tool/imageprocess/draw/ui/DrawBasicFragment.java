package com.liuguoquan.module.tool.imageprocess.draw.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.liuguoquan.module.tool.imageprocess.draw.image.BitmapDemo;
import com.liuguoquan.module.tool.imageprocess.draw.image.Circle;
import com.liuguoquan.module.tool.imageprocess.draw.image.Line;
import com.liuguoquan.module.tool.imageprocess.draw.image.Oval;
import com.liuguoquan.module.tool.imageprocess.draw.image.PathArc;
import com.liuguoquan.module.tool.imageprocess.draw.image.PathCompose;
import com.liuguoquan.module.tool.imageprocess.draw.image.PathLine;
import com.liuguoquan.module.tool.imageprocess.draw.image.PathOther;
import com.liuguoquan.module.tool.imageprocess.draw.image.Point;
import com.liuguoquan.module.tool.imageprocess.draw.image.Rectangle;
import com.liuguoquan.module.tool.imageprocess.draw.image.RoundRect;
import com.liuguoquan.module.tool.imageprocess.draw.image.Text;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 14:47.
 */

public class DrawBasicFragment extends AppBaseFragment {

  @BindView(R2.id.content_container) FrameLayout mContainer;

  public static final String TYPE = "type";
  public static final int TYPE_ARC = 0;
  public static final int TYPE_BITMAP = 1;
  public static final int TYPE_CIRCLE = 2;
  public static final int TYPE_LINE = 3;
  public static final int TYPE_OVAL = 4;
  public static final int TYPE_PATH = 5;
  public static final int TYPE_PATH_LINE = 51;
  public static final int TYPE_PATH_ARC = 52;
  public static final int TYPE_PATH_EFFECT = 53;
  public static final int TYPE_PATH_COMPOSE = 54;
  public static final int TYPE_POINT = 6;
  public static final int TYPE_RECT = 7;
  public static final int TYPE_ROUND_RECT = 71;
  public static final int TYPE_TEXT = 8;
  int mType = 1;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_fragment_draw_basic;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "绘制基本图形";
  }

  @Override protected void initData(Bundle savedInstanceState) {
    mType = getArguments().getInt(TYPE, 1);
  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    init();
  }

  private void init() {

    View view = null;

    switch (mType) {

      case TYPE_PATH_ARC:
        view = new PathArc(getActivity());
        break;

      case TYPE_RECT:
        view = new Rectangle(getActivity());
        break;

      case TYPE_ROUND_RECT:
        view = new RoundRect(getActivity());
        break;

      case TYPE_PATH_LINE:
        view = new PathLine(getActivity());
        break;

      case TYPE_PATH_EFFECT:
        view = new PathOther(getActivity());
        break;

      case TYPE_PATH_COMPOSE:
        view = new PathCompose(getActivity());
        break;

      case TYPE_BITMAP:

        view = new BitmapDemo(getActivity());
        break;

      case TYPE_CIRCLE:
        view = new Circle(getActivity());
        break;

      case TYPE_LINE:
        view = new Line(getActivity());
        break;

      case TYPE_OVAL:
        view = new Oval(getActivity());
        break;

      case TYPE_POINT:

        view = new Point(getActivity());

        break;

      case TYPE_TEXT:

        view = new Text(getActivity());

        break;
    }
    mContainer.addView(view);
  }
}
