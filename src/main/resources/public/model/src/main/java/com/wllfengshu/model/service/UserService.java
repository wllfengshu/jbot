package com.wllfengshu.model.service;

import com.wllfengshu.model.entity.TUser;
import com.wllfengshu.model.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    Map<String, Object> insert(TUser user, String sessionId) throws CustomException;

    Map<String, Object> delete(Integer id, String sessionId) throws CustomException;

    Map<String, Object> update(TUser user, String sessionId) throws CustomException;

    Map<String, Object> select(Integer id, String sessionId) throws CustomException;

    Map<String, Object> selects(Map<String, Object> params, String sessionId) throws CustomException;

}
