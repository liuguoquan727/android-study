package com.liuguoquan.study;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.alibaba.android.arouter.launcher.ARouter;
import com.liuguoquan.commom.base.AppBaseActivity;
import com.liuguoquan.router.RouterConstant;
import com.mdroid.app.TabManager;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

import static com.liuguoquan.study.R.id.ui;

public class MainActivity extends AppBaseActivity {

  public static final String UI = "ui";
  public static final String LIBRARY = "library";
  public static final String TOOL = "Tool";

  private TabManager mTabManager;
  private BottomNavigationView mBottomNavigationView;

  @Override public Status getCurrentStatus() {
    return null;
  }

  @Override public String getPageTitle() {
    return "UI";
  }

  @Override protected int getContentView() {
    return R.layout.activity_main;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          if (mTabManager == null) {
            return false;
          }
          switch (item.getItemId()) {
            case ui:
              if (!UI.equals(mTabManager.getCurrentTag())) {
                mTabManager.changeTab(UI);
              }
              return true;
            case R.id.library:
              if (!LIBRARY.equals(mTabManager.getCurrentTag())) {
                mTabManager.changeTab(LIBRARY);
              }
              return true;
            case R.id.tool:
              if (!TOOL.equals(mTabManager.getCurrentTag())) {
                mTabManager.changeTab(TOOL);
              }
              return true;
          }
          return false;
        }
      };

  @Override protected void initData(Bundle savedInstanceState) {
    mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
    mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    Fragment uiFragment =
        (Fragment) ARouter.getInstance().build(RouterConstant.MODULE_UI_MAIN).navigation();
    if (uiFragment == null) {
      return;
    }
    Fragment libraryFragment =
        (Fragment) ARouter.getInstance().build(RouterConstant.MODULE_LIBRARY_MAIN).navigation();
    if (libraryFragment == null) {
      return;
    }
    Fragment toolFragment =
        (Fragment) ARouter.getInstance().build(RouterConstant.MODULE_TOOL_MAIN).navigation();
    if (toolFragment == null) {
      return;
    }

    mTabManager = new TabManager(this, getSupportFragmentManager(), R.id.main_container);
    mTabManager.addTab(UI, uiFragment.getClass(), null)
        .addTab(LIBRARY, libraryFragment.getClass(), null)
        .addTab(TOOL, toolFragment.getClass(), null);
    if (savedInstanceState != null) {
      mTabManager.restoreState(savedInstanceState);
    } else {
      mTabManager.changeTab(UI);
    }
  }
}
