package com.liuguoquan.commom.bean;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liuguoquan on 2017/7/20.
 */

public class User {
  public long id;
  public String name;
  public int age;

  public User(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Generated(hash = 586692638) public User() {
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
