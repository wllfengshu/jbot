package com.wllfengshu.jbot.service;

import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
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
     * @param connectInfoVO 用户自定义数据库连接信息
     * @param response
     * @return
     * @throws CustomException
     */
    Map<String, Object> settingProject(ConnectInfoVO connectInfoVO, HttpServletResponse response) throws CustomException;

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
