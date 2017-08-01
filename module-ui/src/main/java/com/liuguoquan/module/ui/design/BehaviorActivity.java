package com.liuguoquan.module.ui.design;

import android.os.Bundle;
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

/**
 * [](http://blog.csdn.net/tiankong1206/article/details/48394393)
 */
public class BehaviorActivity extends AppCompatActivity {

  @BindView(R2.id.toolbar) Toolbar mToolbar;
  @BindView(R2.id.recyclerview) RecyclerView mList;

  private List<String> mTitles = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_behavior);
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
    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");
    mTitles.add("体育");
    mTitles.add("军事");
    mTitles.add("科技");
    mTitles.add("教育");
    mTitles.add("历史");
    mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mList.setItemAnimator(new DefaultItemAnimator());
    CoordinatorAdapter adapter = new CoordinatorAdapter(this, mTitles);
    mList.setAdapter(adapter);
  }
}
