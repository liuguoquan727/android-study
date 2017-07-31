package com.liuguoquan.commom.base;

/**
 * 常量(Key 都放在这, 防止重复)
 */
public interface DBKeys {
  /**
   * 手机所属地区(国家)列表 如: +86 +886等等
   */
  String AREA_LIST = "areas";
  /**
   * 权限对话框是否已经显示
   */
  String PERMISSION_CHECK_FLAG = "permission_check_flag";

  /**
   * 登录账号
   */
  String USER_NAME = "username";

  //省市区
  String ADDRESS_LIST = "address_list";
  /** 站点筛选-可选地址信息 */
  String SITE_AREA_INFO = "siteAreaInfo";
  /** 站点搜索历史记录 */
  String KEY_SITE_SEARCH_HISTORY = "siteSearchHistory";
  /** 订单搜索历史记录 */
  String KEY_ORDER_SEARCH_HISTORY = "orderSearchHistory";
}
