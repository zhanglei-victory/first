package com.microsec.ycjc.farm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsec.ycjc.farm.cond.PigCond;
import com.microsec.ycjc.farm.dao.IOutfarmDao;
import com.microsec.ycjc.farm.pojo.Pig;
import com.microsec.ycjc.farm.service.IOutFarmService;
import com.microsec.ycjc.util.DataGrid;

@Service("outfarmService")
public class OutFarmServiceImpl implements IOutFarmService {

	@Autowired
	private IOutfarmDao dao;
	
	public IOutfarmDao getDao() {
		return dao;
	}

	public void setDao(IOutfarmDao dao) {
		this.dao = dao;
	}

	@Override
	public void update(Pig info) {
		dao.update(info);
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
	public Pig checkOutfarm(String id) {
		return dao.checkOutfarm(id);
	}

	@Override
	public void delete(String rfid) {
		dao.delete(rfid);
	}
}
