package com.wllfengshu.model.rest;

import com.wllfengshu.model.entity.TUser;
import com.wllfengshu.model.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "添加用户",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam")
    })
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Map<String, Object> insertUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody TUser user){
        logger.info("UserRest,insertUser-------->user:{}",user);
        return userService.insertUser(user);
    }

    @ApiOperation(value = "删除用户",httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam")
    })
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public Map<String, Object> deleteUser(
            @PathVariable("id") Integer id,
            HttpServletRequest request,
            HttpServletResponse response){
        logger.info("UserRest,deleteUser-------->id:{}",id);
        return userService.deleteUser(id);
    }

    @ApiOperation(value = "修改用户",httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam")
    })
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Map<String, Object> updateUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody TUser user){
        logger.info("UserRest,updateUser-------->user:{}",user);
        return userService.updateUser(user);
    }

    @ApiOperation(value = "获取用户详情",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam")
    })
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Map<String, Object> selectUser(
            @PathVariable("id") Integer id,
            HttpServletRequest request,
            HttpServletResponse response){
        logger.info("UserRest,selectUser-------->id:{}",id);
        return userService.selectUser(id);
    }

    @ApiOperation(value = "查询用户",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "createTimeBegin", value = "创建时间开始(格式：yyyy-MM-dd HH:mm:ss)", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "createTimeEnd", value = "创建时间结束(格式：yyyy-MM-dd HH:mm:ss)", dataType = "string",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code=400, message="IllegalParam")
    })
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String, Object> selectUsers(
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "createTimeBegin",required = false) String createTimeBegin,
            @RequestParam(value = "createTimeEnd",required = false) String createTimeEnd,
            HttpServletRequest request,
            HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        params.put("createTimeBegin",createTimeBegin);
        params.put("createTimeEnd",createTimeEnd);
        logger.info("UserRest,selectUser-------->username:{},createTimeBegin,createTimeEnd",username,createTimeBegin,createTimeEnd);
        return userService.selectUsers(params);
    }
}
