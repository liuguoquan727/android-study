package com.liuguoquan.commom.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.liuguoquan.commom.R;
import com.mdroid.lib.core.base.BaseApp;
import com.mdroid.lib.recyclerview.BaseRecyclerViewAdapter;
import com.mdroid.utils.AndroidUtils;
import com.mdroid.utils.Ln;
import com.mdroid.utils.ReflectUtils;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.liuguoquan.commom.base.Constants.NormalCons.SEPARATOR_POINT;

/**
 * Created by ouyangzn on 2016/11/15.<br/>
 * Description：放置一些公用的方法
 */
public class CommonUtils {

  /**
   * 给RecyclerView设置加载更多和加载更多失败时的属性
   *
   * @param mAdapter BaseRecyclerViewAdapter
   * @param recyclerView RecyclerView
   */
  public static void setRecyclerViewLoadMore(final BaseRecyclerViewAdapter mAdapter,
      RecyclerView recyclerView) {
    LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
    mAdapter.setLoadMoreView(inflater.inflate(R.layout.item_load_more, recyclerView, false));
    View loadMoreFail = inflater.inflate(R.layout.item_load_more_failure, recyclerView, false);
    loadMoreFail.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mAdapter.reloadMore();
      }
    });
    mAdapter.setLoadMoreFailureView(loadMoreFail);
  }

  public static void addDivider2TabLayout(Context context, TabLayout tabLayout) {
    try {
      LinearLayout tabStrip = ReflectUtils.getFieldValue(tabLayout, "mTabStrip");
      tabStrip.setDividerPadding(AndroidUtils.dp2px(context, 13));
      tabStrip.setDividerDrawable(context.getResources().getDrawable(R.drawable.divider));
      tabStrip.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
    } catch (NoSuchFieldException e) {
      Ln.e(e);
    } catch (IllegalAccessException e) {
      Ln.e(e);
    }
  }

  /**
   * 把标题字体加粗、颜色白色
   *
   * @param titleView TextView
   */
  public static void updateTitleText(TextView titleView) {
    titleView.setTextColor(Color.WHITE);
    titleView.getPaint().setFakeBoldText(true);
    titleView.setMaxEms(10);
  }

  /**
   * 给toolbar右边添加一个按钮
   *
   * @param toolbar toolbar
   * @param resId 图片资源id
   * @return 被添加的ImageView
   */
  public static ImageView addBtn2ToolbarRight(Toolbar toolbar, int resId) {
    Context context = toolbar.getContext();
    ImageView img = new ImageView(context, null,
        android.support.v7.appcompat.R.attr.toolbarNavigationButtonStyle);
    img.setImageResource(resId);
    img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
        Toolbar.LayoutParams.MATCH_PARENT);
    params.gravity = Gravity.END | Gravity.CENTER;
    img.setLayoutParams(params);
    toolbar.addView(img);
    return img;
  }

  /***
   * 给toolbar右边增加一个按钮
   *
   * @param toolbar Toolbar
   * @param inflater LayoutInflater
   * @param btnText 按钮上的字
   * @return 按钮TextView
   */
  public static TextView addBtn2ToolbarRight(Toolbar toolbar, LayoutInflater inflater,
      String btnText) {
    TextView btn = (TextView) inflater.inflate(R.layout.view_toolbar_right_text, toolbar, false);
    ((Toolbar.LayoutParams) btn.getLayoutParams()).gravity = Gravity.END;
    btn.setText(btnText);
    toolbar.addView(btn);
    return btn;
  }

  /***
   * 给toolbar右边增加一个按钮
   *
   * @param toolbar Toolbar
   * @param inflater LayoutInflater
   * @param btnText 按钮上的字
   * @return 按钮TextView
   */
  public static TextView addBtn2ToolbarRight(Toolbar toolbar, LayoutInflater inflater,
      @StringRes int btnText) {
    TextView btn = (TextView) inflater.inflate(R.layout.view_toolbar_right_text, toolbar, false);
    ((Toolbar.LayoutParams) btn.getLayoutParams()).gravity = Gravity.END;
    btn.setText(btnText);
    toolbar.addView(btn);
    return btn;
  }

  /**
   * 停止刷新
   *
   * @param refreshLayout SwipeRefreshLayout
   */
  public static void stopRefresh(SwipeRefreshLayout refreshLayout) {
    if (refreshLayout != null && refreshLayout.isRefreshing()) {
      refreshLayout.setRefreshing(false);
    }
  }
}
