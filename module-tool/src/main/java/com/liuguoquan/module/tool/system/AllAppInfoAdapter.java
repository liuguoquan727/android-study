package com.liuguoquan.module.tool.system;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.blankj.utilcode.util.AppUtils;
import com.liuguoquan.module.tool.R;
import com.mdroid.lib.recyclerview.BaseRecyclerViewAdapter;
import com.mdroid.lib.recyclerview.BaseViewHolder;
import java.util.List;

/**
 * Created by liuguoquan on 2017/7/25.
 */

public class AllAppInfoAdapter extends BaseRecyclerViewAdapter<AppUtils.AppInfo> {

  private Fragment mFragment;

  public AllAppInfoAdapter(Fragment fragment, @NonNull List<AppUtils.AppInfo> data) {
    super(R.layout.module_tool_item_list_app_info, data);
    this.mFragment = fragment;
  }

  @Override protected void convert(BaseViewHolder holder, AppUtils.AppInfo item) {
    holder.setImageDrawable(R.id.icon, item.getIcon());
    holder.setText(R.id.app_name, item.getName());
    holder.setText(R.id.app_packagename, item.getPackageName());
    holder.setText(R.id.app_version, item.getVersionName());
  }
}
