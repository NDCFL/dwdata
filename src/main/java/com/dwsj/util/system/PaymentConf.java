package com.dwsj.util.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 支付配置
 * @author YuDong
 *
 */
public final class PaymentConf {
	
	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appId）
	 */
	public static String APP_ID = null;
	
	/**
	 * 微信支付分配的商户号
	 */
	public static String MCH_ID = null;
	
	/**
	 * 微信付款url
	 */
	public static String PAYMENT_URL = null;
	
	/**
	 * 签名用的key，需要和微信网站上填写的一致
	 */
	public static String PARRNER_KEY = null;
	
	public static String weixin_async_url = null;
	
	
	static {
		Properties prop = loadPropertiesFile();
		initThisFiels(prop);
	}
	
	/**
	 * 读取配置文件
	 * @return
	 */
	private static Properties loadPropertiesFile(){
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(Thread.currentThread()
					.getContextClassLoader().getResource("").getPath()
					+ "payment.properties");
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return prop;
	}
	
	/**
	 * 初始化本类的属性值(from sms.properties)
	 * @param prop
	 */
	private static void initThisFiels(Properties prop) {
		APP_ID = prop.getProperty("app_id");
		MCH_ID = prop.getProperty("mch_id");
		PAYMENT_URL = prop.getProperty("payment_url");
		PARRNER_KEY = prop.getProperty("partner_key");
		weixin_async_url = prop.getProperty("weixin_async_url");
	}
	
}
