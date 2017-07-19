package com.wxgzpt.bjpygh.mapper;

import java.util.List;

import com.wxgzpt.bjpygh.entity.DsInformation;

public interface DsInfoMapper {

	public void insertDsInfo(DsInformation DsInfo);
	public void deleteDsInfo(String dsname);
	public DsInformation selectDsInfo(String dsname);
	public List<DsInformation> selectDsInfoList();
}
