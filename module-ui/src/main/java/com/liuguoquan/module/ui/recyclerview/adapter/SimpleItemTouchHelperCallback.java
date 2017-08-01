package com.liuguoquan.module.ui.recyclerview.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by liuguoquan on 2016/10/21.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

  private final ItemTouchHelperListener mListener;

  public SimpleItemTouchHelperCallback(ItemTouchHelperListener listener) {
    this.mListener = listener;
  }

  /**
   * 指定可以支持的拖放和滑动的方向
   */
  @Override public int getMovementFlags(RecyclerView recyclerView,
      RecyclerView.ViewHolder viewHolder) {
    if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
      int dragFlags =
          ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
      int swipeFlags = 0;
      return makeMovementFlags(dragFlags, swipeFlags);
    } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
      int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
      int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
      return makeMovementFlags(dragFlags, swipeFlags);
    }
    return makeMovementFlags(0, 0);
  }

  /**
   * 上下移动时调用
   */
  @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {
    if (viewHolder.getItemViewType() != target.getItemViewType()) {
      return false;
    }
    mListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  /**
   * 左右滑动时调用
   */
  @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    mListener.onItemDismiss(viewHolder.getAdapterPosition());
  }

  /**
   * 支持长按RecyclerView item进入拖动操作，你必须在isLongPressDragEnabled()方法中返回true。或者，也可以调用ItemTouchHelper.startDrag(RecyclerView.ViewHolder)
   * 方法来开始一个拖动。
   */
  @Override public boolean isLongPressDragEnabled() {
    return true;
  }

  /**
   * 而要在view任意位置触摸事件发生时启用滑动操作，则直接在sItemViewSwipeEnabled()中返回true就可以了。或者，你也主动调用ItemTouchHelper.startSwipe(RecyclerView.ViewHolder)
   * 来开始滑动操作。
   */
  @Override public boolean isItemViewSwipeEnabled() {
    return true;
  }

  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      float dX, float dY, int actionState, boolean isCurrentlyActive) {
    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      final float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
      viewHolder.itemView.setAlpha(alpha);
      viewHolder.itemView.setTranslationX(dX);
    } else {
      super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
  }

  /**
   * 当item移动时调用
   */
  @Override public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
      if (viewHolder instanceof ItemTouchHelperViewHolder) {
        ItemTouchHelperViewHolder holder = (ItemTouchHelperViewHolder) viewHolder;
        holder.onItemSelected();
      }
    }
    super.onSelectedChanged(viewHolder, actionState);
  }

  /**
   * 当条目停止移动时调用
   */
  @Override public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    viewHolder.itemView.setAlpha(1.0f);
    if (viewHolder instanceof ItemTouchHelperViewHolder) {
      ItemTouchHelperViewHolder holder = (ItemTouchHelperViewHolder) viewHolder;
      holder.onItemClear();
    }
  }
}
