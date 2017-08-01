package com.liuguoquan.module.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:55.
 */

public class DialogUI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_dialog;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "dialog";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.custom, }) public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.custom) {
      showFullScreenDialog();
    }
  }

  private void showFullScreenDialog() {
    Dialog dialog = new Dialog(getActivity(), R.style.mydialog);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.module_ui_dialog_station_fee);
    dialog.getWindow()
        .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    dialog.show();
  }

  private void showCustomDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BaseAlertDialog);
    View view =
        LayoutInflater.from(getActivity()).inflate(R.layout.module_ui_dialog_station_fee, null);
    TextView fee = (TextView) view.findViewById(R.id.fee);
    EditText inputFee = (EditText) view.findViewById(R.id.input_fee);
    TextView cancel = (TextView) view.findViewById(R.id.cancel);
    TextView confirm = (TextView) view.findViewById(R.id.confirm);
    builder.setView(view);
    //builder.setPositiveButton("确定",null);
    //builder.setNegativeButton("取消",null);
    final AlertDialog dialog = builder.show();

    cancel.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dialog.dismiss();
      }
    });

    confirm.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dialog.dismiss();
      }
    });

    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
    params.width = 900;
    params.height = 690;
    dialog.getWindow().setAttributes(params);
  }
}
