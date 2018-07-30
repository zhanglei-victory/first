package com.microsec.ycjc.farm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.farm.dao.IVaccineDao;
import com.microsec.ycjc.farm.pojo.Vaccine;
import com.microsec.ycjc.farm.service.IVaccineService;

@Service("vaccineService")
public class VaccineServiceImpl implements IVaccineService {

	@Autowired
	private IVaccineDao dao;
	
	public IVaccineDao getDao() {
		return dao;
	}

	public void setDao(IVaccineDao dao) {
		this.dao = dao;
	}

	@Override
	public void insert(Vaccine info) {
		dao.insert(info);
	}

	@Override
	public void update(Vaccine info) {
		dao.update(info);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public Vaccine findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<Vaccine> findAllInfoList() {
		return dao.findAllInfoList();
	}
}
