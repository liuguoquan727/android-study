package com.liuguoquan.study;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mdroid.lib.core.base.BaseApp;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 16:25.
 */

public class App extends BaseApp {

  @Override public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      ARouter.openLog();
      ARouter.openDebug();
      ARouter.printStackTrace();
    }
    ARouter.init(this);
  }

  @Override public boolean isDebug() {
    return true;
  }
}
