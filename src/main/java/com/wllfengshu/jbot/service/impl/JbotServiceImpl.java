package com.wllfengshu.jbot.service.impl;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.configs.EnvConfig;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.utils.FileUtil;
import com.wllfengshu.jbot.utils.MysqlUtil;
import com.wllfengshu.jbot.utils.StringUtil;
import com.wllfengshu.jbot.dao.JbotDAO;
import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import com.wllfengshu.jbot.security.Interceptor;
import com.wllfengshu.jbot.service.JbotService;
import com.wllfengshu.jbot.work.TemplateBoot;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class JbotServiceImpl implements JbotService {

    @NonNull
    private JbotDAO jbotDao;
    @NonNull
    private EnvConfig envConfig;
    @NonNull
    private Interceptor interceptor;

    @Override
    public Map<String, Object> initProject() throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        result.put("data", StringUtil.getServerDbConnect(envConfig.getDbUrl(),envConfig.getDbUsername(),envConfig.getDbPassword()));
        log.info("JbotServiceImpl,init-------->result:{}", result);
        return result;
    }

    @Override
    public Map<String, Object> settingProject(ConnectInfoVO connectInfoVO, HttpServletResponse response) throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        //1 检测数据库地址是否合法(可以是域名或IP)
        if (StringUtil.isEmpty(connectInfoVO.getDbIp())) {
            log.error("数据库地址不合法");
            throw new CustomException("数据库地址不合法", CustomException.ExceptionName.IllegalDbIp);
        }
        //2 检测数据库端口是否合法
        if (StringUtil.isEmpty(connectInfoVO.getDbPort()) && Integer.valueOf(connectInfoVO.getDbPort()) > 0 && Integer.valueOf(connectInfoVO.getDbPort()) < 65535) {
            log.error("数据库端口不合法");
            throw new CustomException("数据库端口不合法", CustomException.ExceptionName.IllegalDbPort);
        }
        //3 检测数据库名是否合法
        if (StringUtil.isEmpty(connectInfoVO.getDbName())) {
            log.error("数据库名不合法");
            throw new CustomException("数据库名不合法", CustomException.ExceptionName.IllegalDbName);
        }
        //4 检测数据库用户名是否合法
        if (StringUtil.isEmpty(connectInfoVO.getDbPort())) {
            log.error("数据库用户名不合法");
            throw new CustomException("数据库用户名不合法", CustomException.ExceptionName.IllegalDbUsername);
        }
        //5 获取数据库中的表信息
        if ("localhost".equals(connectInfoVO.getDbIp()) || "127.0.0.1".equals(connectInfoVO.getDbIp()) || StringUtil.getServerIp().equals(connectInfoVO.getDbIp())) {
            result.put("data", jbotDao.getTables(connectInfoVO.getDbName()));
        } else {
            result.put("data", MysqlUtil.getDbInfo(connectInfoVO));
        }
        log.info("JbotServiceImpl,settingProject-------->result:{}", result);
        return result;
    }

    @Override
    public Map<String, Object> produceProject(String projectName, String packageName, List<Table> tables, HttpServletResponse response) throws CustomException {
        Map<String, Object> result = new HashMap<>(16);
        //1 检测项目名是否合法
        if (!interceptor.checkProject(projectName)) {
            log.error("项目名不合法");
            throw new CustomException("项目名不合法", CustomException.ExceptionName.IllegalProjectName);
        }
        //2 检测包名是否合法
        if (!interceptor.checkPackage(packageName)) {
            log.error("包名不合法");
            throw new CustomException("包名不合法", CustomException.ExceptionName.IllegalPackageName);
        }
        //3 生成项目
        new TemplateBoot().start(projectName, packageName,tables);
        //4 压缩生成的项目
        String targetProjectPath = Constant.TARGET_PROJECT_HOME + "/" + projectName;
        String targetProjectZipPath = targetProjectPath + ".zip";
        FileUtil.fileToZip(targetProjectZipPath,targetProjectPath);
        //5 下载生成的项目
        FileUtil.download(targetProjectZipPath, response);
        //6 删除生成的项目文件
        new File(targetProjectZipPath).delete();
        FileUtil.deleteDir(new File(targetProjectPath));
        result.put("operation","success");
        log.info("JbotServiceImpl,produceProject-------->result:{}", result);
        return result;
    }
}
