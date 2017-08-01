package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.design.adapter.CoordinatorAdapter;
import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {

  @BindView(R2.id.toolbar) Toolbar mToolbar;
  @BindView(R2.id.tabs) TabLayout mTabLayout;
  @BindView(R2.id.list) RecyclerView mList;
  private List<String> mTitles = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coordinator_layout);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);

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
    mTitles.add("美女");

    mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mList.setItemAnimator(new DefaultItemAnimator());
    CoordinatorAdapter adapter = new CoordinatorAdapter(this, mTitles);
    mList.setAdapter(adapter);

    mTabLayout.setSelectedTabIndicatorHeight(mTitles.size());
    for (int i = 0; i < mTitles.size(); i++) {
      mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
    }
  }
}
