package com.wllfengshu.web.dao;

import com.wllfengshu.common.entity.DBInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JbotDao {
	List<DBInfo> getDBInfo(String dbName);
}
