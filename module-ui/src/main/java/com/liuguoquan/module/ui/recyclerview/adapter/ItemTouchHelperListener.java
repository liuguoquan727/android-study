package com.liuguoquan.module.ui.recyclerview.adapter;

/**
 * Created by liuguoquan on 2016/10/21.
 */

public interface ItemTouchHelperListener {

  void onItemMove(int fromPosition, int toPosition);

  void onItemDismiss(int position);
}
