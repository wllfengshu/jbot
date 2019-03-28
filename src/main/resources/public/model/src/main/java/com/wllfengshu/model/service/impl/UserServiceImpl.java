package com.wllfengshu.model.service.impl;

import com.wllfengshu.model.dao.UserDao;
import com.wllfengshu.model.entity.TUser;
import com.wllfengshu.model.exception.CustomException;
import com.wllfengshu.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insert(TUser entity,String sessionId)throws CustomException {
        logger.info("insert entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        userDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id,String sessionId)throws CustomException{
        logger.info("delete id:{}",id);
        Map<String, Object> result = new HashMap<>();
        userDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(TUser entity,String sessionId)throws CustomException{
        logger.info("update entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        userDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id,String sessionId)throws CustomException{
        logger.info("select id:{}",id);
        Map<String, Object> result = new HashMap<>();
        result.put("data",userDao.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selects(Map<String, Object> params,String sessionId)throws CustomException{
        logger.info("selects params:{}",params);
        Map<String, Object> result = new HashMap<>();
        result.put("data",userDao.selectUsers(params));
        result.put("total",userDao.selectUsersCount(params));
        return result;
    }

}
