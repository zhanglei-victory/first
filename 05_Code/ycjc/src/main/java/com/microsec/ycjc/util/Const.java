package com.microsec.ycjc.util;

public class Const {
	/** “888888”的md5加密 */
	public static final String PWD_MD5 = "21218CCA77804D2BA1922C33E0151105";
	
	/** 当前登录的用户attribute  */
	public static final String LOGIN_USER_ATTRIBUTE = "loginUser";
	/** 用户名或者密码错误 */
	public static final String LOGIN_ERR_MSG = "用户名或者密码错误";
	/** 此用户已经被登录 */
	public static final String USER_ONLINE_MSG = "此用户已经被登录";
	/** 父权限节点的上级id */
	public static final String ROOT_AUTH_ID = "999999999";
	/** 短信接口URL */
	public static final String SMS_URL = "http://api.duanxin.cm/";
	/** 短信接口用户名 */
	public static final String SMS_USERNAME = "70201300";
	/** 短信接口密码 */
	public static final String SMS_PASSWORD = "527cce16d89029ad3af62b603a5d40d0";
	
	/** 闸点报警水位 */
	public static final String WATERPOINT_LEVEL_ALARM_TYPE = "wlss";
	/** 闸点报警水位增速 */
	public static final String WATERPOINT_LEVELUP_ALARM_TYPE = "wahs";
	/** 闸点报警水速 */
	public static final String WATERPOINT_SPEED_ALARM_TYPE = "wsss";
	/** 闸点报警水速增速 */
	public static final String WATERPOINT_SPEEDUP_ALARM_TYPE = "wass";
	
	/** 闸点报警水位后缀*/
	public static final String WATERPOINT_LEVEL_ALARM_TYPE_SUFFIX = "shuiwei";
	/** 闸点报警水位增速后缀 */
	public static final String WATERPOINT_LEVELUP_ALARM_TYPE_SUFFIX = "shuiweizengsu";
	/** 闸点报警水速 后缀*/
	public static final String WATERPOINT_SPEED_ALARM_TYPE_SUFFIX = "shuisu";
	/** 闸点报警水速增速 后缀*/
	public static final String WATERPOINT_SPEEDUP_ALARM_TYPE_SUFFIX = "shuisuzengshu";
	
	/** 配置文件存放application范围内key*/
	public static final String CONTEXT_MAP = "context_map";
	
	/** 分页默认的页数 */
	public static final String START_PAGE_NUMBER = "1";

	/** 分页每页初始化条数 */
	public static final String SINGLE_PAGE_SIZE = "10";
	
	/** 水里系统 */
	public static final String SYS_NAME_WATER = "水利";
	/** 自备井系统 */
	public static final String SYS_NAME_WELL = "自备井";
	/** 水源地系统 */
	public static final String SYS_NAME_SOURCE = "水源地";
	
	/** 仓库hashmap */
	public static final String MAP_STORAGE = "storageMap";
	
	/** 养殖场hashmap */
	public static final String MAP_FARM = "farmMap";
	
	/** 屠宰场hashmap */
	public static final String MAP_SLAUHOUSE = "slauhouseMap";
	
	/** 超市hashmap */
	public static final String MAP_MARKET = "marketMap";
	
	/** 场景ID */
	/** 养殖场场景ID */
	public static final String SCENE_FARM = "1";
	/** 屠宰场场景ID */
	public static final String SCENE_SLAUHOUSE = "2";
	/** 分割加工场景ID */
	public static final String SCENE_DIVISION = "3";
	/** 加油站场景ID */
	public static final String SCENE_CHEERUP = "4";
	/** 仓储批发市场场景ID */
	public static final String SCENE_STORAGE = "5";
	/** 零售超市场景ID */
	public static final String SCENE_SUPERMARKET = "6";
	
	/** 出入库状态 */
	/** 出库状态 */
	public static final String STATUS_OUT_STORAGE = "1";
	
	/** 小车rfid */
	public static final String RFID_TYPE_CAR = "1";
	
	/** 司机rfid */
	public static final String RFID_TYPE_DRIVER = "2";
	
	/** 整猪rfid */
	public static final String RFID_TYPE_PIG = "3";
	
	/** 半片猪rfid */
	public static final String RFID_TYPE_HALFPIG = "4";
	
	/** 分割加工肉块rfid */
	public static final String RFID_TYPE_MEAT = "5";
}
