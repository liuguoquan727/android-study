package com.liuguoquan.module.tool.system;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.blankj.utilcode.util.AppUtils;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import com.mdroid.lib.core.rxjava.PausedHandlerScheduler;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by liuguoquan on 2017/7/25.
 */

public class AllAppInfoFragment extends AppBaseFragment {

  @BindView(R2.id.list) RecyclerView mList;

  private List<AppUtils.AppInfo> mAppInfos;
  private AllAppInfoAdapter mAdapter;

  @Override public void initData(Bundle savedInstanceState) {

  }

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override public int getContentView() {
    return R.layout.module_tool_fragment_all_app_info;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "App信息";
  }

  @Override public void initView(View view) {
    requestBaseInit(getPageTitle());
    mAdapter = new AllAppInfoAdapter(this, mAppInfos);
    mList.setLayoutManager(new LinearLayoutManager(getActivity()));
    mList.setAdapter(mAdapter);
    Observable.just("2")
        .subscribeOn(Schedulers.io())
        .map(new Function<String, List<AppUtils.AppInfo>>() {
          @Override public List<AppUtils.AppInfo> apply(@NonNull String s) throws Exception {
            return AppUtils.getAppsInfo();
          }
        })
        .observeOn(PausedHandlerScheduler.from(getHandler()))
        .compose(mLifecycleProvider.<List<AppUtils.AppInfo>>bindUntilEvent(FragmentEvent.DESTROY))
        .subscribe(new Consumer<List<AppUtils.AppInfo>>() {
          @Override public void accept(@NonNull List<AppUtils.AppInfo> appInfos) throws Exception {
            mAdapter.resetData(appInfos);
          }
        });
  }
}
