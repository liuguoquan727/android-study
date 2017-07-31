package com.liuguoquan.module.library.db;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.library.R;
import com.liuguoquan.module.library.R2;
import com.liuguoquan.module.library.dao.DaoHelper;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Created by liuguoquan on 2017/7/20.
 */

public class DBFragment extends AppBaseFragment {

  @Override public void initData(Bundle savedInstanceState) {

  }

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override public int getContentView() {
    return R.layout.module_library_fragment_db;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "GreenDao";
  }

  @Override public void initView(View view) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.insert, R2.id.read }) public void onClick(View v) {
    switch (v.getId()) {
      case R2.id.insert:
        insert();
        break;
      case R2.id.read:
        read();
        break;
    }
  }

  private void insert() {
    User user = new User();
    user.setId(23);
    user.setName("123");
    user.setAge(234);
    DaoHelper.getInstance(getActivity()).getDaoSession().getUserDao().insertOrReplace(user);
  }

  private void read() {
    User user = DaoHelper.getInstance(getActivity()).getDaoSession().getUserDao().load(23l);
    Log.d("lgq", "read: " + user.getName());
  }
}
