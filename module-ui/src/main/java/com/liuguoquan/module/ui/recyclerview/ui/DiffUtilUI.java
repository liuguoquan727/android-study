package com.liuguoquan.module.ui.recyclerview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.recyclerview.adapter.DiffUtilAdapter;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 20:35.
 */

public class DiffUtilUI extends AppBaseFragment {

  @BindView(R2.id.list) RecyclerView mList;

  private List<Item> mDatas = new ArrayList<>();
  private List<Item> mNewDatas = new ArrayList<>();
  private DiffUtilAdapter mAdapter;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_recyclerview_diff_util;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "DiffUtil";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    for (int i = 0; i < 20; i++) {
      Item item = new Item(i, "liu" + i);
      mDatas.add(item);
    }

    mAdapter = new DiffUtilAdapter(getActivity(), mDatas);
    mList.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    mList.setItemAnimator(new DefaultItemAnimator());
    mList.setAdapter(mAdapter);
  }

  @OnClick({ R2.id.add, R2.id.remove }) public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.add) {
      add();
    } else if (i == R.id.remove) {
      refresh();
    }
  }

  private void refresh() {
    mNewDatas.clear();
    mNewDatas.addAll(mDatas);
    //改变第三个位置的对象
    Item item = new Item(3, "zhang");
    mNewDatas.remove(3);
    mNewDatas.add(3, item);
    DiffUtil.DiffResult diffResult =
        DiffUtil.calculateDiff(new DiffCallback(mDatas, mNewDatas), true);
    //将新数据给Adapter
    mAdapter.setDatas(mNewDatas);
    diffResult.dispatchUpdatesTo(mAdapter);
    mDatas.clear();
    mDatas.addAll(mNewDatas);
  }

  private void add() {

    mNewDatas.clear();
    mNewDatas.addAll(mDatas);
    mNewDatas.add(new Item(89, "xiao"));
    mNewDatas.add(new Item(90, "xia"));
    DiffUtil.DiffResult diffResult =
        DiffUtil.calculateDiff(new DiffCallback(mDatas, mNewDatas), true);
    mAdapter.setDatas(mNewDatas);
    diffResult.dispatchUpdatesTo(mAdapter);
    mDatas.clear();
    mDatas.addAll(mNewDatas);
  }

  private class DiffCallback extends DiffUtil.Callback {

    private List<Item> mOldDatas;
    private List<Item> mNewDatas;

    public DiffCallback(List<Item> oldDatas, List<Item> newDatas) {
      this.mOldDatas = oldDatas;
      this.mNewDatas = newDatas;
    }

    @Override public int getOldListSize() {
      return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override public int getNewListSize() {
      return mNewDatas != null ? mNewDatas.size() : 0;
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * For example, if your items have unique ids, this method should check their id equality.
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     * 本例判断id字段是否一致
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
      boolean is = mOldDatas.get(oldItemPosition).id == mNewDatas.get(newItemPosition).id;
      Log.d("lgq", "areItemsTheSame " + oldItemPosition + " " + newItemPosition + " " + is);
      return is;
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     * DiffUtil uses this method to check equality instead of {@link Object#equals(Object)}
     * DiffUtil 用这个方法替代equals方法去检查是否相等。
     * so that you can change its behavior depending on your UI.
     * 所以你可以根据你的UI去改变它的返回值
     * For example, if you are using DiffUtil with a
     * {@link android.support.v7.widget.RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     * This method is called only if {@link #areItemsTheSame(int, int)} returns
     * {@code true} for these items.
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     * oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
      String oldName = mOldDatas.get(oldItemPosition).getName();
      String newName = mNewDatas.get(newItemPosition).getName();
      Log.d("lgq", "areContentsTheSame" + " " + oldName + " " + newName);
      if (!oldName.equals(newName)) {
        Log.d("lgq", "false");
        return false;
      }
      return true;
    }

    /**
     * areItemsTheSame()返回true而areContentsTheSame()返回false，也就是说两个对象代表的数据是一条，但是内容更新了。
     */
    @Nullable @Override public Object getChangePayload(int oldItemPosition, int newItemPosition) {
      String oldItem = mOldDatas.get(oldItemPosition).getName();
      String newItem = mNewDatas.get(newItemPosition).getName();
      Bundle bundle = new Bundle();
      if (!oldItem.equals(newItem)) {
        bundle.putString("name", newItem);
      }

      if (bundle.size() == 0) {
        return null;
      }
      Log.d("lgq", "getChangePayload");
      return bundle;
    }
  }
}
