package com.microsec.ycjc.farm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.microsec.traceSocket.webservice.ClientThread;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.service.IFarmService;
import com.microsec.ycjc.farm.service.IInFarmService;
import com.microsec.ycjc.util.Const;
import com.microsec.ycjc.util.DataGrid;
import com.microsec.ycjc.util.Json;
import com.microsec.ycjc.util.YcjcJsonUtil;

@Controller
@RequestMapping("/infarm/")
public class InFarmControl {

	// service
	@Resource
	private IInFarmService infarmService;

	@Resource
	private IFarmService farmService;
	
	public IFarmService getFarmService() {
		return farmService;
	}
	public void setFarmService(IFarmService farmService) {
		this.farmService = farmService;
	}
	public IInFarmService getInfarmService() {
		return infarmService;
	}
	public void setInfarmService(IInFarmService infarmService) {
		this.infarmService = infarmService;
	}
	// ���ݲ�ѯ�����õ���������
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
		String pigbirthday_searchbegin = request.getParameter("pigbirthday_searchbegin");
		String pigbirthday_searchend = request.getParameter("pigbirthday_searchend");
		String infarmdate_searchbegin = request.getParameter("infarmdate_searchbegin");
		String infarmdate_searchend = request.getParameter("infarmdate_searchend");
		String outfarmdate_searchbegin = request.getParameter("outfarmdate_searchbegin");
		String outfarmdate_searchend = request.getParameter("outfarmdate_searchend");
		String farmid_search = request.getParameter("farmid_search");
		
		if(pigbirthday_searchbegin == null) {
			pigbirthday_searchbegin = "";
		}
		if(pigbirthday_searchend == null) {
			pigbirthday_searchend = "";
		}
		if(infarmdate_searchbegin == null) {
			infarmdate_searchbegin = "";
		}
		if(infarmdate_searchend == null) {
			infarmdate_searchend = "";
		}
		if(outfarmdate_searchbegin == null) {
			outfarmdate_searchbegin = "";
		}
		if(outfarmdate_searchend == null) {
			outfarmdate_searchend = "";
		}
		if(farmid_search == null) {
			farmid_search = "";
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
		PigCond cond = new PigCond();
		cond.setPigbirthday_searchbegin(pigbirthday_searchbegin);
		cond.setPigbirthday_searchend(pigbirthday_searchend);
		cond.setInfarmdate_searchbegin(infarmdate_searchbegin);
		cond.setInfarmdate_searchend(infarmdate_searchend);
		cond.setOutfarmdate_searchbegin(outfarmdate_searchbegin);
		cond.setOutfarmdate_searchend(outfarmdate_searchend);
		cond.setFarmid_search(farmid_search);
		
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = infarmService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}
	// �õ����е�����
	@RequestMapping("/toinfarmList")
	public String toinfarmList(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/infarmList";
	}
	//���
	/**
	 * @param request
	 * @param response
	 * @param info
	 */
	@RequestMapping("/addInFarm")
	public void addInFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		Json j = new Json();
		try {
			//��������
			//����ʱ��Ϊ��ǰʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String infarmdate = sdf.format(new Date());
			info.setInfarmdate(infarmdate);
			infarmService.insert(info);
			//������ʾ������ֳ�����ݸĳɺ���
			Farm farm = farmService.findInfoById(info.getFarmid());
			info.setFarmid(farm.getFarmname());
			
			//������ʾ�����Ա���������ʾ
			String sex = info.getPigsex();
			if(sex.equals("1")) {
				info.setPigsex("��");
			} else if(sex.equals("0")) {
				info.setPigsex("ĸ");
			}
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			j.setObj(info);
			
			//�·������ʾ����С�������Ϣ��
			//{��cmd��:��set_lcd��,��args��:{��device_id��:06,��device_value��:������,����,����/����,�Ա�,���䡱}}
			//������ʽ���ַ���+�������ָ�
			//���࣬����������/����״��
			//ʵ����������100kg�����Լ���/������������ʾ���ء�������ʾ����״����
			//С�����У�25��
			StringBuffer sendmsgBuffer = new StringBuffer();
			sendmsgBuffer.append(info.getPigtype()).append(",").append(info.getInfarmweight()).append(",").
				append(info.getPigfrom());

			//��֯�·��ַ���
			String sendmsg = "{\"seq_no\":\"110\",\"cmd\":\"set_lcd\",\"args\":{\"device_id\":\"06\",\"device_value\":\""+sendmsgBuffer.toString()+"\"}}\n";
			System.out.println(sendmsg);
			ClientThread thread = new ClientThread();
			thread.send(sendmsg);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//��ת�����ҳ��
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/addinFarm";
	}
	
	// ��ת���޸�ҳ��
	@RequestMapping("/toUpdate/{id}")
	public String toUpdate(@PathVariable(value="id") String infoId,Model model) {
		// �õ���ǰ
		Pig tmpObj = infarmService.findByRfid(infoId);
		model.addAttribute("infarmForModify", tmpObj);
		
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		
		return "farm/updateinFarm";
	}

	// �޸�
	@RequestMapping("/updateinFarm")
	public void updateinFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		//�޸ĵ�ǰ��ɫ
		Json j = new Json();
		try {
			//�޸�����
			infarmService.update(info);
			//������ʾ������ֳ�����ݸĳɺ���
			Farm farm = farmService.findInfoById(info.getFarmid());
			info.setFarmid(farm.getFarmname());
			//������ʾ�����Ա��Ժ�����ʾ
			String sex = info.getPigsex();
			if(sex.equals("1")) {
				info.setPigsex("��");
			} else if(sex.equals("0")) {
				info.setPigsex("ĸ");
			}
			//�趨
			j.setSuccess(true);
			j.setMsg("�޸ĳɹ�");
			j.setObj(info);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("�޸�ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// ɾ��
	@RequestMapping("/deleteinFarm/{id}")
	public void deleteinFarm(@PathVariable(value="id") String infoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			//ɾ��
			infarmService.delete(infoId);
			j.setSuccess(true);
			j.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("ɾ��ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	// ɾ��
	@RequestMapping("/deleteCheck")
	public void deleteCheck(HttpServletRequest request,HttpServletResponse response) {
		String rfidid = request.getParameter("rfidId");
		//�ж��������Ƿ���������
		List<Breed> list = infarmService.findBreedByRfid(rfidid);
		try {
			if(list != null) {
				if (list.size() > 0) {
					response.getWriter().print("false");
				} else {
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
	 * ��֤ɨ���RFID��ĳ�������Ƿ��ظ�
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkDuplicate")
	public void checkDuplicate(HttpServletRequest request,HttpServletResponse response) {
		String rfidid = request.getParameter("rfidid");
		//�ж����Ƿ���������
		Pig pig = infarmService.findByRfid(rfidid);
		try {
			if(pig != null) {
				response.getWriter().print("true");
			} else {
				response.getWriter().print("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
