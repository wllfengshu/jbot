package com.wllfengshu.jbot.rest;

import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.service.JbotService;
import io.swagger.annotations.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author wllfengshu
 */
@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class JbotRest {

    @NonNull
    private JbotService jbotService;

    @ApiOperation(value = "初始化项目", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @GetMapping("/init")
    public Map<String, Object> initProject(
            HttpServletRequest request,
            HttpServletResponse response) throws CustomException {
        log.info("JbotRest,init");
        return jbotService.initProject();
    }

    @ApiOperation(value = "设置项目", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @PostMapping("/setting")
    public Map<String, Object> settingProject(
            @RequestBody @ApiParam(value = "数据库连接实体类（数据库连接信息）", required = true) ConnectInfoVO connectInfoVO,
            HttpServletRequest request,
            HttpServletResponse response) throws CustomException {
        log.info("JbotRest,getTableFromDB-------->connectInfoVO:{}", connectInfoVO);
        return jbotService.settingProject(connectInfoVO, response);
    }

    @ApiOperation(value = "生成项目", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名（eg:jbot）", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "packageName", value = "包名（不包含项目名,eg:com.wllfengshu）", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @PostMapping("/produce")
    public Map<String, Object> produceProject(
            @RequestParam(value = "projectName") String projectName,
            @RequestParam(value = "packageName") String packageName,
            @RequestBody @ApiParam(value = "选择的表的集合", required = true) List<Table> tables,
            HttpServletRequest request,
            HttpServletResponse response) throws CustomException {
        log.info("JbotRest,produceProject-------->tables:{},projectName:{},packageName:{}", tables, projectName, packageName);
        return jbotService.produceProject(projectName, packageName, tables, response);
    }
}
