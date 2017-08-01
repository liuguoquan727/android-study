package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.design.adapter.CoordinatorAdapter;
import java.util.ArrayList;
import java.util.List;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

  private List<String> mTitles = new ArrayList<>();
  private View bottomSheet;
  private BottomSheetBehavior behavior;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_sheet_behavior);
    ButterKnife.bind(this);
    initView();
  }

  private void initView() {
    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");
    mTitles.add("体育");
    mTitles.add("军事");
    mTitles.add("科技");
    mTitles.add("教育");
    mTitles.add("历史");
    mTitles.add("文化");
    mTitles.add("深圳");
    mTitles.add("新闻");
    mTitles.add("财经");
    mTitles.add("娱乐");
    mTitles.add("体育");
    mTitles.add("军事");
    mTitles.add("科技");
    mTitles.add("教育");
    mTitles.add("历史");

    CoordinatorLayout coordinatorLayout =
        (CoordinatorLayout) findViewById(R.id.activity_bottom_sheet_behavior);
    //必须是CoordinatorLayout的子View
    bottomSheet = coordinatorLayout.findViewById(R.id.design_bottom_sheet);
    behavior = BottomSheetBehavior.from(bottomSheet);
    behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
        Log.d("lgq", "state: " + newState);
      }

      @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        //slideoffset 0 ~ 1.0
        Log.d("lgq", "slideOffset: " + slideOffset);
      }
    });
  }

  @OnClick({ R2.id.bottom_sheet_dialog, R2.id.bottom_sheet_behavior })
  public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.bottom_sheet_dialog) {
      showBottomSheetDialog();
    } else if (i == R.id.bottom_sheet_behavior) {
      showBottomBehaviorDialog();
    }
  }

  /**
   * STATE_COLLAPSED：关闭Bottom Sheets，高度可控属性app:behavior_peekHeight（默认为0）
   * STATE_DRAGGING：在用户直接拖动Bottom Sheets向上或向下的中间状态
   * STATE_SETTLING：当Bottom Sheets被释放和沉降到其最终位置记录的状态
   * STATE_EXPANDED：Bottom Sheets展开时的状态
   * STATE_HIDDEN：默认情况下禁用(和启用应用程序：behavior_hideable属性),这使得用户可以向下滑动Bottom Sheets完全隐藏底部。
   */
  private void showBottomBehaviorDialog() {
    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
  }

  private void showBottomSheetDialog() {
    BottomSheetDialog dialog = new BottomSheetDialog(this);
    View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_list, null);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new CoordinatorAdapter(this, mTitles));
    dialog.setContentView(view);
    dialog.show();
  }
}
