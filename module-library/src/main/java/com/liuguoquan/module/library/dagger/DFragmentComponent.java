package com.liuguoquan.module.library.dagger;

import dagger.Component;

/**
 * Description: 注入器，将目标类依赖的实例注入到目标类DaggerFragment中
 *
 * Created by liuguoquan on 2017/7/27.
 */

@Component(modules = WatchModule.class) public interface DFragmentComponent {

  void inject(DaggerFragment fragment);
}
