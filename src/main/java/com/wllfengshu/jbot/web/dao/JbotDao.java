package com.wllfengshu.jbot.web.dao;

import com.wllfengshu.jbot.web.entity.DbInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wllfengshu
 */
@Repository
public interface JbotDao {

    /**
     * 获取指定数据库中表结构
     *
     * @param dbName
     * @return
     */
    List<DbInfo> getDbInfo(String dbName);

}
