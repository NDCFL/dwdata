package com.dwsj.vo;

/**
 * 返回结果码
 * @author cgw
 * @date 2017年8月22日
 */
public final class ResultCode {
	public static final int OK = 100;					// 成功

	public static final int ERROR = 101;				// 失败
	
	// code
	public static final int PARAM_NULL = 102;			// 参数为空
	public static final int PARAM_FMT_ERR = 103;		// 参数格式错误
	public static final int DATA_RES_NOT_EXISTS = 104;	// 资源不存在
	public static final int PHONE_HAD_REGISTERED = 105;	// 手机已注册
	public static final int EMAIL_HAD_REGISTERED = 106;	// 邮箱已注册
	public static final int FILE_UPLOAD_FAILED = 107;	// 文件上传失败
	public static final int USER_NOT_REGISTER = 108;	// 用户未注册
	public static final int FORGET_PSW_SET_EXPIRE = 109;	// 重设密码超时
	public static final int SERVER_INNER_ERROR = 110;	// 服务器内部错误
	public static final int REMOTE_SERVER_ERROR = 111;	// 远程服务错误(具体根据response的code和data来做业务处理)
	public static final int PAY_SIGN_ERROR = 112;	// 支付-验签失败
	public static final int PAY_SIGN_EXCEPTION = 112;	// 支付-验签异常
	public static final int PAY_GATEWAY_NOT_EXSITS = 113;	// 支付网关不存在
	public static final int PAY_PASSWORD_ERROR = 114;	// 支付密码错误
	public static final int WITHDRAW_APPLY_COMMITED = 115;	// 提现申请已提交
	public static final int HANDLING = 116;	// 处理中
	public static final int PAY_NOT_ENOUGH = 117;	// 余额不足
	public static final int PAY_SUCCESSED_BUT_NOT_RECORD = 118;	// 支付成功,但创建交易记录失败
	public static final int PAY_PASSWORD_NOT_SETTINGS = 119;	// 未设置支付密码
	public static final int AUDIT_NOT_PASS = 220;	// 审核未通过
	public static final int ORDER_ID_NULL = 221;	// 订单号为空
	public static final int USER_NOT_EXISTS = 222;	// 用户不存在
	
	public static final int RED_BOMB_FULLED = 223;	// 红包炸弹已满
	public static final int RED_BOMB_CAN_NOT_PATICIPANT = 224;	// 当天不能再玩红包炸弹
	public static final int RED_BOMB_ALREADY_PATICIPANTED = 225;	// 已经投入过该红包炸弹
	public static final int RED_BOMB_BECOME_DETONATOR = 226;	// 成为引爆者
	public static final int RED_BOMB_NOT_DETONATOR_FULLED = 227;	// 未成为为引爆者(炸弹已满)
	public static final int RED_BOMB_NOT_FULLED = 228;	// 炸弹未满
	public static final int RED_BOMB_SEASON_OLDED = 229;	// 该场红包炸弹已经过期,请投下一场
	
	public static final int APP_TOKEN_EXPIRED = 230;	// app的token已过期
	public static final int APP_TOKEN_NULL = 231;	// app端发送过来的token为空
	public static final int REPEAT_REQUEST = 232;	// 重复请求
	
	// msg
	public static final String PARAM_NULL_MSG = "参数不能为空";
	public static final String PARAM_FMT_ERR_MSG = "参数格式错误";
	public static final String DATA_RES_NOT_EXISTS_MSG = "资源不存在";
	public static final String PHONE_HAD_REGISTERED_MSG = "该手机号码已注册";
	public static final String EMAIL_HAD_REGISTERED_MSG = "该邮箱已注册";
	public static final String FILE_UPLOAD_FAILED_MSG = "文件上传失败";
	public static final String USER_NOT_REGISTER_MSG = "用户未注册";
	public static final String FORGET_PSW_SET_EXPIRE_MSG = "重设密码超时";
	public static final String SERVER_INNER_ERROR_MSG = "服务器内部错误";
	public static final String REMOTE_SERVER_ERROR_MSG = "远程服务错误";
	public static final String PAY_SIGN_ERROR_MSG = "验签失败";
	public static final String PAY_SIGN_EXCEPTION_MSG = "验签异常";
	public static final String PAY_GATEWAY_NOT_EXSITS_MSG = "支付网关不存在";
	public static final String PAY_PASSWORD_ERROR_MSG = "支付密码错误";
	public static final String WITHDRAW_APPLY_COMMITED_MSG = "提现申请已提交";
	public static final String HANDLING_MSG = "业务处理中";
	public static final String PAY_NOT_ENOUGH_MSG = "余额不足";
	public static final String PAY_SUCCESSED_BUT_NOT_RECORD_MSG = "支付成功,但创建交易记录失败";
	public static final String PAY_PASSWORD_NOT_SETTINGS_MSG = "未设置支付密码";
	public static final String AUDIT_NOT_PASS_MSG = "审核未通过";
	public static final String ORDER_ID_NULL_MSG = "订单号为空";
	public static final String USER_NOT_EXISTS_MSG = "用户不存在";
	public static final String RED_BOMB_FULLED_MSG = "红包炸弹已满";
	public static final String RED_BOMB_CAN_NOT_PATICIPANT_MSG = "当天不能再玩红包炸弹";
	public static final String RED_BOMB_ALREADY_PATICIPANTED_MSG = "已经投入过该红包炸弹";
	public static final String RED_BOMB_SEASON_OLDED_MSG = "该场红包炸弹已经过期,请投下一场";
	public static final String APP_TOKEN_EXPIRED_MSG = "token已过期";
	public static final String APP_TOKEN_NULL_MSG = "token不能为空";
	public static final String REPEAT_REQUEST_MSG = "重复请求";
	
}
