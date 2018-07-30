package com.microsec.ycjc.farm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.microsec.traceSocket.webservice.ClientThread;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.farm.cond.FarmCond;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.service.IFarmService;
import com.microsec.ycjc.farm.service.IInFarmService;
import com.microsec.ycjc.user.pojo.UserInfo;
import com.microsec.ycjc.util.Const;
import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.YcjcJsonUtil;

@Controller
@RequestMapping("/farm/")
public class FarmControl {

	//创建输出日志记录器
	public static Logger infoLog=Logger.getLogger( FarmControl.class.getName());
	@Resource
	private IFarmService farmService;

	@Resource
	private IInFarmService infarmService;

	public IInFarmService getInfarmService() {
		return infarmService;
	}

	public void setInfarmService(IInFarmService infarmService) {
		this.infarmService = infarmService;
	}

	public IFarmService getFarmService() {
		return farmService;
	}

	public void setFarmService(IFarmService farmService) {
		this.farmService = farmService;
	}
	// toGisMonitoring
	@RequestMapping("/toGisMonitoring")
	public String toGisMonitoring(HttpSession session) {
		return "farm/gisMonitoring";
	}
	// 根据查询条件得到所有数据
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// 查询条件
		String name = request.getParameter("search_farm_name");
		String licenseid=request.getParameter("licenseid");
		if(name == null) {
			name = "";
		}
		if(licenseid == null) {
			licenseid = "";
		}
		//分页及其每页条数
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//当前页  
		int intPage = Integer.parseInt((page == null || page == "0") ? Const.START_PAGE_NUMBER: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? Const.SINGLE_PAGE_SIZE: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;
		FarmCond cond = new FarmCond();
		cond.setFarmname(name);
		cond.setStart(start);
		cond.setSinglePagesize(number);
	//	cond.setLicenseid(licenseid);
		
		DataGrid d = farmService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}
	// 得到所有的数据
//	@RequestMapping("/toFarmList")
//	public String toFarmList(Model model,HttpSession session) {
//		UserInfo user = (UserInfo) session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
//		
//		List<Farm> farm = farmService.findAllInfoByCondd();
//		//Breed_Condition bc = formatDate();
//		List<Farm> farm_new = new ArrayList<Farm>();
//		if(!"".equals(user.getUserlicenseid())){
//			for(Farm f:farm){
//				if(user.getUserlicenseid().equals(f.getLicenseid())){
//					farm_new.add(f);
//				}
//			}
//			
//		}else{
//			Farm f = new Farm();
//			f.setLicenseid("");
//			f.setFarmname("全部养殖场");
//			farm_new.add(f);
//			farm_new.addAll(farm);
//		}
//		model.addAttribute("farm", farm_new);
//		//model.addAttribute("searchbegin", bc.getSearchbegin());
//		//model.addAttribute("searchend", bc.getSearchend());
//		
//		return "farm/farmList";
//	}
	//添加
	@RequestMapping("/addFarm")
	public void addFarm(HttpServletRequest request,HttpServletResponse response,Farm farm) {
		Json j = new Json();
		try {
			//插入数据
			farmService.insertInfo(farm);
			//设定
			j.setSuccess(true);
			j.setMsg("添加成功");
			j.setObj(farm);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//跳转到添加页面
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "farm/addFarm";
	}
	
	// 跳转到修改页面
	@RequestMapping("/toUpdate/{id}")
	public String toUpdate(@PathVariable(value="id") String farmId,Model model,HttpSession session) {
		
		UserInfo user = (UserInfo) session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		model.addAttribute("user", user);

		// 得到当前
		Farm tmpObj = farmService.findInfoById(farmId);
		model.addAttribute("farmForModify", tmpObj);
		return "farm/updateFarm";
	}
	// 跳转到设置阈值页面
//	@RequestMapping("/toSetMaxmin/{id}/{type}")
//	public String toSetMaxmin(@PathVariable(value="id") String farmId,@PathVariable(value="type") String typeId,Model model) {
//		Map<String,String> paramMap = new HashMap<String,String>();
//		paramMap.put("sceneid", farmId);
//		paramMap.put("scenetype", typeId);
//		
//		SceneObj obj = sceneobjService.findInfoByIdAndType(paramMap);
//		if(obj != null) {
//		} else {
//			obj = new SceneObj();
//			// 得到当前
//			Farm tmpObj = farmService.findInfoById(farmId);
//			obj.setSceneid(tmpObj.getFarmid());
//			obj.setScenetype(Const.SCENE_FARM);
//		}
//		
//		model.addAttribute("info", obj);
//		return "farm/maxminFarm";
//	}
	// 修改
//	@RequestMapping("/sceneUpdateFarm")
//	public void sceneUpdateFarm(HttpServletRequest request,HttpServletResponse response) {
//		//修改当前
//		Json j = new Json();
//		
//		String tempMin = obj.getTempmin();
//		String tempMax = obj.getTempmax();
//		
//		String humidityMin = obj.getHumiditymin();
//		String humidityMax = obj.getHumiditymax();
//		
//		String lightMin = obj.getLightmin();
//		String lightMax = obj.getLightmax();
//		
//		Float tempminfloat = "".equals(tempMin)?0.0f:Float.parseFloat(tempMin);
//		Float tempmaxfloat = "".equals(tempMax)?0.0f:Float.parseFloat(tempMax);
//		
//		Float humidityminfloat = "".equals(humidityMin)?0.0f:Float.parseFloat(humidityMin);
//		Float humiditymaxfloat = "".equals(humidityMax)?0.0f:Float.parseFloat(humidityMax);
//		
//		Float lightminfloat = "".equals(lightMin)?0.0f:Float.parseFloat(lightMin);
//		Float lightmaxfloat = "".equals(lightMax)?0.0f:Float.parseFloat(lightMax);
//		try {
//			//最小阈值 < 最大阈值
//			if(tempminfloat < tempmaxfloat && humidityminfloat < humiditymaxfloat && lightminfloat < lightmaxfloat) {
//				Map<String,String> paramMap = new HashMap<String,String>();
//				paramMap.put("sceneid", obj.getSceneid());
//				paramMap.put("scenetype", obj.getScenetype());
//				// 查找数据是否存在
////				if(sObj != null) {
////					//修改数据
////					//设定
////					j.setSuccess(true);
////					j.setMsg("修改设置成功");
////					j.setObj(sObj);
////					
////					//同时下发命令给中间件，让其重新得到场景map结构,便于阈值开关联动
////					String sendmsg = "{\"cmd\":\"set_maxmin_openflag\"}\n";
////					infoLog.info("养殖场阈值设置：".concat(sendmsg));
////					ClientThread thread = new ClientThread();
////					thread.send(sendmsg);
////				} else {
////					j.setSuccess(true);
////					j.setMsg("添加设置成功");
////					j.setObj(obj);
////				}
//			} else {
//				j.setSuccess(false);
//				j.setMsg("阈值设置不符合条件，应该按照以下规则进行设置<br>其中： 最小阈值 < 最大阈值");
//				infoLog.info("阈值设置不符合条件，应该按照以下规则进行设置<br>其中： 最小阈值 < 最大阈值");
//			}
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("操作失败");
//		}
//		
//		YcjcJsonUtil.writeJson(j, response);
//	}
	// 跳转到修改页面
	@RequestMapping("/toDetail/{id}")
	public String toDetail(@PathVariable(value="id") String farmId,Model model) {
		// 得到当前
		Farm tmpObj = farmService.findInfoById(farmId);
		model.addAttribute("farmForDetail", tmpObj);
		return "farm/detailFarm";
	}

//	// 修改
//	@RequestMapping("/updateFarm")
//	public void updateFarm(HttpServletRequest request,HttpServletResponse response,Farm farm) {
//		//修改当前
//		Json j = new Json();
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String updatetime = sdf.format(new Date());
//			farm.setUpddatetime(updatetime);
//			//修改数据
//			farmService.updateInfo(farm);
//			//设定
//			j.setSuccess(true);
//			j.setMsg("修改成功");
//			j.setObj(farm);
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("修改失败");
//		}
//		
//		YcjcJsonUtil.writeJson(j, response);
//	}
//	// 删除
//	@RequestMapping("/deleteFarm/{id}")
//	public void deleteFarm(@PathVariable(value="id") String licenseId,HttpServletResponse response) {
//		Json j = new Json();
//		try {
//			Farm farm = farmService.findByLicenseId(licenseId);
//			String farmid = farm.getFarmid();
//			//删除
//			farmService.deleteInfo(farmid);
//			
//			//删除阈值设置表
////			Map<String,String> paramMap = new HashMap<String, String>();
////			paramMap.put("sceneid", farmid);
////			paramMap.put("scenetype", Const.SCENE_FARM);
////			sceneobjService.delete(paramMap);
//			j.setSuccess(true);
//			j.setMsg("删除成功");
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("删除失败");
//		}
//		YcjcJsonUtil.writeJson(j, response);
//	}
//	// 删除
//	@RequestMapping("/deleteFarmCheck/{id}")
//	public void deleteFarmCheck(@PathVariable(value="id") String sceneseid,HttpServletResponse response) {
//		//判断是否被用户使用
////		List<Pig> list = infarmService.findByLicenseId(sceneseid);
//		try {
//			if(list != null) {
//				if (list.size() > 0) {
//					response.getWriter().print("false");
//				} else {
//					response.getWriter().print("true");
//				}
//			} else {
//				response.getWriter().print("true");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	// 验证名是否被占用
	@RequestMapping("/checkFarmName")
	public void checkFarmName(HttpServletRequest request,HttpServletResponse response) {
		// 得到登录名
		String farmname = request.getParameter("farmname");
		List<Farm> list = farmService.findAllInfoList();
		//默认验证通过
		String flag = "true";
		try {
			if (list != null) {
				for(Farm farm:list) {
					if(farm.getFarmname().equals(farmname)) {
						flag = "false";
						break;
					}
				}
				if(flag.equals("false")) {
					response.getWriter().print("false");
				} else if(flag.equals("true")) {
					response.getWriter().print("true");
				}
			} else {
				response.getWriter().print("true");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param infoid
	 * @param switchflag
	 * @param response
	 */
	@RequestMapping("/openAndCloseLight/{id}/{switchid}")
	public void openAndCloseLight(@PathVariable(value="id") String infoid,@PathVariable(value="switchid") String switchflag,HttpServletResponse response) {
		//修改当前
		Json j = new Json();
		try {
			String strflag = "";
			if("1".equals(switchflag)) {
				strflag = "true";
			} else if("0".equals(switchflag)) {
				strflag = "false";
			}
			//组织下发字符串
			String sendmsg = "{\"seq_no\":\"100\",\"cmd\":\"set_switch\",\"args\":{\"device_id\":2,\"device_type\":24,\"device_value\":\""+strflag+"\"}}\n";
			infoLog.info("养殖场下发开关灯命令：".concat(sendmsg));
			
			ClientThread thread = new ClientThread();
			boolean flag = thread.send(sendmsg);
			if(flag) {
				//修改开关灯状态
				Farm tmpfarm = farmService.findInfoById(infoid);
				if(tmpfarm != null) {
					if("1".equals(switchflag)) {
						tmpfarm.setLightstatus("1");
					} else if("0".equals(switchflag)) {
						tmpfarm.setLightstatus("0");
					}
					farmService.updateInfo(tmpfarm);
				}
				//设定
				j.setSuccess(true);
				j.setMsg("操作成功");
				infoLog.info("养殖场下发开关灯命令成功");
			} else {
				j.setSuccess(false);
				j.setMsg("操作失败");
				infoLog.info("养殖场下发开关灯命令失败");
			}
			System.out.println(flag);
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("操作失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * 
	 * @param infoid
	 * @param switchflag
	 * @param response
	 */
	@RequestMapping("/openAndCloseFan/{id}/{switchid}")
	public void openAndCloseFan(@PathVariable(value="id") String infoid,@PathVariable(value="switchid") String switchflag,HttpServletResponse response) {
		//修改当前
		Json j = new Json();
		try {
			String strflag = "";
			if("1".equals(switchflag)) {
				strflag = "true";
			} else if("0".equals(switchflag)) {
				strflag = "false";
			}
			//组织下发字符串
			String sendmsg = "{\"seq_no\":\"101\",\"cmd\":\"set_switch\",\"args\":{\"device_id\":3,\"device_type\":24,\"device_value\":\""+strflag+"\"}}\n";
			infoLog.info("养殖场下发开关风扇命令：".concat(sendmsg));
			
			ClientThread thread = new ClientThread();
			boolean flag = thread.send(sendmsg);
			if(flag) {
				//修改开关风扇状态
				Farm tmpfarm = farmService.findInfoById(infoid);
				if(tmpfarm != null) {
					if("1".equals(switchflag)) {
						tmpfarm.setFanstatus("1");
					} else if("0".equals(switchflag)) {
						tmpfarm.setFanstatus("0");
					}
					farmService.updateInfo(tmpfarm);
				}
				//设定
				j.setSuccess(true);
				j.setMsg("操作成功");
				infoLog.info("养殖场下发开关风扇命令成功");
			} else {
				j.setSuccess(false);
				j.setMsg("操作失败");
				infoLog.info("养殖场下发开关风扇命令失败");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("操作失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// 查找所有的养殖场信息并返回json格式供客户端解析使用
	@RequestMapping("/getAllFarmPoint")
	public void getAllFarmPoint(HttpServletResponse response,HttpServletRequest request) {
		System.out.println("--- map get allfarm point data begin  ---");
//		HttpSession session = request.getSession();
//		UserInfo user = (UserInfo)session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
//		String licenseid = "";
//		if(user != null) {
//			//licenseid = user.getUserlicenseid();
//			if(licenseid == null) {
//				licenseid = "";
//			}
//		}
		JSONArray json = null;
//		FarmCond cond = new FarmCond();
	//	cond.setLicenseid(licenseid);
		List<Farm> list = farmService.findAllInfoList();
		
		json = JSONArray.fromObject(list);
		System.out.println("--- map get allfarm point data end  ---" + json);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("***********JSON************\n" + json);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
