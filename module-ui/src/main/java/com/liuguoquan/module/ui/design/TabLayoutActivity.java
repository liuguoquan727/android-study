package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

  @BindView(R2.id.toolbar) Toolbar mToolbar;
  @BindView(R2.id.tabs) TabLayout mTabLayout;
  @BindView(R2.id.viewpager) ViewPager mViewPager;
  @BindView(R2.id.activity_coordinator_layout) CoordinatorLayout mActivityCoordinatorLayout;
  private List<String> mTitles = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tablayout_layout);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);
    initData();
    initView();
  }

  private void initData() {
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
  }

  private void initView() {
    mTabLayout.setSelectedTabIndicatorHeight(mTitles.size());
    for (int i = 0; i < mTitles.size(); i++) {
      mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
    }

    final List<Fragment> fragments = new ArrayList<Fragment>();

    for (int i = 0; i < mTitles.size(); i++) {
      TabFragment fragment = new TabFragment();
      Bundle bundle = new Bundle();
      bundle.putString(TabFragment.TITLE, mTitles.get(i));
      fragment.setArguments(bundle);
      fragment.setTitle(mTitles.get(i));
      fragments.add(fragment);
    }

    //实例化适配器
    FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        return fragments.get(position);
      }

      @Override public int getCount() {
        return fragments.size();
      }

      @Override public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
      }
    };

    //给ViewPager设置适配器
    mViewPager.setAdapter(mAdapter);
    //将TabLayout和ViewPager关联起来
    mTabLayout.setupWithViewPager(mViewPager);
  }
}
