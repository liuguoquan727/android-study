package com.liuguoquan.module.library.dao;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by liuguoquan on 2017/7/20.
 */

public class DaoHelper {

  private static DaoHelper sInstance;
  private DaoMaster.DevOpenHelper mHelper;
  private SQLiteDatabase db;
  private DaoMaster daoMaster;
  private DaoSession mDaoSession;

  public DaoHelper(Context context) {
    mHelper = new DaoMaster.DevOpenHelper(context, "liu.db", null);
    db = mHelper.getWritableDatabase("123");
    daoMaster = new DaoMaster(db);
    mDaoSession = daoMaster.newSession();
  }

  public static DaoHelper getInstance(Context context) {
    if (sInstance == null) {
      SQLiteDatabase.loadLibs(context.getApplicationContext());
      sInstance = new DaoHelper(context);
    }
    return sInstance;
  }

  public DaoSession getDaoSession() {
    return mDaoSession;
  }
}
