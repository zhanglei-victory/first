package com.microsec.ycjc.farm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.farm.cond.BreedCond;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.pojo.Vaccine;
import com.microsec.ycjc.farm.service.IBreedService;
import com.microsec.ycjc.farm.service.IFarmService;
import com.microsec.ycjc.farm.service.IInFarmService;
import com.microsec.ycjc.farm.service.IVaccineService;
import com.microsec.ycjc.util.Const;
import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.JsonList;
import com.microsec.ycjc.util.YcjcJsonUtil;

@Controller
@RequestMapping("/breed/")
public class BreedControl {

	// service
	@Resource
	private IInFarmService infarmService;
	
	@Resource
	private IVaccineService vaccineService;
	
	public IVaccineService getVaccineService() {
		return vaccineService;
	}
	public void setVaccineService(IVaccineService vaccineService) {
		this.vaccineService = vaccineService;
	}
	public IInFarmService getInfarmService() {
		return infarmService;
	}
	public void setInfarmService(IInFarmService infarmService) {
		this.infarmService = infarmService;
	}
	
	@Resource
	private IBreedService breedService;

	public IBreedService getBreedService() {
		return breedService;
	}
	public void setBreedService(IBreedService breedService) {
		this.breedService = breedService;
	}
	
	@Resource
	private IFarmService farmService;
	
	public IFarmService getFarmService() {
		return farmService;
	}
	public void setFarmService(IFarmService farmService) {
		this.farmService = farmService;
	}
	// 根据查询条件得到所有数据
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// 查询条件
		String recorddate_searchbegin = request.getParameter("recorddate_searchbegin");
		String recorddate_searchend = request.getParameter("recorddate_searchend");
		String farmid = request.getParameter("farmid");
		String rfidid = request.getParameter("rfidId");
		
		if(recorddate_searchbegin == null) {
			recorddate_searchbegin = "";
		}
		if(recorddate_searchend == null) {
			recorddate_searchend = "";
		}
		if(farmid == null) {
			farmid = "";
		}
		if(rfidid == null) {
			rfidid = "";
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
		BreedCond cond = new BreedCond();
		cond.setRecorddate_searchbegin(recorddate_searchbegin);
		cond.setRecorddate_searchend(recorddate_searchend);
		cond.setFarmid(farmid);
		cond.setRfidId(rfidid);
		
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = breedService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}

	// 得到所有的数据
	@RequestMapping("/tobreedList")
	public String toinfarmList(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		
		return "farm/breedList";
	}
	// 
	@RequestMapping("/toBatchVaccineAdd")
	public String toBatchVaccineAdd(Model model) {
		//养殖场列表
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		//疫苗列表
		List<Vaccine> vlist = vaccineService.findAllInfoList();
		model.addAttribute("vacclist", vlist);
		return "farm/batchaddVaccineBreed";
	}
	
	@RequestMapping("/toBatchOtherAdd")
	public String toBatchOtherAdd(Model model) {
		//养殖场列表
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/batchaddOtherBreed";
	}
	
	@RequestMapping("/toIllnessAdd")
	public String toIllnessAdd(Model model) {
		//养殖场列表
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		
		return "farm/addIllnessBreed";
	}
	
