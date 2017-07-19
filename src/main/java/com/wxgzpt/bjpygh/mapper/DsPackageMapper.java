package com.wxgzpt.bjpygh.mapper;

import java.util.List;

import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.DsPackage;

public interface DsPackageMapper {
	
	public void insertDsPackage(DsPackage dsPackage);
	public void deleteDsPackage(String dsname);
	public List<DsPackage> selectDsPackage(String dsname);
	public DsPackage selectDsPackageById(int packageid);
}
