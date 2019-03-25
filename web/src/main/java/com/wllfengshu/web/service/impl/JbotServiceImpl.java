package com.wllfengshu.web.service.impl;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileDownloadUtil;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.MysqlUtil;
import com.wllfengshu.common.utils.StringUtil;
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
	public Map<String, Object> initProject() {
		Map<String, Object> result = new HashMap<>(16);
		result.put("data",StringUtil.getServerDbConnect());
		logger.info("JbotServiceImpl,init-------->result:{}",result);
		return result;
	}

	@Override
	public Map<String, Object> settingProject(ConnectInfo connectInfo, HttpServletResponse response){
		Map<String, Object> result = new HashMap<>(16);
		//1 检测数据库端口是否合法
		if (Integer.valueOf(connectInfo.getDbPort())<0 && Integer.valueOf(connectInfo.getDbPort())>65535){
			response.setStatus(411);
			result.put("isSuccess",false);
			result.put("msg","数据库端口不合法");
			return result;
		}
		//2 获取数据库中的表信息
		if ("localhost".equals(connectInfo.getDbIp())
			|| "127.0.0.1".equals(connectInfo.getDbIp())
			|| StringUtil.getServerIp().equals(connectInfo.getDbIp())){
			result.put("data", jbotDao.getDBInfo(connectInfo.getDbName()));
		}else {
			result.put("data", MysqlUtil.getDBInfo(connectInfo));
		}
		logger.info("JbotServiceImpl,settingProject-------->result:{}",result);
		return result;
	}

	@Override
	public Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo, HttpServletResponse response){
		Map<String, Object> result = new HashMap<>(16);
		//1 检测projectName和packageName不能是特殊字符串
		if (!Interceptor.checkProject(projectName)){
			response.setStatus(421);
			result.put("isSuccess",false);
			result.put("msg","项目名不合法");
			return result;
		}
		//2 检测packageName不能是特殊字符串
		if (!Interceptor.checkPackage(packageName)){
			response.setStatus(422);
			result.put("isSuccess",false);
			result.put("msg","包名不合法");
			return result;
		}
		//3 调用生成项目的入口类
		if (Launch.start(projectName, packageName, dbInfo)){
			//3.1 下载生成的项目
			FileDownloadUtil.download(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip",response);
			result.put("isSuccess",true);
			result.put("msg","生成项目成功");
			//3.2 删除生成的项目文件
			FileUtil.deleteFile(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip");
			FileUtil.deleteDir(new File(Collective.TARGET_PROJECT_HOME+"/"+projectName));
		}else {
			response.setStatus(423);
			result.put("isSuccess",false);
			result.put("msg","生成项目失败");
		}
		logger.info("JbotServiceImpl,produceProject-------->result:{}",result);
		return result;
	}
}
