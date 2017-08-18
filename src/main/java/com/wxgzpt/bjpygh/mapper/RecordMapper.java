package com.wxgzpt.bjpygh.mapper;

import java.util.List;

import com.wxgzpt.bjpygh.entity.IntegralRecord;

public interface RecordMapper {

	public List<IntegralRecord> getRecordById(String userid);
	public void insertRecord(IntegralRecord record);
}
