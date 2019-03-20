package com.wllfengshu.web.dao;

import com.wllfengshu.common.entity.DBInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao
 * @author wllfengshu
 */
@Repository
public interface JbotDao {
	/**
	 * 获取指定数据库中表结构
	 * @param dbName
	 * @return
	 */
	List<DBInfo> getDBInfo(String dbName);
}
