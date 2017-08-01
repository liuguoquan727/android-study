package com.liuguoquan.module.ui.recyclerview.ui;

/**
 * Created by liuguoquan on 2016/10/18.
 */

public class Item {

  public int id = 0;
  public String name;

  public Item(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
