package com.wllfengshu.web.service;

import com.wllfengshu.common.entity.ConnectInfo;
import com.wllfengshu.common.entity.DBInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JbotService {
	Map<String, Object> settingProject(ConnectInfo connectInfo);
	Map<String, Object> produceProject(String projectName, String packageName, DBInfo dbInfo);
}
