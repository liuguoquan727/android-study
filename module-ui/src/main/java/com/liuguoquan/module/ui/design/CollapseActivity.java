package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.design.adapter.CoordinatorAdapter;
import java.util.ArrayList;
import java.util.List;

public class CollapseActivity extends AppCompatActivity {

  @BindView(R2.id.RecyclerView) RecyclerView mList;
  @BindView(R2.id.tabs) TabLayout mTabLayout;
  @BindView(R2.id.Expand) ImageView mExpand;
  @BindView(R2.id.LayoutMore) LinearLayout mLayoutMore;
  @BindView(R2.id.Root) RelativeLayout mRoot;
  private List<String> mTitles = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_page_statistic_monthly_new);
    ButterKnife.bind(this);

    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");
    mTitles.add("体育");
    mTitles.add("军事");
    mTitles.add("科技");
    mTitles.add("教育");
    mTitles.add("历史");
    mTitles.add("文化");
    mTitles.add("深圳");
    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");
    mTitles.add("体育");
    mTitles.add("军事");
    mTitles.add("科技");
    mTitles.add("教育");
    mTitles.add("历史");
    mTitles.add("文化");
    mTitles.add("深圳");
    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");

    mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mList.setItemAnimator(new DefaultItemAnimator());
    CoordinatorAdapter adapter = new CoordinatorAdapter(this, mTitles);
    mList.setAdapter(adapter);

    //mTabLayout.setSelectedTabIndicatorHeight(5); //设置Indicator高度
    for (int i = 0; i < 3; i++) {
      mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
    }
  }

  @OnClick(R2.id.Root) public void onClick() {
    if (mLayoutMore.getVisibility() == View.VISIBLE) {
      mLayoutMore.setVisibility(View.GONE);
      mExpand.setImageResource(R.drawable.ic_page_statistic_monthly_expand);
    } else {
      mLayoutMore.setVisibility(View.VISIBLE);
      mExpand.setImageResource(R.drawable.ic_page_statistic_monthly_collapse);
    }
  }
}
