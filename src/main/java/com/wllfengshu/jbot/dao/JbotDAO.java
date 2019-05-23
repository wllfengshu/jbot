package com.wllfengshu.jbot.dao;

import com.wllfengshu.jbot.model.po.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wllfengshu
 */
@Repository
public interface JbotDAO {

    /**
     * 获取指定数据库中表结构
     *
     * @param dbName
     * @return
     */
    List<Table> getTable(String dbName);

}
