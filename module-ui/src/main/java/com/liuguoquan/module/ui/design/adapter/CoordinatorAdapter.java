package com.liuguoquan.module.ui.design.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguoquan on 16/9/21.
 */

public class CoordinatorAdapter extends RecyclerView.Adapter<CoordinatorAdapter.ItemHolder> {

  private Context mContext;
  private Fragment mFragement;
  private List<String> mDatas = new ArrayList<>();

  public CoordinatorAdapter(Fragment fragment, List<String> datas) {
    this.mFragement = fragment;
    this.mContext = fragment.getActivity();
    this.mDatas = datas;
  }

  public CoordinatorAdapter(Context context, List<String> datas) {
    this.mContext = context;
    this.mDatas = datas;
  }

  @Override public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(mContext).inflate(R.layout.item_coordinator, parent, false);
    return new ItemHolder(view);
  }

  @Override public void onBindViewHolder(ItemHolder holder, int position) {

    String title = mDatas.get(position);
    holder.mTitle.setText(title);
  }

  @Override public int getItemCount() {
    return mDatas.size();
  }

  static class ItemHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.title) TextView mTitle;

    public ItemHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
