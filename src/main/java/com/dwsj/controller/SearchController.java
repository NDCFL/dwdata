package com.dwsj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.crawlUtil.*;
import com.dwsj.service.*;
import com.dwsj.util.GsonUtils;
import com.dwsj.util.StringUtil;
import com.dwsj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;


@Controller
@RequestMapping("search")
public class SearchController {
	
	@Autowired
	private GeneralQueryService generalQueryService;
	@Autowired
	private KeHuService keHuService;
	@Autowired
	private UserPCService userPCService;
	@Autowired
	private QueryHistoryService queryHistoryService;
	@Autowired
	private ReplaceableCookieService replaceableCookieService;
	@Resource
	private FixedPriceService fixedPriceService;
	@Resource
	private ConsumeHistoryService consumeHistoryService;

	
	
	/**
	 * 获取该客户上次的查询记录
	 * 若无数据，此新建查询
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getData")
	@ResponseBody
	public KeHuVo getData(KeHuVo keHuVo, HttpSession session) throws Exception{
		return keHuService.checkKeHu(keHuVo);
	}
	@RequestMapping("/searchData")
	@ResponseBody
	public Message searchData(KeHuVo keHuVo, HttpSession session,HttpServletRequest request,Integer handle) throws Exception{
		UserPCVo userPCVo = (UserPCVo)session.getAttribute("userPCVo");
		if(userPCVo==null){
			return Message.fail("请先登录");
		}
		KeHuVo keHu;
		try{
			keHu= keHuService.checkKeHu(keHuVo);
			if (keHu == null) {
				return Message.fail("非法查询");
			}
		}catch (Exception e){
			e.printStackTrace();
			return Message.fail("非法查询");
		}
		//0是更新查询，1是刷新查询
		if(handle==0){
			Long id = generalQueryService.getInfoById(userPCVo.getId(), keHu.getId());
			System.out.println(id+"=-====");
			if(id==null){
				return Message.success("-1");
			}else{
				return Message.success(id+"");
			}

		}else{
			Integer jifen = fixedPriceService.getById(2l).getPrice();
			if(userPCVo.getJifen()-jifen<0){
				return Message.fail("积分不足，请充值");
			}
			GeneralQueryVo history1 = generalQueryService.selectByUserIdAndKeHuId(userPCVo.getId(), keHu.getId());
			GeneralQueryVo history = new GeneralQueryVo();
			history.setCreateTime(new Date());
			history.setPcUserId(userPCVo.getId());
			history.setKehuId(keHuVo.getId());
			String wyjyCookie = replaceableCookieService.getById(7L).getCookie();
			String wyjyBaoData = WyjyBaoUtil.getWyjyBaoData1(keHuVo.getKehuName(),  keHuVo.getKehuIdcard(),keHuVo.getKehuPhone(), wyjyCookie);
			String zcjCookie = replaceableCookieService.getById(6L).getCookie();
			history.setWyjybaoData(wyjyBaoData);
			history.setSxyData(SuXinYunUtil.getData( keHuVo.getKehuIdcard()));
			history.setZcjData(ZhongChengJieUtil.getZcjData(keHuVo.getKehuName(), keHuVo.getKehuIdcard(), zcjCookie));
			history.setXysData(XinYuSouUtil.getXysDataList(keHuVo.getKehuName()).replaceAll("=",":"));
			history.setPzyData(getHtml(keHuVo.getKehuIdcard(),request));
			history.setKehuId(keHu.getId());
			history.setPcUserId(userPCVo.getId());
			ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
			consumeHistoryVo.setPcUserId(userPCVo.getId());
			consumeHistoryVo.setAmount(jifen);
			consumeHistoryVo.setCreateTime(new Date());
			consumeHistoryVo.setBcr(keHuVo.getKehuName());
			consumeHistoryVo.setIsActive((byte)0);
			consumeHistoryVo.setRemark("普通查询");
			consumeHistoryService.save(consumeHistoryVo);
			UserPCVo userPCVo1 = (UserPCVo) userPCService.getById(userPCVo.getId());
			//用总积分减去查询所需要的积分
			userPCVo1.setJifen(userPCVo1.getJifen()-jifen);
			//扣除余额
			userPCService.updateJiFen(userPCVo1);
			if(history1==null){
				generalQueryService.save(history);
				return Message.success(history.getId()+"");
			}else{
				history.setId(history1.getId());
				generalQueryService.update(history);
				return Message.success(history1.getId()+"");
			}
		}
	}
	//判断用户月
	public Integer isHaveMoney(Long userPcId,int type){
		UserPCVo userPCVo = userPCService.getById(userPcId);
		FixedPriceVo fixedPriceVo = fixedPriceService.getById(11l);
		if(userPCVo!=null && fixedPriceVo!=null && userPCVo.getJifen()-fixedPriceVo.getPrice()>0){
			return fixedPriceVo.getPrice();
		}else{
			return null;
		}
	}

	public String getHtml(String idcard,HttpServletRequest request) throws Exception{
		Long time = System.currentTimeMillis();
		String result = PingZhengYunUtil.getPzyData(idcard,replaceableCookieService.getById(1L).getCookie());
		String projectPath = request.getSession().getServletContext().getRealPath("/upload/find/"+time+".html");
		File f = new File(projectPath);
		if (!f.exists()){
			f.createNewFile();
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
		BufferedWriter writer=new BufferedWriter(write);
		String htmls = "";
		writer.write(result);
		writer.close();
		return  "/upload/find/"+time+".html";
	}

	/**
	 * 将部分无数据的平台数据转成HTML
	 * @param request
	 * @param queryId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/searchHtml",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String searchHtml1(HttpServletRequest request, Long queryId, String type) throws Exception{
		String html = "<html><body>该平台未注册或无数据</body></html>";
		GeneralQueryVo history = generalQueryService.getById(queryId);
		if ("pzy".equals(type)){
			html = StringUtil.isEmpty(history.getPzyData()) ? html : history.getPzyData();
		} else if ("qlm".equals(type)){
			html = StringUtil.isEmpty(history.getQlmData()) ? html : history.getQlmData();
		} else if ("grx".equals(type)){
			html = StringUtil.isEmpty(history.getGrxData()) ? html : history.getGrxData();
		} else if ("jjy".equals(type)){
			html = StringUtil.isEmpty(history.getJjyData()) ? html : history.getJjyData();
		}
		return html;
		
	}
	
	/**
	 * 获取信誉搜数据集合
	 * @param name
	 * @return
	 */
	@RequestMapping(value="getXysDataList",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getXysDataList(String name) {
		return XinYuSouUtil.getXysDataList(name);
	}
	
	/**
	 * 根据订单ID获取信誉搜个人详细信息
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="getXysDataByOrderId/{orderId}",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getXysDataByOrderId(@PathVariable("orderId") String orderId) throws Exception {
		return XinYuSouUtil.getXysDataByOrderId(orderId);
	}

	
}
