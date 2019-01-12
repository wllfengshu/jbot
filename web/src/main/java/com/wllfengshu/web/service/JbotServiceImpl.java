package com.wllfengshu.web.service;

import com.wllfengshu.core.Launch;
import com.wllfengshu.web.dao.JbotDao;
import com.wllfengshu.common.entity.ConnectInfo;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JbotServiceImpl implements JbotService {

	@Autowired
	private JbotDao jbotDao;

	@Override
	public Map<String, Object> settingProject(ConnectInfo connectInfo){
		Map<String, Object> result = new HashMap<>();
		//获取数据库中的表信息
		result.put("data",jbotDao.getDBInfo(connectInfo.getDbName()));
		LogUtil.info(this,"settingProject-------->result:%s",result);
		return result;
	}

	@Override
	public Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo){
		Map<String, Object> result = new HashMap<>();
		//调用生成项目的入口类
		result.put("isSuccess", Launch.start(projectName, packageName, dbInfo));
		LogUtil.info(this,"produceProject-------->result:%s",result);
		return result;
	}
}
