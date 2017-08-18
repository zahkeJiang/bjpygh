package com.wxgzpt.bjpygh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.IntegralRecord;
import com.wxgzpt.bjpygh.mapper.RecordMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class RecordDao {

	SqlSession sqlSession = null;
	
	//插入驾校信息
	public void insertRecord(IntegralRecord record){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			RecordMapper recordMapper = sqlSession.getMapper(RecordMapper.class);
			recordMapper.insertRecord(record);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	//获取驾校列表信息
		public List<IntegralRecord> getRecordById(String userid){
			List<IntegralRecord> records = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				RecordMapper recordMapper = sqlSession.getMapper(RecordMapper.class);
				records = recordMapper.getRecordById(userid);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return records;
		}
	
}
