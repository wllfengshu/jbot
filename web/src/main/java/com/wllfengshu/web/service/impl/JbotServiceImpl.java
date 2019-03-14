package com.wllfengshu.web.service.impl;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileDownloadUtil;
import com.wllfengshu.common.utils.MysqlUtil;
import com.wllfengshu.core.Launch;
import com.wllfengshu.web.dao.JbotDao;
import com.wllfengshu.common.entity.ConnectInfo;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.web.security.Interceptor;
import com.wllfengshu.web.service.JbotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * serviceImpl
 * @author wllfengshu
 */
@Service
public class JbotServiceImpl implements JbotService {

	@Autowired
	private JbotDao jbotDao;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Map<String, Object> settingProject(ConnectInfo connectInfo){
		Map<String, Object> result = new HashMap<>();
		//获取数据库中的表信息
		if (!"localhost".equals(connectInfo.getDbIp()) && !"127.0.0.1".equals(connectInfo.getDbIp())){
			result.put("data", MysqlUtil.getDBInfo(connectInfo));
		}else {
			result.put("data", jbotDao.getDBInfo(connectInfo.getDbName()));
		}
		logger.info("JbotServiceImpl,settingProject-------->result:{}",result);
		return result;
	}

	@Override
	public Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo, HttpServletResponse response){
		Map<String, Object> result = new HashMap<>();
		//检测projectName和packageName不能是特殊字符串
		if (!Interceptor.checkProject(projectName)){
			response.setStatus(410);
			result.put("isSuccess",false);
			result.put("msg","项目名不能为关键字");
			return result;
		}
		if (!Interceptor.checkPackage(packageName)){
			response.setStatus(411);
			result.put("isSuccess",false);
			result.put("msg","包名不能为关键字");
			return result;
		}
		//调用生成项目的入口类
		if (Launch.start(projectName, packageName, dbInfo)){
			FileDownloadUtil.download(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip",response);
			result.put("isSuccess",true);
			result.put("msg","生成项目成功");
		}else {
			response.setStatus(412);
			result.put("isSuccess",false);
			result.put("msg","生成项目失败");
		}
		logger.info("JbotServiceImpl,produceProject-------->result:{}",result);
		return result;
	}
}
