package com.dwsj.util.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统配置
 * @author YuDong
 *
 */
public final class SysConf {
	
	/**
	 * 项目地址
	 */
	public static String BASE_PATH = null;
	
	/**
	 * 黑爬虫api-url
	 */
	public static String HPC_API_URL = null;

	
	/**
	 * 黑爬虫api-key
	 */
	public static String HPC_API_KEY = null;

	/**
	 * 诺正通 商户id
	 */
	public static String NZT_MALL_ID = null;
	
	/**
	 * 诺正通 appkey
	 */
	public static String NZT_APP_KEY = null;
	
	
	/**
	 * 诚为信url
	 */
	public static String CWX_URL = null;
	
	/**
	 * 诚为信的code，放入header内
	 */
	public static String CWX_CODE = null;
	
	/**
	 * 诚为信的key，放入header内
	 */
	public static String CWX_KEY = null;
	
	/**
	 * 诚为信的vip key
	 */
	public static String CWX_VIP_KEY = null;
	
	/**
	 * 诚为信的vip code
	 */
	public static String CWX_VIP_CODE = null;
	
	/**
	 * 手机号识别用户ID
	 */
	public static String PHONE_RECOGNITION_USERID = null;
	
	/**
	 * 手机号识别运营商、标记url
	 */
	public static String PHONE_RECOGNITION_URL = null;
	
	/**
	 * 手机号识别apikey
	 */
	public static String PHONE_RECOGNITION_KEY = null;
	
	/**
	 * 手机号定位key
	 */
	public static String LBS_POSITION_KEY = null;
	
	/**
	 * 手机号定位secret
	 */
	public static String LBS_POSITION_SECRET = null;
	
	/**
	 * 手机号定位appcode
	 */
	public static String LBS_APP_CODE = null;
	
	/**
	 * 置信帐号
	 */
	public static String ZX_ACCOUNT = null;
	
	/**
	 * 置信key，QQ通讯录使用
	 */
	public static String ZX_APP_KEY = null;
	
	/**
	 * 置信zx_app_secret，QQ通讯录使用
	 */
	public static String ZX_APP_SECRET = null;
	
	public static String PYTHON_LOCAL_PATH = "C:/python/diwangshuju/";
	
	/**
	 * 电话邦-appid
	 */
	public static String dhb_appid = null;
	/**
	 * 电话邦-密钥
	 */
	public static String dhb_appsecret = null;
	/**
	 * 电话邦-授权回调
	 */
	public static String dhb_auth_callback = null;
	
	/**
	 * 速信云/速信记账服务uid
	 */
	public static String SXY_UID = null;
	/**
	 * 速信云/速信记账服务token
	 */
	public static String SXY_TOKEN = null;
	
	
	/**
	 * 系统运行环境配置
	 */
	public static final String system_run_conf_model_db = "DB";
	public static final String system_run_conf_model_local = "LOCAL";
	public static String system_run_conf_model = system_run_conf_model_db;	// as default
	
