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
	// ���ݲ�ѯ�����õ���������
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
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
		cond.setOutfarmdate_searchbegin(outfarmdate_searchbegin);
		cond.setOutfarmdate_searchend(outfarmdate_searchend);
		cond.setFarmid_search(farmid_search);
		cond.setPermitoutfarm(permitoutfarm);
		
		cond.setStart(start);
		cond.setSinglePagesize(number);
		
		DataGrid d = outfarmService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}

	// �õ����е�����
	@RequestMapping("/tooutfarmList")
	public String tooutfarmList(Model model) {
		List<Farm> list = farmService.findAllInfoList();
		model.addAttribute("farmlist", list);
		return "farm/outfarmList";
	}

	//��ת�����ҳ��
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "farm/addoutFarm";
	}
	
	// ��ת���޸�ҳ��
	@RequestMapping("/toUpdate/{id}")
	public String toUpdate(@PathVariable(value="id") String infoId,Model model) {
		// �õ���ǰ
		Pig tmpObj = outfarmService.findByRfid(infoId);
		model.addAttribute("outfarmForModify", tmpObj);
		
		return "farm/updateoutFarm";
	}

	// �޸�
	@RequestMapping("/addOutFarm")
	public void addOutFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		//�޸�
		Json j = new Json();
		try {
			//����ʱ��Ϊ��ǰʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String outfarmdate = sdf.format(new Date());
			info.setOutfarmdate(outfarmdate);
			//�޸�����
			outfarmService.update(info);
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
			j.setMsg("������ӳɹ�");
			j.setObj(info);
			//�·������ʾ����С�������Ϣ��
			//{��cmd��:��set_lcd��,��args��:{��device_id��:06,��device_value��:������,����,����/����,�Ա�,���䡱}}
			//������ʽ���ַ���+�������ָ�
			//���࣬����������/����״��
			//ʵ����������100kg�����Լ���/������������ʾ���ء�������ʾ����״����
			//С�����У�25��
			StringBuffer sendmsgBuffer = new StringBuffer();
			sendmsgBuffer.append(info.getPigtype()).append(",").append(info.getOutfarmweight()).append(",").
				append(info.getHealthstatus());

			//��֯�·��ַ���
			String sendmsg = "{\"seq_no\":\"111\",\"cmd\":\"set_lcd\",\"args\":{\"device_id\":\"06\",\"device_value\":\""+sendmsgBuffer.toString()+"\"}}\n";
			System.out.println(sendmsg);
			ClientThread thread = new ClientThread();
			thread.send(sendmsg);
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("�������ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
		
	// �޸�
	@RequestMapping("/updateOutFarm")
	public void updateOutFarm(HttpServletRequest request,HttpServletResponse response,Pig info) {
		//�޸�
		Json j = new Json();
		try {
			//�޸�����
			outfarmService.update(info);
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
	/**
	 * ��֤�Ƿ����
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkOutfarm")
	public void checkOutfarm(HttpServletRequest request,HttpServletResponse response) {
		String rfidid = request.getParameter("rfidId");
		//�ж����Ƿ���������
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
	// ɾ��
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable(value="id") String infoId,HttpServletResponse response) {
		Json j = new Json();
		try {
			//ɾ��
			outfarmService.delete(infoId);
			j.setSuccess(true);
			j.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("ɾ��ʧ��");
		}
		YcjcJsonUtil.writeJson(j, response);
	}
	/**
	 * ����rfid�õ�С�������Ŀ
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
				//δ����
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