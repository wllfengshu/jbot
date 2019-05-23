package com.wllfengshu.jbot.service;

import com.wllfengshu.jbot.model.ConnectInfo;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.po.Table;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
public interface JbotService {

    /**
     * 初始化
     *
     * @return
     * @throws CustomException
     */
    Map<String, Object> initProject() throws CustomException;

    /**
     * 设置数据库连接信息
     *
     * @param connectInfo 用户自定义数据库连接信息
     * @param response
     * @return
     * @throws CustomException
     */
    Map<String, Object> settingProject(ConnectInfo connectInfo, HttpServletResponse response) throws CustomException;

    /**
     * 生成目标项目
     *
     * @param projectName 项目名
     * @param packageName 包名
     * @param tables      选择的表的集合
     * @param response
     * @return
     * @throws CustomException
     */
    Map<String, Object> produceProject(String projectName, String packageName, List<Table> tables, HttpServletResponse response) throws CustomException;
}
