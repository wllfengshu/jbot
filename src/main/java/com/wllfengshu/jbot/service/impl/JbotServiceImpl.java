package com.wllfengshu.jbot.service.impl;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.po.Table;
import com.wllfengshu.jbot.utils.FileUtil;
import com.wllfengshu.jbot.utils.MysqlUtil;
import com.wllfengshu.jbot.utils.StringUtil;
import com.wllfengshu.jbot.dao.JbotDAO;
import com.wllfengshu.jbot.model.ConnectInfo;
import com.wllfengshu.jbot.security.Interceptor;
import com.wllfengshu.jbot.service.JbotService;
import com.wllfengshu.jbot.work.TemplateBoot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Slf4j
@Service
public class JbotServiceImpl implements JbotService {

    @Autowired
    private JbotDAO jbotDao;

    @Override
    public Map<String, Object> initProject() throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        result.put("data", StringUtil.getServerDbConnect());
        log.info("JbotServiceImpl,init-------->result:{}", result);
        return result;
    }

    @Override
    public Map<String, Object> settingProject(ConnectInfo connectInfo, HttpServletResponse response) throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        //1 检测数据库地址是否合法(可以是域名或IP)
        if (StringUtil.isEmpty(connectInfo.getDbIp())) {
            log.error("数据库地址不合法");
            throw new CustomException("数据库地址不合法", CustomException.ExceptionName.IllegalDbIp);
        }
        //2 检测数据库端口是否合法
        if (StringUtil.isEmpty(connectInfo.getDbPort()) && Integer.valueOf(connectInfo.getDbPort()) > 0 && Integer.valueOf(connectInfo.getDbPort()) < 65535) {
            log.error("数据库端口不合法");
            throw new CustomException("数据库端口不合法", CustomException.ExceptionName.IllegalDbPort);
        }
        //3 检测数据库名是否合法
        if (StringUtil.isEmpty(connectInfo.getDbName())) {
            log.error("数据库名不合法");
            throw new CustomException("数据库名不合法", CustomException.ExceptionName.IllegalDbName);
        }
        //4 检测数据库用户名是否合法
        if (StringUtil.isEmpty(connectInfo.getDbPort())) {
            log.error("数据库用户名不合法");
            throw new CustomException("数据库用户名不合法", CustomException.ExceptionName.IllegalDbUsername);
        }
        //5 获取数据库中的表信息
        if ("localhost".equals(connectInfo.getDbIp()) || "127.0.0.1".equals(connectInfo.getDbIp()) || StringUtil.getServerIp().equals(connectInfo.getDbIp())) {
            result.put("data", jbotDao.getTable(connectInfo.getDbName()));
        } else {
            result.put("data", MysqlUtil.getDbInfo(connectInfo));
        }
        log.info("JbotServiceImpl,settingProject-------->result:{}", result);
        return result;
    }

    @Override
    public Map<String, Object> produceProject(String projectName, String packageName, List<Table> tables, HttpServletResponse response) throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        //1 检测项目名是否合法
        if (!Interceptor.checkProject(projectName)) {
            log.error("项目名不合法");
            throw new CustomException("项目名不合法", CustomException.ExceptionName.IllegalProjectName);
        }
        //2 检测包名是否合法
        if (!Interceptor.checkPackage(packageName)) {
            log.error("包名不合法");
            throw new CustomException("包名不合法", CustomException.ExceptionName.IllegalPackageName);
        }
        //3 调用生成项目的入口类
        try {
            //3.0 生成项目
            new TemplateBoot(projectName, packageName,tables).start();
            //3.1 压缩生成的项目
            FileUtil.fileToZip(Constant.TARGET_PROJECT_HOME + "/" + projectName + ".zip",Constant.TARGET_PROJECT_HOME + "/" + projectName);
            //3.2 下载生成的项目
            FileUtil.download(Constant.TARGET_PROJECT_HOME + "/" + projectName + ".zip", response);
            //3.3 删除生成的项目文件
            new File(Constant.TARGET_PROJECT_HOME + "/" + projectName + ".zip").delete();
            FileUtils.deleteDirectory(new File(Constant.TARGET_PROJECT_HOME + "/" + projectName));
        }catch (Exception e) {
            log.error("生成项目失败",e);
            throw new CustomException("生成项目失败", CustomException.ExceptionName.FailedProduceProject);
        }
        log.info("JbotServiceImpl,produceProject-------->result:{}", result);
        return result;
    }
}
