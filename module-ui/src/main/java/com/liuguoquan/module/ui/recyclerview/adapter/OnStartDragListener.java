package com.liuguoquan.module.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by liuguoquan on 2016/10/21.
 */

public interface OnStartDragListener {
  /**
   * Called when a view is requesting a start of a drag.
   *
   * @param viewHolder The holder of the view to drag.
   */
  void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
