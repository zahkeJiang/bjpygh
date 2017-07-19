package com.wxgzpt.bjpygh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.mapper.DsInfoMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class DsInfoDao {

	SqlSession sqlSession = null;
	
	//插入驾校信息
	public void insertDsInfo(DsInformation dsInfo){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsInfoMapper dsInfroMapper = sqlSession.getMapper(DsInfoMapper.class);
			dsInfroMapper.insertDsInfo(dsInfo);
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
	
	//删除驾校信息
	public void deleteDsInfo(String dsname){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsInfoMapper dsInfroMapper = sqlSession.getMapper(DsInfoMapper.class);
			dsInfroMapper.deleteDsInfo(dsname);
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
	
	//获取驾校信息
	public DsInformation selectDsInfo(String dsname){
		DsInformation dsInfo = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsInfoMapper dsInfroMapper = sqlSession.getMapper(DsInfoMapper.class);
			dsInfo = dsInfroMapper.selectDsInfo(dsname);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return dsInfo;
	}
	
	//获取驾校列表信息
		public List<DsInformation> selectDsInfoList(){
			List<DsInformation> dsInfoList = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsInfoMapper dsInfroMapper = sqlSession.getMapper(DsInfoMapper.class);
				dsInfoList = dsInfroMapper.selectDsInfoList();
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return dsInfoList;
		}
	
}