	//添加
	@RequestMapping("/illnessAdd")
	public void illnessAdd(HttpServletRequest request,HttpServletResponse response,Breed info) {
		Json j = new Json();
		try {
			//插入数据
			info.setRfidId(info.getRfidIdIllness());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String recorddate = sdf.format(new Date());
			info.setRecorddate(recorddate);
			
			info.setFarmid(info.getFarmidIllness());
			String content = info.getContent();
			info.setContent(content);
			breedService.insert(info);
			
			//便于显示，把养殖场内容改成汉字
			Farm farm = farmService.findInfoById(info.getFarmidIllness());
			info.setFarmid(farm.getFarmname());
			//猪的编号
			String pigno = (infarmService.findByRfid(info.getRfidIdIllness())).getPigno();
			info.setPigno(pigno);
			//设定
			j.setSuccess(true);
			j.setMsg("添加成功");
			j.setObj(info);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * 批量添加疫苗信息
	 * @param request
	 * @param response
	 * @param info
	 * @param model
	 */
	@RequestMapping("/batchVaccineAdd")
	public void batchVaccineAdd(HttpServletRequest request,HttpServletResponse response,Breed info,Model model) {
		
		JsonList j = new JsonList();
		
		//插入数据
		String farmid = info.getFarmid();
		
		//判断猪是否已经注入疫苗，注入疫苗后不允许再次注入
		String vaccid = info.getVaccid();
		BreedCond cond = new BreedCond();
		cond.setFarmid(farmid);
		cond.setVaccid(vaccid);
		List<Breed> blist = breedService.findVaccineList(cond);
		if(blist != null && blist.size() > 0) {
			j.setSuccess(false);
			j.setMsg("该疫苗已经注入，请不要重复注入!");
		} else {
			//养殖场下的所有猪信息
			List<Pig> pigsinfarmlist = infarmService.findByFarmId(farmid);
			
			List<Object> list = new ArrayList<Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//添加记录到养殖记录表
			for(Pig pig :pigsinfarmlist) {
				Breed tempbreed = new Breed();
				String rfidid = pig.getRfidId();
				String recorddate = sdf.format(new Date());
				
				String vacccontent = (vaccineService.findById(vaccid)).getVaccname();
				
				tempbreed.setVaccid(info.getVaccid());
				tempbreed.setContent(vacccontent);
				tempbreed.setFarmid(farmid);
				tempbreed.setRfidId(rfidid);
				tempbreed.setRecorddate(recorddate);
				
				list.add(tempbreed);
				
				breedService.insert(tempbreed);
			}	
			
			try {
				//设定
				j.setSuccess(true);
				j.setMsg("添加成功");
				// 重新组装显示
				for(Object tmp:list) {
					Breed tmpbreed = (Breed)tmp;
					String rfidid1 = tmpbreed.getRfidId();
					String pigno = (infarmService.findByRfid(rfidid1)).getPigno();
					tmpbreed.setPigno(pigno);
					
					//养殖场
					String farmname = (farmService.findInfoById(farmid)).getFarmname();
					tmpbreed.setFarmid(farmname);
				}
				j.setObjList(list);
			} catch (Exception e) {
				j.setSuccess(false);
				j.setMsg("添加失败");
			}
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	
	/**
	 * 批量添加其他养殖信息
	 * @param request
	 * @param response
	 * @param info
	 * @param model
	 */
	@RequestMapping("/batchOtherAdd")
	public void batchOtherAdd(HttpServletRequest request,HttpServletResponse response,Breed info,Model model) {
		
		JsonList j = new JsonList();
		
		String farmid = info.getFarmid();
		String content = info.getContent();
		
		//养殖场下的所有猪信息
		List<Pig> pigsinfarmlist = infarmService.findByFarmId(farmid);
		
		List<Object> list = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//添加记录到养殖记录表
		for(Pig pig :pigsinfarmlist) {
			Breed tempbreed = new Breed();
			
			String rfidid = pig.getRfidId();
			String recorddate = sdf.format(new Date());
			tempbreed.setContent(content);
			tempbreed.setFarmid(farmid);
			tempbreed.setRfidId(rfidid);
			tempbreed.setRecorddate(recorddate);
			
			list.add(tempbreed);
			
			breedService.insert(tempbreed);
		}	
		
		try {
			//设定
			j.setSuccess(true);
			j.setMsg("添加成功");
			// 重新组装显示
			for(Object tmp:list) {
				Breed tmpbreed = (Breed)tmp;
				String rfidid1 = tmpbreed.getRfidId();
				//猪的编号
				String pigno = (infarmService.findByRfid(rfidid1)).getPigno();
				tmpbreed.setPigno(pigno);
				
				//养殖场
				String farmname = (farmService.findInfoById(farmid)).getFarmname();
				tmpbreed.setFarmid(farmname);
			}
			j.setObjList(list);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	
	//下拉框联动取得数据
	@RequestMapping("/dynamicAddOption")
	public void dynamicAddOption(HttpServletResponse response,HttpServletRequest request) {
		String selfarmid = request.getParameter("farmid");
		JSONArray json = null;
		List<Pig> pigs = infarmService.findByFarmId(selfarmid);
		json = JSONArray.fromObject(pigs);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("***********JSON************\n" + json);
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
