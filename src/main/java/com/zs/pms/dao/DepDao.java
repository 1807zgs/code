package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDep;

public interface DepDao {
	public List<TDep> queryByPid(int pid);
}
