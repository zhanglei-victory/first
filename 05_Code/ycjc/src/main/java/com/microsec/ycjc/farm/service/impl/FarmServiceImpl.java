package com.microsec.ycjc.farm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.farm.cond.FarmCond;
import com.microsec.ycjc.farm.dao.IFarmDao;
import com.microsec.ycjc.farm.pojo.Farm;
import com.microsec.ycjc.farm.service.IFarmService;
import com.microsec.ycjc.util.DataGrid;

@Service("farmService")
public class FarmServiceImpl implements IFarmService {

	@Autowired
	private IFarmDao dao;

	public IFarmDao getDao() {
		return dao;
	}

	public void setDao(IFarmDao dao) {
		this.dao = dao;
	}

	@Override
	public void insertInfo(Farm info) {
		dao.insertFarm(info);
	}

	@Override
	public void updateInfo(Farm info) {
		dao.updateFarm(info);
	}

	@Override
	public void deleteInfo(String id) {
		dao.deleteFarm(id);
	}

	@Override
	public Farm findInfoById(String id) {
		return dao.findById(id);
	}

	@Override
	public long findAllCountByCond(FarmCond cond) {
		return dao.findAllCountByCond(cond);
	}

	@Override
	public DataGrid findAllInfoByCond(FarmCond cond) {
		List<Farm> list = dao.findAllInfoByCond(cond);
		DataGrid j = new DataGrid();
		j.setRows(list);
		j.setTotal(this.findAllCountByCond(cond));
		return j;
	}
	@Override
	public List<Farm> findAllInfoList() {
		return dao.findAllInfoList();
	}

	@Override
	public Farm findByLicenseId(String id) {
		return dao.findByLicenseId(id);
	}

	@Override
	public List<Farm> findAllInfoByCondd() {
		// TODO Auto-generated method stub
		return dao.findAllInfoByCondd();
	}

	@Override
	public List<Farm> findAllAuthInfoList(FarmCond cond) {
		return dao.findAllAuthInfoList(cond);
	}
}
