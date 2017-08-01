package com.liuguoquan.module.ui.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuguoquan on 16/9/21.
 */

public class ListRecyclerViewAdapter
    extends RecyclerView.Adapter<ListRecyclerViewAdapter.ItemHolder>
    implements ItemTouchHelperListener {

  private OnStartDragListener mListener;
  private Context mContext;
  private List<String> mDatas = new ArrayList<>();

  public ListRecyclerViewAdapter(Context context, List<String> datas) {
    this.mContext = context;
    this.mDatas = datas;
  }

  public void setOnStartDragListener(OnStartDragListener listener) {
    mListener = listener;
  }

  @Override public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view =
        LayoutInflater.from(mContext).inflate(R.layout.module_ui_item_list_drag, parent, false);
    return new ItemHolder(view);
  }

  @Override public void onBindViewHolder(final ItemHolder holder, int position) {

    String title = mDatas.get(position);
    holder.mTitle.setText(title);

    //控件触发条目上下移动
    holder.mImage.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
          mListener.onStartDrag(holder);
        }
        return false;
      }
    });
  }

  @Override public int getItemCount() {
    return mDatas.size();
  }

  @Override public void onItemMove(int fromPosition, int toPosition) {
    Collections.swap(mDatas, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override public void onItemDismiss(int position) {
    mDatas.remove(position);
    notifyItemRemoved(position);
  }

  static class ItemHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    @BindView(R2.id.title) TextView mTitle;
    @BindView(R2.id.image) ImageView mImage;

    public ItemHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override public void onItemSelected() {
      itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override public void onItemClear() {
      itemView.setBackgroundColor(Color.WHITE);
    }
  }
}
