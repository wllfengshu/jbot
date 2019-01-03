package com.wllfengshu.jbot.dao;

import com.wllfengshu.jbot.entity.DBInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JbotDao {
	List<DBInfo> getDBInfo(String dbName);
}
