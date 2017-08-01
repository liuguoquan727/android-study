package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;

public class FabActivity extends AppCompatActivity {

  @BindView(R2.id.fab) FloatingActionButton mFab;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fab);
    ButterKnife.bind(this);

    mFab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final Snackbar snackbar = Snackbar.make(v, "snackbar click", Snackbar.LENGTH_SHORT);
        snackbar.setAction("确定", new View.OnClickListener() {
          @Override public void onClick(View v) {
            snackbar.dismiss();
          }
        });
        snackbar.show();
      }
    });
  }
}
