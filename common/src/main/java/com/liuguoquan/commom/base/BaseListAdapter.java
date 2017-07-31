package com.liuguoquan.commom.base;

import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：ListView或者GridView的adapter的基类
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
  protected List<T> mData;

  public BaseListAdapter(List<T> list) {
    this.mData = list == null ? new ArrayList<T>(0) : list;
  }

  public List<T> getData() {
    return mData;
  }

  public void add(T data) {
    mData.add(data);
    notifyDataSetChanged();
  }

  public void add(List<T> data) {
    if (data != null && data.size() != 0) {
      mData.addAll(data);
      notifyDataSetChanged();
    }
  }

  public void remove(int index) {
    mData.remove(index);
    notifyDataSetChanged();
  }

  public void remove(T data) {
    mData.remove(data);
    notifyDataSetChanged();
  }

  public void remove(List<T> dataList) {
    mData.removeAll(dataList);
    notifyDataSetChanged();
  }

  public void resetData(List<T> list) {
    this.mData = list == null ? new ArrayList<T>(0) : list;
  }

  @Override public int getCount() {
    return mData.size();
  }

  @Override public Object getItem(int position) {
    return mData.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }
}
