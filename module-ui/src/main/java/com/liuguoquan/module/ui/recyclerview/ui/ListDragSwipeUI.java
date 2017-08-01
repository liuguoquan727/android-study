package com.liuguoquan.module.ui.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import butterknife.BindView;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.recyclerview.adapter.ListRecyclerViewAdapter;
import com.liuguoquan.module.ui.recyclerview.adapter.OnStartDragListener;
import com.liuguoquan.module.ui.recyclerview.adapter.SimpleItemTouchHelperCallback;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 20:42.
 */

public class ListDragSwipeUI extends AppBaseFragment {

  @BindView(R2.id.recyclerview) RecyclerView mRecyclerview;
  private List<String> mTitles = new ArrayList<>();
  private ItemTouchHelper mHelper;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_recyclerview_list_drag;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "List列表移动";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    initData();
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
    mTitles.add("美女");
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

    LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mRecyclerview.setLayoutManager(mLayoutManager);
    mRecyclerview.setItemAnimator(new DefaultItemAnimator());
    ListRecyclerViewAdapter mAdapter = new ListRecyclerViewAdapter(getActivity(), mTitles);
    mAdapter.setOnStartDragListener(new OnStartDragListener() {
      @Override public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mHelper.startDrag(viewHolder);
      }
    });
    mRecyclerview.setAdapter(mAdapter);
    SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback(mAdapter);
    mHelper = new ItemTouchHelper(callback);
    mHelper.attachToRecyclerView(mRecyclerview);
  }
}
