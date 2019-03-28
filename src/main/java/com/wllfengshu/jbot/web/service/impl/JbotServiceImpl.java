package com.wllfengshu.jbot.web.service.impl;

import com.wllfengshu.jbot.common.constant.Collective;
import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.common.utils.FileDownloadUtil;
import com.wllfengshu.jbot.common.utils.FileUtil;
import com.wllfengshu.jbot.common.utils.MysqlUtil;
import com.wllfengshu.jbot.common.utils.StringUtil;
import com.wllfengshu.jbot.core.Launch;
import com.wllfengshu.jbot.web.dao.JbotDao;
import com.wllfengshu.jbot.web.entity.ConnectInfo;
import com.wllfengshu.jbot.web.entity.DbInfo;
import com.wllfengshu.jbot.common.security.Interceptor;
import com.wllfengshu.jbot.web.service.JbotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
	public Map<String, Object> initProject() throws CustomException {
		Map<String, Object> result = new HashMap<>(16);
		result.put("data",StringUtil.getServerDbConnect());
		logger.info("JbotServiceImpl,init-------->result:{}",result);
		return result;
	}

	@Override
	public Map<String, Object> settingProject(ConnectInfo connectInfo, HttpServletResponse response)throws CustomException {
		Map<String, Object> result = new HashMap<>(16);
		//1 检测数据库地址是否合法(可以是域名或IP)
		if (StringUtil.isEmpty(connectInfo.getDbIp())){
			logger.error("数据库地址不合法");
			throw new CustomException("数据库地址不合法", CustomException.ExceptionName.IllegalDbIp);
		}
		//2 检测数据库端口是否合法
		if (StringUtil.isEmpty(connectInfo.getDbPort()) && Integer.valueOf(connectInfo.getDbPort())>0 && Integer.valueOf(connectInfo.getDbPort())<65535){
			logger.error("数据库端口不合法");
			throw new CustomException("数据库端口不合法", CustomException.ExceptionName.IllegalDbPort);
		}
		//3 检测数据库名是否合法
		if (StringUtil.isEmpty(connectInfo.getDbName())){
			logger.error("数据库名不合法");
			throw new CustomException("数据库名不合法", CustomException.ExceptionName.IllegalDbName);
		}
		//4 检测数据库用户名是否合法
		if (StringUtil.isEmpty(connectInfo.getDbPort())){
			logger.error("数据库用户名不合法");
			throw new CustomException("数据库用户名不合法", CustomException.ExceptionName.IllegalDbUsername);
		}
		//5 获取数据库中的表信息
		if ("localhost".equals(connectInfo.getDbIp()) || "127.0.0.1".equals(connectInfo.getDbIp()) || StringUtil.getServerIp().equals(connectInfo.getDbIp())){
			result.put("data", jbotDao.getDbInfo(connectInfo.getDbName()));
		}else {
			result.put("data", MysqlUtil.getDbInfo(connectInfo));
		}
		logger.info("JbotServiceImpl,settingProject-------->result:{}",result);
		return result;
	}

	@Override
	public Map<String, Object> produceProject(String projectName, String packageName, DbInfo dbInfo, HttpServletResponse response)throws CustomException {
		Map<String, Object> result = new HashMap<>(16);
		//1 检测项目名是否合法
		if (!Interceptor.checkProject(projectName)){
			logger.error("项目名不合法");
			throw new CustomException("项目名不合法", CustomException.ExceptionName.IllegalProjectName);
		}
		//2 检测包名是否合法
		if (!Interceptor.checkPackage(packageName)){
			logger.error("包名不合法");
			throw new CustomException("包名不合法", CustomException.ExceptionName.IllegalPackageName);
		}
		//3 调用生成项目的入口类
		if (Launch.start(projectName, packageName, dbInfo)){
			//3.1 下载生成的项目
			FileDownloadUtil.download(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip",response);
			//3.2 删除生成的项目文件
			FileUtil.deleteFile(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip");
			FileUtil.deleteDir(new File(Collective.TARGET_PROJECT_HOME+"/"+projectName));
		}else {
			logger.error("生成项目失败");
			throw new CustomException("生成项目失败", CustomException.ExceptionName.FailedProduceProject);
		}
		logger.info("JbotServiceImpl,produceProject-------->result:{}",result);
		return result;
	}
}
