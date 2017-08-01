package com.liuguoquan.module.ui.date;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import butterknife.OnClick;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;
import java.util.Calendar;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/8/1 17:34.
 */

public class DateUI extends AppBaseFragment {
  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_ui_date;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "时间日期";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    requestBaseInit(getPageTitle());
  }

  @OnClick({ R2.id.time, R2.id.date }) public void onCLick(View v) {
    int i = v.getId();
    if (i == R.id.time) {
      showTime();
    } else if (i == R.id.date) {
      showDate();
    }
  }

  private void showTime() {

    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);
    TimePickerDialog dialog =
        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
          @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

          }
        }, mHour, mMinute, true);
    dialog.show();
  }

  private void showDate() {
    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);
    DatePickerDialog dialog =
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
          @Override public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

          }
        }, mYear, mMonth, mDay);
    dialog.show();
  }
}
