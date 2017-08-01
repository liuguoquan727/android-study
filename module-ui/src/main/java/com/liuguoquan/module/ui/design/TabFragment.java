package com.liuguoquan.module.ui.design;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;
import com.liuguoquan.module.ui.design.adapter.CoordinatorAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

  public static final String TITLE = "title";

  @BindView(R2.id.list) RecyclerView mList;

  private String mTitle;
  private List<String> mTitles = new ArrayList<>();

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("lgq", mTitle + "onCreate");
    mTitle = getArguments().getString(TITLE);

    for (int i = 0; i < 20; i++) {
      mTitles.add("item " + i);
    }
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Log.d("lgq", mTitle + "onCreateView");
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_tab, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Log.d("lgq", mTitle + "onViewCreated");

    mList.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    mList.setItemAnimator(new DefaultItemAnimator());
    CoordinatorAdapter adapter = new CoordinatorAdapter(this, mTitles);
    mList.setAdapter(adapter);
  }

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
      Log.d("lgq", mTitle + "visible");
    } else {
      Log.d("lgq", mTitle + "inVisible");
    }
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }
}
