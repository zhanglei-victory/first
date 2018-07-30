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
	// ���ݲ�ѯ�����õ���������
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
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
		
		//��ҳ����ÿҳ����
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		//��ǰҳ  
		int intPage = Integer.parseInt((page == null || page == "0") ? Const.START_PAGE_NUMBER: page);
		// ÿҳ��ʾ����
		int number = Integer.parseInt((rows == null || rows == "0") ? Const.SINGLE_PAGE_SIZE: rows);
		// ÿҳ�Ŀ�ʼ��¼ ��һҳΪ1 �ڶ�ҳΪnumber +1
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

	// �õ����е�����
	@RequestMapping("/tobreedList")
	public String toinfarmList(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		
		return "farm/breedList";
	}
	// 
	@RequestMapping("/toBatchVaccineAdd")
	public String toBatchVaccineAdd(Model model) {
		//��ֳ���б�
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		//�����б�
		List<Vaccine> vlist = vaccineService.findAllInfoList();
		model.addAttribute("vacclist", vlist);
		return "farm/batchaddVaccineBreed";
	}
	
	@RequestMapping("/toBatchOtherAdd")
	public String toBatchOtherAdd(Model model) {
		//��ֳ���б�
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/batchaddOtherBreed";
	}
	
	@RequestMapping("/toIllnessAdd")
	public String toIllnessAdd(Model model) {
		//��ֳ���б�
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		
		return "farm/addIllnessBreed";
	}
	
	//���
	@RequestMapping("/illnessAdd")
	public void illnessAdd(HttpServletRequest request,HttpServletResponse response,Breed info) {
		Json j = new Json();
		try {
			//��������
			info.setRfidId(info.getRfidIdIllness());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String recorddate = sdf.format(new Date());
			info.setRecorddate(recorddate);
			
			info.setFarmid(info.getFarmidIllness());
			String content = info.getContent();
			info.setContent(content);
			breedService.insert(info);
			
			//������ʾ������ֳ�����ݸĳɺ���
			Farm farm = farmService.findInfoById(info.getFarmidIllness());
			info.setFarmid(farm.getFarmname());
			//��ı��
			String pigno = (infarmService.findByRfid(info.getRfidIdIllness())).getPigno();
			info.setPigno(pigno);
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			j.setObj(info);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * �������������Ϣ
	 * @param request
	 * @param response
	 * @param info
	 * @param model
	 */
	@RequestMapping("/batchVaccineAdd")
	public void batchVaccineAdd(HttpServletRequest request,HttpServletResponse response,Breed info,Model model) {
		
		JsonList j = new JsonList();
		
		//��������
		String farmid = info.getFarmid();
		
		//�ж����Ƿ��Ѿ�ע�����磬ע������������ٴ�ע��
		String vaccid = info.getVaccid();
		BreedCond cond = new BreedCond();
		cond.setFarmid(farmid);
		cond.setVaccid(vaccid);
		List<Breed> blist = breedService.findVaccineList(cond);
		if(blist != null && blist.size() > 0) {
			j.setSuccess(false);
			j.setMsg("�������Ѿ�ע�룬�벻Ҫ�ظ�ע��!");
		} else {
			//��ֳ���µ���������Ϣ
			List<Pig> pigsinfarmlist = infarmService.findByFarmId(farmid);
			
			List<Object> list = new ArrayList<Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//��Ӽ�¼����ֳ��¼��
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
				//�趨
				j.setSuccess(true);
				j.setMsg("��ӳɹ�");
				// ������װ��ʾ
				for(Object tmp:list) {
					Breed tmpbreed = (Breed)tmp;
					String rfidid1 = tmpbreed.getRfidId();
					String pigno = (infarmService.findByRfid(rfidid1)).getPigno();
					tmpbreed.setPigno(pigno);
					
					//��ֳ��
					String farmname = (farmService.findInfoById(farmid)).getFarmname();
					tmpbreed.setFarmid(farmname);
				}
				j.setObjList(list);
			} catch (Exception e) {
				j.setSuccess(false);
				j.setMsg("���ʧ��");
			}
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	
	/**
	 * �������������ֳ��Ϣ
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
		
		//��ֳ���µ���������Ϣ
		List<Pig> pigsinfarmlist = infarmService.findByFarmId(farmid);
		
		List<Object> list = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//��Ӽ�¼����ֳ��¼��
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
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			// ������װ��ʾ
			for(Object tmp:list) {
				Breed tmpbreed = (Breed)tmp;
				String rfidid1 = tmpbreed.getRfidId();
				//��ı��
				String pigno = (infarmService.findByRfid(rfidid1)).getPigno();
				tmpbreed.setPigno(pigno);
				
				//��ֳ��
				String farmname = (farmService.findInfoById(farmid)).getFarmname();
				tmpbreed.setFarmid(farmname);
			}
			j.setObjList(list);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	
	//����������ȡ������
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