	public static final int file_type_other = 0;	// 其他文件类型
	public static final int file_type_img = 1;		// 图片文件类型
	public static final int file_type_sound = 2;	// 音频文件类型
	public static final int file_type_video = 3;	// 视频文件类型
	public static String domain_name = null;		// 对面网域名
	public static String upload_server_name = null;	// 文件服务器名
	public static String upload_path = null;	// 文件上传绝对路径
	public static String img_rel_path = null;	// 图片上传的相对路径
	public static String sound_rel_path = null;	// 音频上传的相对路径
	public static String video_rel_path = null;	// 视频上传的相对路径
	public static String other_rel_path = null;	// 其他上传的相对路径
	public static String system_rel_path = null;// 文件服务器-系统文件保存的相对路径
	public static String upload_url = null;		// 文件对外发布的URL
	public static String img_rel_url = null;	// 图片对外发布的URL
	public static String sound_rel_url = null;	// 音频对外发布的URL
	public static String video_rel_url = null;	// 视频对外发布的URL
	public static String other_rel_url = null;	// 其他对外发布的URL
	public static String system_rel_url = null;	// 文件服务器-系统文件发布的URI
//	static {
//		Properties prop = loadPropertiesFile();
//		initThisFiels(prop);
//	}
//
//	/**
//	 * 读取配置文件
//	 * @return
//	 */
//	private static Properties loadPropertiesFile(){
//		Properties prop = new Properties();
//		InputStream in = null;
//		try {
//			in = new FileInputStream(Thread.currentThread()
//					.getContextClassLoader().getResource("").getPath()
//					+ "system.properties");
//			prop.load(in);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return prop;
//	}
//
//	/**
//	 * 初始化本类的属性值(from sms.properties)
//	 * @param prop
//	 */
//	private static void initThisFiels(Properties prop) {
//		//IS_ENCRYPT_PWD = Boolean.valueOf(prop.getProperty("IS_ENCRYPT_PWD"));
//		//prop.getProperty("sms_check_code_time_out");
//		BASE_PATH = prop.getProperty("base_path");
//
//		HPC_API_URL = prop.getProperty("hpc_api_url");
//		HPC_API_KEY = prop.getProperty("hpc_api_key");
//
//		NZT_MALL_ID = prop.getProperty("nzt_mall_id");
//		NZT_APP_KEY = prop.getProperty("nzt_app_key");
//
//		CWX_URL = prop.getProperty("cwx_url");
//		CWX_KEY = prop.getProperty("cwx_key");
//		CWX_CODE = prop.getProperty("cwx_code");
//		CWX_VIP_KEY = prop.getProperty("cwx_vip_key");
//		CWX_VIP_CODE = prop.getProperty("cwx_vip_code");
//
//		PHONE_RECOGNITION_URL = prop.getProperty("phone_recognition_url");
//		PHONE_RECOGNITION_USERID = prop.getProperty("phone_recognition_userid");
//		PHONE_RECOGNITION_KEY = prop.getProperty("phone_recognition_key");
//
//		LBS_POSITION_KEY = prop.getProperty("lbs_position_key");
//		LBS_POSITION_SECRET = prop.getProperty("lbs_position_secret");
//		LBS_APP_CODE = prop.getProperty("lbs_app_code");
//
//		ZX_ACCOUNT = prop.getProperty("zx_account");
//		ZX_APP_KEY = prop.getProperty("zx_app_key");
//		ZX_APP_SECRET = prop.getProperty("zx_app_secret");
//
//		SXY_UID = prop.getProperty("sxy_uid");
//		SXY_TOKEN = prop.getProperty("sxy_token");
//
//		system_run_conf_model = prop.getProperty("system_run_conf_model").toUpperCase();
//		upload_server_name = prop.getProperty("upload_server_name");
//		domain_name = prop.getProperty("domain_name");
//		upload_path = prop.getProperty("upload_path");
//		img_rel_path = prop.getProperty("img_rel_path");
//		sound_rel_path = prop.getProperty("sound_rel_path");
//		video_rel_path = prop.getProperty("video_rel_path");
//		other_rel_path = prop.getProperty("other_rel_path");
//		system_rel_path = prop.getProperty("system_rel_path");
//		upload_url = prop.getProperty("upload_url");
//		img_rel_url = prop.getProperty("img_rel_url");
//		sound_rel_url = prop.getProperty("sound_rel_url");
//		video_rel_url = prop.getProperty("video_rel_url");
//		other_rel_url = prop.getProperty("other_rel_url");
//		system_rel_url = prop.getProperty("system_rel_url");
//
////		PYTHON_LOCAL_PATH = prop.getProperty("python_local_path");
//		dhb_appid = prop.getProperty("dhb_appid");
//		dhb_appsecret = prop.getProperty("dhb_appsecret");
//		dhb_auth_callback = prop.getProperty("dhb_auth_callback");
//	}
	
}
