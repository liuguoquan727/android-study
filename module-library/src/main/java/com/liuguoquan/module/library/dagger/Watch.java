package com.liuguoquan.module.library.dagger;

import android.util.Log;
import com.liuguoquan.commom.base.Constants;

/**
 * Created by liuguoquan on 2017/7/27.
 */

public class Watch {

  private String name;
  private int price;

  public Watch() {

  }

  public Watch(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void work() {
    Log.d(Constants.TAG, "work: :" + "手表工作");
  }

  @Override public String toString() {
    return "Watch{" + "name='" + name + '\'' + ", price=" + price + '}';
  }
}
