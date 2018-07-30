package com.microsec.ycjc.farm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.microsec.traceSocket.webservice.ClientThread;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.service.IFarmService;
import com.microsec.ycjc.farm.service.IInFarmService;
import com.microsec.ycjc.farm.service.IOutFarmService;
import com.microsec.ycjc.util.Const;
import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.YcjcJsonUtil;

@Controller
@RequestMapping("/outfarm/")
public class OutFarmControl {

	// service
	@Resource
	private IOutFarmService outfarmService;

	public IOutFarmService getOutfarmService() {
		return outfarmService;
	}
	public void setOutfarmService(IOutFarmService outfarmService) {
		this.outfarmService = outfarmService;
	}
	@Resource
	private IFarmService farmService;
	
	public IFarmService getFarmService() {
		return farmService;
	}
	public void setFarmService(IFarmService farmService) {
		this.farmService = farmService;
	}
	
	@Resource
	private IInFarmService infarmService;
	
	public IInFarmService getInfarmService() {
		return infarmService;
	}
	public void setInfarmService(IInFarmService infarmService) {
		this.infarmService = infarmService;
	}
	// 根据查询条件得到所有数据
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// 查询条件
		String outfarmdate_searchbegin = request.getParameter("outfarmdate_searchbegin");
		String outfarmdate_searchend = request.getParameter("outfarmdate_searchend");
		String farmid_search = request.getParameter("farmid_search");
		String permitoutfarm = request.getParameter("permitoutfarm");
		
		if(outfarmdate_searchbegin == null) {
			outfarmdate_searchbegin = "";
		}
		if(outfarmdate_searchend == null) {
			outfarmdate_searchend = "";
		}
		if(farmid_search == null) {
			farmid_search = "";
		}
		if(permitoutfarm == null) {
			permitoutfarm = "";
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
		
		PigCond cond = new PigCond();
		cond.setOutfarmdate_searchbegin(outfarmdate_searchbegin);
		cond.setOutfarmdate_searchend(outfarmdate_searchend);
		cond.setFarmid_search(farmid_search);
		cond.setPermitoutfarm(permitoutfarm);
		
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = outfarmService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}

	// 得到所有的数据
	@RequestMapping("/tooutfarmList")
	public String tooutfarmList(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/outfarmList";
	}

	//跳转到添加页面
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "farm/addoutFarm";
	}
	
	// 跳转到修改页面
	@RequestMapping("/toUpdate/{id}")
	public String toUpdate(@PathVariable(value="id") String infoId,Model model) {
		// 得到当前
		Pig tmpObj = outfarmService.findByRfid(infoId);
		model.addAttribute("outfarmForModify", tmpObj);
		
		return "farm/updateoutFarm";
	}

	// 修改
	@RequestMapping("/addOutFarm")
	public void addOutFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		//修改
		Json j = new Json();
		try {
			//出栏时间为当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String outfarmdate = sdf.format(new Date());
			info.setOutfarmdate(outfarmdate);
			//修改数据
			outfarmService.update(info);
			//便于显示，把养殖场内容改成汉字
			Farm farm = farmService.findInfoById(info.getFarmid());
			info.setFarmid(farm.getFarmname());
			//便于显示，将性别以汉字显示
			String sex = info.getPigsex();
			if(sex.equals("1")) {
				info.setPigsex("公");
			} else if(sex.equals("0")) {
				info.setPigsex("母");
			}
			
			//设定
			j.setSuccess(true);
			j.setMsg("出栏添加成功");
			j.setObj(info);
			//下发命令（显示入栏小猪相关信息）
			//{“cmd”:”set_lcd”,”args”:{“device_id”:06,”device_value”:”种类,重量,产地/姓名,性别,年龄”}}
			//内容形式：字符串+“，”分割
			//种类，重量，产地/健康状况
			//实例：长白猪，100kg，来自济南/健康（入栏显示产地、出栏显示健康状况）
			//小明，男，25岁
			StringBuffer sendmsgBuffer = new StringBuffer();
			sendmsgBuffer.append(info.getPigtype()).append(",").append(info.getOutfarmweight()).append(",").
				append(info.getHealthstatus());

			//组织下发字符串
			String sendmsg = "{\"seq_no\":\"111\",\"cmd\":\"set_lcd\",\"args\":{\"device_id\":\"06\",\"device_value\":\""+sendmsgBuffer.toString()+"\"}}\n";
			System.out.println(sendmsg);
			ClientThread thread = new ClientThread();
			thread.send(sendmsg);
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("出栏添加失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
		
	// 修改
	@RequestMapping("/updateOutFarm")
	public void updateOutFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		//修改
		Json j = new Json();
		try {
			//修改数据
			outfarmService.update(info);
			//便于显示，把养殖场内容改成汉字
			Farm farm = farmService.findInfoById(info.getFarmid());
			info.setFarmid(farm.getFarmname());
			//便于显示，将性别以汉字显示
			String sex = info.getPigsex();
			if(sex.equals("1")) {
				info.setPigsex("公");
			} else if(sex.equals("0")) {
				info.setPigsex("母");
			}
			
			//设定
			j.setSuccess(true);
			j.setMsg("修改成功");
			j.setObj(info);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * 验证是否出栏
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkOutfarm")
	public void checkOutfarm(HttpServletRequest request,HttpServletResponse response) {
		String rfidid = request.getParameter("rfidId");
		//判断猪是否在饲养中
		Pig pig = outfarmService.checkOutfarm(rfidid);
		try {
			if(pig != null) {
				response.getWriter().print("false");
			} else {
				response.getWriter().print("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 删除
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable(value="id") String infoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			//删除
			outfarmService.delete(infoId);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * 根据rfid得到小猪相关项目
	 * @param response
	 * @param request
	 */
	@RequestMapping("/dynamicAddInFarmContent")
	public void dynamicAddInFarmContent(HttpServletResponse response,HttpServletRequest request) {
		String rfidid = request.getParameter("rfidid");
		Pig tmpObj;
		JSONArray json = null;
		
		Pig inout = null;
		if(rfidid != null && !rfidid.equals("")) {
			tmpObj = outfarmService.findByRfid(rfidid);
			if(tmpObj != null) {
				String permitoutfarm = tmpObj.getPermitoutfarm();
				//未出栏
				if(!"1".equals(permitoutfarm)) {
					inout = infarmService.findByRfid(rfidid);
				}
			}
		}
		json = JSONArray.fromObject(inout);
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