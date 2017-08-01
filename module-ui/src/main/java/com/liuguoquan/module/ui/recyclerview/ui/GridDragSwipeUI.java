package com.liuguoquan.module.ui.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import butterknife.BindView;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.recyclerview.adapter.GridRecyclerViewAdapter;
import com.liuguoquan.module.ui.recyclerview.adapter.SimpleItemTouchHelperCallback;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 20:43.
 */

public class GridDragSwipeUI extends AppBaseFragment {

  @BindView(R2.id.recyclerview) RecyclerView mRecyclerview;

  private List<String> mTitles = new ArrayList<>();
  private ItemTouchHelper mHelper;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_recylcerview_grid_drag;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "GridView列表移动";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {

    requestBaseInit(getPageTitle());
    for (int i = 0; i < 21; i++) {
      mTitles.add("深圳" + i);
    }
    initView();
  }

  private void initView() {
    GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
    mRecyclerview.setLayoutManager(mLayoutManager);
    mRecyclerview.setHasFixedSize(true);
    mRecyclerview.setItemAnimator(new DefaultItemAnimator());
    GridRecyclerViewAdapter mAdapter = new GridRecyclerViewAdapter(getActivity(), mTitles);
    mRecyclerview.setAdapter(mAdapter);
    SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback(mAdapter);
    mHelper = new ItemTouchHelper(callback);
    mHelper.attachToRecyclerView(mRecyclerview);
  }
}
