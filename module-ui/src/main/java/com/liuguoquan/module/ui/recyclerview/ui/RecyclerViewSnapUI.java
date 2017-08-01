package com.liuguoquan.module.ui.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.recyclerview.adapter.App;
import com.liuguoquan.module.ui.recyclerview.adapter.Snap;
import com.liuguoquan.module.ui.recyclerview.adapter.SnapAdapter;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 21:01.
 */

public class RecyclerViewSnapUI extends AppBaseFragment implements Toolbar.OnMenuItemClickListener {

  public static final String ORIENTATION = "orientation";

  private RecyclerView mRecyclerView;
  private boolean mHorizontal;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_recyclerview_snap;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "SnapHelper";
  }

  @Override protected void initData(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      mHorizontal = true;
    } else {
      mHorizontal = savedInstanceState.getBoolean(ORIENTATION);
    }
  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    mRecyclerView = (RecyclerView) parent.findViewById(R.id.recyclerView);
    Toolbar toolbar = getToolBar();
    toolbar.inflateMenu(R.menu.main);
    toolbar.setBackgroundResource(R.color.main_color_normal);
    toolbar.setOnMenuItemClickListener(this);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setHasFixedSize(true);

    setupAdapter();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(ORIENTATION, mHorizontal);
  }

  private void setupAdapter() {
    List<App> apps = getApps();

    SnapAdapter snapAdapter = new SnapAdapter();
    if (mHorizontal) {
      snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Snap center", apps));
      snapAdapter.addSnap(new Snap(Gravity.START, "Snap start", apps));
      snapAdapter.addSnap(new Snap(Gravity.END, "Snap end", apps));
    } else {
      snapAdapter.addSnap(new Snap(Gravity.CENTER_VERTICAL, "Snap center", apps));
      snapAdapter.addSnap(new Snap(Gravity.TOP, "Snap top", apps));
      snapAdapter.addSnap(new Snap(Gravity.BOTTOM, "Snap bottom", apps));
    }

    mRecyclerView.setAdapter(snapAdapter);
  }

  private List<App> getApps() {
    List<App> apps = new ArrayList<>();
    apps.add(new App("Google+", R.drawable.ic_google_48dp, 4.6f));
    apps.add(new App("Gmail", R.drawable.ic_gmail_48dp, 4.8f));
    apps.add(new App("Inbox", R.drawable.ic_inbox_48dp, 4.5f));
    apps.add(new App("Google Keep", R.drawable.ic_keep_48dp, 4.2f));
    apps.add(new App("Google Drive", R.drawable.ic_drive_48dp, 4.6f));
    apps.add(new App("Hangouts", R.drawable.ic_hangouts_48dp, 3.9f));
    apps.add(new App("Google Photos", R.drawable.ic_photos_48dp, 4.6f));
    apps.add(new App("Messenger", R.drawable.ic_messenger_48dp, 4.2f));
    apps.add(new App("Sheets", R.drawable.ic_sheets_48dp, 4.2f));
    apps.add(new App("Slides", R.drawable.ic_slides_48dp, 4.2f));
    apps.add(new App("Docs", R.drawable.ic_docs_48dp, 4.2f));
    apps.add(new App("Google+", R.drawable.ic_google_48dp, 4.6f));
    apps.add(new App("Gmail", R.drawable.ic_gmail_48dp, 4.8f));
    apps.add(new App("Inbox", R.drawable.ic_inbox_48dp, 4.5f));
    apps.add(new App("Google Keep", R.drawable.ic_keep_48dp, 4.2f));
    apps.add(new App("Google Drive", R.drawable.ic_drive_48dp, 4.6f));
    apps.add(new App("Hangouts", R.drawable.ic_hangouts_48dp, 3.9f));
    apps.add(new App("Google Photos", R.drawable.ic_photos_48dp, 4.6f));
    apps.add(new App("Messenger", R.drawable.ic_messenger_48dp, 4.2f));
    apps.add(new App("Sheets", R.drawable.ic_sheets_48dp, 4.2f));
    apps.add(new App("Slides", R.drawable.ic_slides_48dp, 4.2f));
    apps.add(new App("Docs", R.drawable.ic_docs_48dp, 4.2f));
    return apps;
  }

  @Override public boolean onMenuItemClick(MenuItem item) {
    if (item.getItemId() == R.id.layoutType) {
      mHorizontal = !mHorizontal;
      setupAdapter();
      item.setTitle(mHorizontal ? "Vertical" : "Horizontal");
    }
    return false;
  }
}
