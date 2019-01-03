package com.wllfengshu.jbot.service;

import com.wllfengshu.jbot.entity.ConnectInfo;
import com.wllfengshu.jbot.entity.DBInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface JbotService {
	Map<String, Object> settingProject(String projectName, String packageName, ConnectInfo connectInfo);
	Map<String, Object> produceProject(DBInfo dbInfo);
}
