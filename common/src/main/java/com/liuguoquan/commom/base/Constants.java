package com.liuguoquan.commom.base;

import android.text.TextUtils;
import com.mdroid.lib.core.base.BaseExtraKeys;

/**
 * 常量配置
 */
public class Constants {

  public static final String TAG = "lgq";
  public static String HOST = "https://api.github.com";

  public static String getSmallPicture(String url) {
    if (TextUtils.isEmpty(url)) return null;
    return url.concat("-small");
  }

  public static String getMediumPicture(String url) {
    if (TextUtils.isEmpty(url)) return null;
    return url.concat("-medium");
  }

  public static String getLargePicture(String url) {
    if (TextUtils.isEmpty(url)) return null;
    return url.concat("-large");
  }

  /**
   * 跳转activity时附带数据的key值
   */
  public interface ExtraKey extends BaseExtraKeys {

    /** 手机号是否绑定 **/
    String KEY_PHONE_IS_BINDED = "isbind";
    String KEY_PHONE_INPUT_TYPE = "input_type";
    String KEY_USER_NAME = "user_name";
    String KEY_MERCHANT_ID = "merchant_id";
    String KEY_SITE_ID = "siteId";
    String KEY_SITE_NAME = "siteName";
    String KEY_SITE_PHOTOS = "sitePhotos";

    String KEY_PHONE = "phone_number";
    String KEY_ORDER_ID = "orderId";
    String KEY_ORDER_START_TIME = "orderStartTime";
    String KEY_ORDER_STATUS = "orderStatus";
    String KEY_DATA = "data";
    String KEY_GUN_CODE = "gun_code";
    String KEY_STALL_NUMBER = "stall_number";
  }

  public interface NormalCons {
    String SEPARATOR_COMMA = ",";
    String SEPARATOR_POINT = ".";
    String SEPARATOR_LINE = "--";
    /** 分页--每页10条数据 */
    int LIMIT_10 = 10;
    /** 分页--每页20条数据 */
    int LIMIT_20 = 20;
    /** 每行3列 */
    int GRID_COLUMN_3 = 3;
    /** 每行4列 */
    int GRID_COLUMN_4 = 4;
    /** 每行5列 */
    int GRID_COLUMN_5 = 5;
  }
}
