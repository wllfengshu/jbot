package com.wllfengshu.jbot.service;

import com.wllfengshu.core.Launch;
import com.wllfengshu.jbot.common.Jbot;
import com.wllfengshu.jbot.dao.JbotDao;
import com.wllfengshu.jbot.entity.ConnectInfo;
import com.wllfengshu.jbot.entity.DBInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JbotServiceImpl implements JbotService {

	@Autowired
	private JbotDao jbotDao;

	@Override
	public Map<String, Object> settingProject(String projectName, String packageName, ConnectInfo connectInfo){
		Map<String, Object> result = new HashMap<>();
		//设置项目名
		Jbot.projectName = projectName;
		//设置包名
		Jbot.packageName = packageName;
		//设置数据库连接信息
		Jbot.connectInfo = connectInfo;
		//获取数据库中的表信息
		result.put("data",jbotDao.getDBInfo(connectInfo.getDbName()));
		return result;
	}

	@Override
	public Map<String, Object> produceProject(DBInfo dbInfo){
		Map<String, Object> result = new HashMap<>();
		//设置勾选的表信息
		Jbot.dbInfo = dbInfo;
		//调用生成项目的入口类
		result.put("isSuccess",Launch.start());
		return result;
	}
}
