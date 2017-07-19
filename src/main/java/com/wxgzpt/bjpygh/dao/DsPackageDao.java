package com.wxgzpt.bjpygh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.DsPackage;
import com.wxgzpt.bjpygh.mapper.DsPackageMapper;
import com.wxgzpt.bjpygh.utils.SqlSessionFactoryUtil;

public class DsPackageDao {

SqlSession sqlSession = null;
	
	//插入驾校信息
	public void insertDsPackage(DsPackage dsPackage){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsPackageMapper dsPackageMapper = sqlSession.getMapper(DsPackageMapper.class);
			dsPackageMapper.insertDsPackage(dsPackage);
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
	public void deleteDsPackage(String dsname){
		try {
			sqlSession = SqlSessionFactoryUtil.openSqlSession();
			DsPackageMapper dsPackageMapper = sqlSession.getMapper(DsPackageMapper.class);
			dsPackageMapper.deleteDsPackage(dsname);
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
	
	//查询驾校信息
		public List<DsPackage> selectDsPackage(String dsname){
			List<DsPackage> dspList = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsPackageMapper dsPackageMapper = sqlSession.getMapper(DsPackageMapper.class);
				dspList = dsPackageMapper.selectDsPackage(dsname);
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return dspList;
		}
		
		//查询驾校信息
		public DsPackage selectDsPackageById(String packageid){
			DsPackage dsPackage = null;
			try {
				sqlSession = SqlSessionFactoryUtil.openSqlSession();
				DsPackageMapper dsPackageMapper = sqlSession.getMapper(DsPackageMapper.class);
				dsPackage = dsPackageMapper.selectDsPackageById(Integer.parseInt(packageid));
				sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			}finally{
				if (sqlSession != null){
					sqlSession.close();
				}
			}
			return dsPackage;
		}
		
}
