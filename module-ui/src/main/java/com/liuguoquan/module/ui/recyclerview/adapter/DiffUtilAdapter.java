package com.liuguoquan.module.ui.recyclerview.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.recyclerview.ui.Item;
import java.util.List;

/**
 * Created by liuguoquan on 2016/10/18.
 */

public class DiffUtilAdapter extends RecyclerView.Adapter<DiffUtilAdapter.DiffItemHolder> {

  private Context mContext;
  private List<Item> mDatas;

  public DiffUtilAdapter(Context context, List<Item> datas) {
    this.mContext = context;
    this.mDatas = datas;
  }

  public void setDatas(List<Item> mDatas) {
    this.mDatas = mDatas;
  }

  @Override public DiffItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.module_ui_item_diff_util, parent, false);
    return new DiffItemHolder(view);
  }

  @Override public void onBindViewHolder(DiffItemHolder holder, int position) {
    Item info = mDatas.get(position);
    holder.mInfo.setText(info.getName());
    Log.d("lgq", "onBindViewHolder");
  }

  @Override
  public void onBindViewHolder(DiffItemHolder holder, int position, List<Object> payloads) {
    Log.d("lgq", "onBindViewHolder2");
    if (payloads.isEmpty()) {
      onBindViewHolder(holder, position);
    } else {
      Bundle bundle = (Bundle) payloads.get(0);
      for (String key : bundle.keySet()) {
        switch (key) {
          case "name":
            holder.mInfo.setText((CharSequence) bundle.get(key));
            break;
        }
      }
    }
  }

  @Override public int getItemCount() {
    return mDatas != null ? mDatas.size() : 0;
  }

  static class DiffItemHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.info) TextView mInfo;

    public DiffItemHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
