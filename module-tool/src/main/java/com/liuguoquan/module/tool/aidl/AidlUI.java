package com.liuguoquan.module.tool.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/3 08:58.
 */

public class AidlUI extends AppBaseFragment {

  private static final String TAG = "lgq";

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_aidl;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "AIDL";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  private ServiceConnection mConnection = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
      IBookManager manager = IBookManager.Stub.asInterface(iBinder);
      Book book = new Book("天龙八部", "60");
      try {
        manager.addBook(book);
        for (Book b : manager.getBookList()) {
          Log.d(TAG, "onServiceConnected: " + b.toString());
        }
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public void onServiceDisconnected(ComponentName componentName) {
      Log.d(TAG, "onServiceDisconnected: ");
    }
  };

  @OnClick({ R2.id.start, R2.id.stop }) public void onClick(View v) {
    int id = v.getId();
    if (id == R2.id.start) {
      Intent intent = new Intent(getActivity(), BookManagerService.class);
      getActivity().bindService(intent, mConnection, BIND_AUTO_CREATE);
    } else if (id == R2.id.stop) {
      getActivity().unbindService(mConnection);
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }
}
