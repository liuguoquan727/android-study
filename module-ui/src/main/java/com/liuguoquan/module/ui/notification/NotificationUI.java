package com.liuguoquan.module.ui.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:25.
 */

public class NotificationUI extends AppBaseFragment {

  private NotificationManager mManager;

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_notification;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "Notification";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
    mManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
  }

  @OnClick({ R2.id.normal, R2.id.fold, R2.id.pending }) public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.normal) {
      normalMode();
    } else if (i == R.id.fold) {
      foldMode();
    } else if (i == R.id.pending) {
      pendingMode();
    }
  }

  private void normalMode() {
    //首先，创建Builder对象，创建一个PendingIntent来实现消息点击跳转事件。
    Notification.Builder builder = new Notification.Builder(getActivity());
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
    PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
    //其次，通过builder给Notification添加不同的属性：
    builder.setContentIntent(pendingIntent);
    builder.setSmallIcon(R.drawable.ic_launcher);
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    builder.setAutoCancel(true);
    builder.setContentTitle("普通Notification");

    //最后，通过NotifcationManager对象，调用notify发送一个通知
    mManager.notify(0, builder.build());
  }

  private void foldMode() {
    Notification.Builder builder = new Notification.Builder(getActivity());
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
    PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

    builder.setContentIntent(pendingIntent);
    builder.setSmallIcon(R.drawable.ic_launcher);
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    builder.setAutoCancel(true);
    builder.setContentTitle("折叠式Notification");

    //用RemoteViews来创建自定义Notification视图
    RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(),
        R.layout.module_ui_notification_layout_fold);
    Notification notification = builder.build();
    //指定展开的视图
    notification.bigContentView = remoteViews;
    //notification.contentView = remoteViews;
    mManager.notify(1, notification);
  }

  private void pendingMode() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
    PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

    builder.setContentIntent(pendingIntent);
    builder.setSmallIcon(R.drawable.ic_launcher);
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    builder.setAutoCancel(true);
    builder.setContentTitle("悬挂式Notification");

    //设置点击跳转
    Intent hangIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
    hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消当前的
    PendingIntent hangPendingIntent =
        PendingIntent.getActivity(getActivity(), 0, hangIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    //setFullScreenIntent来将Notification变为悬挂式Notification。
    builder.setFullScreenIntent(hangPendingIntent, true);

    Notification notification = builder.build();

    mManager.notify(2, notification);
  }
}
