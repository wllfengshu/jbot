package com.wllfengshu.web.service;

import com.wllfengshu.common.entity.ConnectInfo;
import com.wllfengshu.common.entity.DBInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * service
 * @author wllfengshu
 */
@Service
public interface JbotService {
	/**
	 * 设置数据库连接信息
	 * @param connectInfo
	 * @return
	 */
	Map<String, Object> settingProject(ConnectInfo connectInfo);

	/**
	 * 生成目标项目
	 * @param projectName
	 * @param packageName
	 * @param dbInfo
	 * @param response
	 * @return
	 */
	Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo, HttpServletResponse response);
}
