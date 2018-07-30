package com.microsec.ycjc.farm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.dao.IInfarmDao;
import com.microsec.ycjc.farm.pojo.Breed;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.service.IInFarmService;
import com.microsec.ycjc.util.DataGrid;

@Service("infarmService")
public class InfarmServiceImpl implements IInFarmService {

	@Autowired
	private IInfarmDao dao;

	public IInfarmDao getDao() {
		return dao;
	}

	public void setDao(IInfarmDao dao) {
		this.dao = dao;
	}

	@Override
	public void insert(Pig info) {
		dao.insert(info);
	}

	@Override
	public void update(Pig info) {
		dao.update(info);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public Pig findByRfid(String id) {
		return dao.findByRfid(id);
	}

	@Override
	public long findAllCountByCond(PigCond cond) {
		return dao.findAllCountByCond(cond);
	}

	@Override
	public DataGrid findAllInfoByCond(PigCond cond) {
		List<Pig> list = dao.findAllInfoByCond(cond);
		DataGrid j = new DataGrid();
		j.setRows(list);
		j.setTotal(this.findAllCountByCond(cond));
		return j;
	}

	@Override
	public List<Pig> findAllInfoList() {
		return dao.findAllInfoList();
	}

	@Override
	public List<Pig> findByFarmId(String id) {
		return dao.findByFarmId(id);
	}

	@Override
	public List<Breed> findBreedByRfid(String id) {
		return dao.findBreedByRfid(id);
	}
}
