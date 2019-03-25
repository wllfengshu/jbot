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
	 * 初始化
	 * @return
	 */
	Map<String, Object> initProject();

	/**
	 * 设置数据库连接信息
	 * @param connectInfo 用户自定义数据库连接信息
	 * @return
	 */
	Map<String, Object> settingProject(ConnectInfo connectInfo, HttpServletResponse response);

	/**
	 * 生成目标项目
	 * @param projectName 项目名
	 * @param packageName 包名
	 * @param dbInfo 数据库实体类（选择的表的集合）
	 * @param response
	 * @return
	 */
	Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo, HttpServletResponse response);
}
