package com.wllfengshu.web.rest;

import com.wllfengshu.common.entity.ConnectInfo;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.web.service.JbotService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * rest
 * @author wllfengshu
 */
@RestController
@RequestMapping("/jbot")
public class JbotRest {

    @Autowired
    private JbotService jbotService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "初始化项目",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam"),
            @ApiResponse(code=401, message="获取服务器数据库配置失败")
    })
    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public Map<String, Object> initProject(
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("JbotRest,init");
        return jbotService.initProject();
    }

    @ApiOperation(value = "设置项目",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam"),
            @ApiResponse(code=411, message="数据库端口不合法"),
            @ApiResponse(code=412, message="获取数据库表结构失败")
    })
    @RequestMapping(value = "/setting",method = RequestMethod.POST)
    public Map<String, Object> settingProject(
            @RequestBody @ApiParam(value = "数据库连接实体类（数据库连接信息）",required = true) ConnectInfo connectInfo,
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("JbotRest,getTableFromDB-------->connectInfo:{}",connectInfo);
        return jbotService.settingProject(connectInfo, response);
    }

    @ApiOperation(value = "生成项目",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名（eg:jbot）", required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "packageName", value = "包名（不包含项目名,eg:com.wllfengshu）", required = true, dataType = "string",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam"),
            @ApiResponse(code=421, message="项目名不合法"),
            @ApiResponse(code=422, message="包名不合法"),
            @ApiResponse(code=423, message="生成项目失败")
    })
    @RequestMapping(value = "/produce",method = RequestMethod.POST)
    public Map<String, Object> produceProject(
            @RequestParam(value = "projectName") String projectName,
            @RequestParam(value = "packageName") String packageName,
            @RequestBody @ApiParam(value = "数据库实体类（选择的表的集合）",required = true) DBInfo dbInfo,
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("JbotRest,produceProject-------->dbInfo:{},projectName:{},packageName:{}",dbInfo,projectName,packageName);
        return jbotService.produceProject(projectName, packageName, dbInfo, response);
    }
}
