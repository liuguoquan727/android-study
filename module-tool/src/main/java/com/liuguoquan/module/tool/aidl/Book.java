package com.liuguoquan.module.tool.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuguoquan on 2017/7/12.
 */

public class Book implements Parcelable {

  public String name;
  public String price;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeString(this.price);
  }

  public Book() {
  }

  public Book(String name, String price) {
    this.name = name;
    this.price = price;
  }

  protected Book(Parcel in) {
    this.name = in.readString();
    this.price = in.readString();
  }

  public static final Creator<Book> CREATOR = new Creator<Book>() {
    @Override public Book createFromParcel(Parcel source) {
      return new Book(source);
    }

    @Override public Book[] newArray(int size) {
      return new Book[size];
    }
  };

  @Override public String toString() {
    return "Book{" + "name='" + name + '\'' + ", price='" + price + '\'' + '}';
  }
}
