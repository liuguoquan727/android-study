package com.liuguoquan.module.ui.design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.liuguoquan.module.ui.R;
import com.liuguoquan.module.ui.R2;

public class ToolbarActivity extends AppCompatActivity {

  @BindView(R2.id.toolbar) Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_toolbar);
    ButterKnife.bind(this);

    mToolbar.setTitle("Title");
    mToolbar.setSubtitle("SubTitle");
    mToolbar.setLogo(R.drawable.ic_launcher);
    mToolbar.setNavigationIcon(R.drawable.ic_launcher);
    setSupportActionBar(mToolbar);
    mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
          Toast.makeText(ToolbarActivity.this, "设置", Toast.LENGTH_SHORT).show();
          return true;
        }
        return true;
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_scrolling, menu);
    return true;
  }
}
