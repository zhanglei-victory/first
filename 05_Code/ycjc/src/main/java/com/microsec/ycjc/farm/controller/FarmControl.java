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

	//���������־��¼��
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
	// ���ݲ�ѯ�����õ���������
	@RequestMapping("/findAllInfoByCond")
	public void findAllInfoByCond(HttpServletResponse response,HttpServletRequest request) {
		// ��ѯ����
		String name = request.getParameter("search_farm_name");
		String licenseid=request.getParameter("licenseid");
		if(name == null) {
			name = "";
		}
		if(licenseid == null) {
			licenseid = "";
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
		FarmCond cond = new FarmCond();
		cond.setFarmname(name);
		cond.setStart(start);
		cond.setSinglePagesize(number);
	//	cond.setLicenseid(licenseid);
		
		DataGrid d = farmService.findAllInfoByCond(cond);
		YcjcJsonUtil.writeJson(d, response);
	}
	// �õ����е�����
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
//			f.setFarmname("ȫ����ֳ��");
//			farm_new.add(f);
//			farm_new.addAll(farm);
//		}
//		model.addAttribute("farm", farm_new);
//		//model.addAttribute("searchbegin", bc.getSearchbegin());
//		//model.addAttribute("searchend", bc.getSearchend());
//		
//		return "farm/farmList";
//	}
	//���
	@RequestMapping("/addFarm")
	public void addFarm(HttpServletRequest request,HttpServletResponse response,Farm farm) {
		Json j = new Json();
		try {
			//��������
			farmService.insertInfo(farm);
			//�趨
			j.setSuccess(true);
			j.setMsg("��ӳɹ�");
			j.setObj(farm);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("���ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	//��ת�����ҳ��
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "farm/addFarm";
	}
	
	// ��ת���޸�ҳ��
	@RequestMapping("/toUpdate/{id}")
	public String toUpdate(@PathVariable(value="id") String farmId,Model model,HttpSession session) {
		
		UserInfo user = (UserInfo) session.getAttribute(Const.LOGIN_USER_ATTRIBUTE);
		model.addAttribute("user", user);

		// �õ���ǰ
		Farm tmpObj = farmService.findInfoById(farmId);
		model.addAttribute("farmForModify", tmpObj);
		return "farm/updateFarm";
	}
	// ��ת��������ֵҳ��
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
//			// �õ���ǰ
//			Farm tmpObj = farmService.findInfoById(farmId);
//			obj.setSceneid(tmpObj.getFarmid());
//			obj.setScenetype(Const.SCENE_FARM);
//		}
//		
//		model.addAttribute("info", obj);
//		return "farm/maxminFarm";
//	}
	// �޸�
//	@RequestMapping("/sceneUpdateFarm")
//	public void sceneUpdateFarm(HttpServletRequest request,HttpServletResponse response) {
//		//�޸ĵ�ǰ
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
//			//��С��ֵ < �����ֵ
//			if(tempminfloat < tempmaxfloat && humidityminfloat < humiditymaxfloat && lightminfloat < lightmaxfloat) {
//				Map<String,String> paramMap = new HashMap<String,String>();
//				paramMap.put("sceneid", obj.getSceneid());
//				paramMap.put("scenetype", obj.getScenetype());
//				// ���������Ƿ����
////				if(sObj != null) {
////					//�޸�����
////					//�趨
////					j.setSuccess(true);
////					j.setMsg("�޸����óɹ�");
////					j.setObj(sObj);
////					
////					//ͬʱ�·�������м�����������µõ�����map�ṹ,������ֵ��������
////					String sendmsg = "{\"cmd\":\"set_maxmin_openflag\"}\n";
////					infoLog.info("��ֳ����ֵ���ã�".concat(sendmsg));
////					ClientThread thread = new ClientThread();
////					thread.send(sendmsg);
////				} else {
////					j.setSuccess(true);
////					j.setMsg("������óɹ�");
////					j.setObj(obj);
////				}
//			} else {
//				j.setSuccess(false);
//				j.setMsg("��ֵ���ò�����������Ӧ�ð������¹����������<br>���У� ��С��ֵ < �����ֵ");
//				infoLog.info("��ֵ���ò�����������Ӧ�ð������¹����������<br>���У� ��С��ֵ < �����ֵ");
//			}
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("����ʧ��");
//		}
//		
//		YcjcJsonUtil.writeJson(j, response);
//	}
	// ��ת���޸�ҳ��
	@RequestMapping("/toDetail/{id}")
	public String toDetail(@PathVariable(value="id") String farmId,Model model) {
		// �õ���ǰ
		Farm tmpObj = farmService.findInfoById(farmId);
		model.addAttribute("farmForDetail", tmpObj);
		return "farm/detailFarm";
	}

//	// �޸�
//	@RequestMapping("/updateFarm")
//	public void updateFarm(HttpServletRequest request,HttpServletResponse response,Farm farm) {
//		//�޸ĵ�ǰ
//		Json j = new Json();
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String updatetime = sdf.format(new Date());
//			farm.setUpddatetime(updatetime);
//			//�޸�����
//			farmService.updateInfo(farm);
//			//�趨
//			j.setSuccess(true);
//			j.setMsg("�޸ĳɹ�");
//			j.setObj(farm);
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("�޸�ʧ��");
//		}
//		
//		YcjcJsonUtil.writeJson(j, response);
//	}
//	// ɾ��
//	@RequestMapping("/deleteFarm/{id}")
//	public void deleteFarm(@PathVariable(value="id") String licenseId,HttpServletResponse response) {
//		Json j = new Json();
//		try {
//			Farm farm = farmService.findByLicenseId(licenseId);
//			String farmid = farm.getFarmid();
//			//ɾ��
//			farmService.deleteInfo(farmid);
//			
//			//ɾ����ֵ���ñ�
////			Map<String,String> paramMap = new HashMap<String, String>();
////			paramMap.put("sceneid", farmid);
////			paramMap.put("scenetype", Const.SCENE_FARM);
////			sceneobjService.delete(paramMap);
//			j.setSuccess(true);
//			j.setMsg("ɾ���ɹ�");
//		} catch (Exception e) {
//			j.setSuccess(false);
//			j.setMsg("ɾ��ʧ��");
//		}
//		YcjcJsonUtil.writeJson(j, response);
//	}
//	// ɾ��
//	@RequestMapping("/deleteFarmCheck/{id}")
//	public void deleteFarmCheck(@PathVariable(value="id") String sceneseid,HttpServletResponse response) {
//		//�ж��Ƿ��û�ʹ��
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
	// ��֤���Ƿ�ռ��
	@RequestMapping("/checkFarmName")
	public void checkFarmName(HttpServletRequest request,HttpServletResponse response) {
		// �õ���¼��
		String farmname = request.getParameter("farmname");
		List<Farm> list = farmService.findAllInfoList();
		//Ĭ����֤ͨ��
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
		//�޸ĵ�ǰ
		Json j = new Json();
		try {
			String strflag = "";
			if("1".equals(switchflag)) {
				strflag = "true";
			} else if("0".equals(switchflag)) {
				strflag = "false";
			}
			//��֯�·��ַ���
			String sendmsg = "{\"seq_no\":\"100\",\"cmd\":\"set_switch\",\"args\":{\"device_id\":2,\"device_type\":24,\"device_value\":\""+strflag+"\"}}\n";
			infoLog.info("��ֳ���·����ص����".concat(sendmsg));
			
			ClientThread thread = new ClientThread();
			boolean flag = thread.send(sendmsg);
			if(flag) {
				//�޸Ŀ��ص�״̬
				Farm tmpfarm = farmService.findInfoById(infoid);
				if(tmpfarm != null) {
					if("1".equals(switchflag)) {
						tmpfarm.setLightstatus("1");
					} else if("0".equals(switchflag)) {
						tmpfarm.setLightstatus("0");
					}
					farmService.updateInfo(tmpfarm);
				}
				//�趨
				j.setSuccess(true);
				j.setMsg("�����ɹ�");
				infoLog.info("��ֳ���·����ص�����ɹ�");
			} else {
				j.setSuccess(false);
				j.setMsg("����ʧ��");
				infoLog.info("��ֳ���·����ص�����ʧ��");
			}
			System.out.println(flag);
			
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("����ʧ��");
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
		//�޸ĵ�ǰ
		Json j = new Json();
		try {
			String strflag = "";
			if("1".equals(switchflag)) {
				strflag = "true";
			} else if("0".equals(switchflag)) {
				strflag = "false";
			}
			//��֯�·��ַ���
			String sendmsg = "{\"seq_no\":\"101\",\"cmd\":\"set_switch\",\"args\":{\"device_id\":3,\"device_type\":24,\"device_value\":\""+strflag+"\"}}\n";
			infoLog.info("��ֳ���·����ط������".concat(sendmsg));
			
			ClientThread thread = new ClientThread();
			boolean flag = thread.send(sendmsg);
			if(flag) {
				//�޸Ŀ��ط���״̬
				Farm tmpfarm = farmService.findInfoById(infoid);
				if(tmpfarm != null) {
					if("1".equals(switchflag)) {
						tmpfarm.setFanstatus("1");
					} else if("0".equals(switchflag)) {
						tmpfarm.setFanstatus("0");
					}
					farmService.updateInfo(tmpfarm);
				}
				//�趨
				j.setSuccess(true);
				j.setMsg("�����ɹ�");
				infoLog.info("��ֳ���·����ط�������ɹ�");
			} else {
				j.setSuccess(false);
				j.setMsg("����ʧ��");
				infoLog.info("��ֳ���·����ط�������ʧ��");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("����ʧ��");
		}
		
		YcjcJsonUtil.writeJson(j, response);
	}
	// �������е���ֳ����Ϣ������json��ʽ���ͻ��˽���ʹ��
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
