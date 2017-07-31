package com.liuguoquan.module.library.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/27 22:24.
 */

@Module public class WatchModule {

  @Provides public Watch provideWatch() {
    Watch watch = new Watch("TISSOT", 3333);
    return watch;
  }
}
