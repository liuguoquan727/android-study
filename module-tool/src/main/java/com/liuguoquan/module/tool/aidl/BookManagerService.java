package com.liuguoquan.module.tool.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {

  private static final String TAG = "lgq";

  private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();

  private Binder mBinder = new IBookManager.Stub() {

    @Override public List<Book> getBookList() throws RemoteException {
      return mBooks;
    }

    @Override public void addBook(Book book) throws RemoteException {
      mBooks.add(book);
    }
  };

  public BookManagerService() {
  }

  @Override public void onCreate() {
    super.onCreate();
    mBooks.add(new Book("射雕英雄传", "55"));
    mBooks.add(new Book("神雕侠侣", "85"));
  }

  @Override public IBinder onBind(Intent intent) {
    return mBinder;
  }
}
