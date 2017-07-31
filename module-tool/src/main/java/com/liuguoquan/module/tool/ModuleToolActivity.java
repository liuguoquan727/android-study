package com.liuguoquan.module.tool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Description:
 *
 * Created by liuguoquan on 2017/7/31 20:17.
 */

public class ModuleToolActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.module_tool_activity_main);
    Fragment fragment = new ToolFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.content_container, fragment);
    transaction.commit();
  }
}
