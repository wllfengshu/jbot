package com.wllfengshu.model.service;

import com.wllfengshu.model.entity.TUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
	Map<String, Object>  insertUser(TUser user);
	Map<String, Object>  deleteUser(Integer id);
	Map<String, Object>  updateUser(TUser user);
	Map<String, Object>  selectUsers(Map<String, Object> params);
	Map<String, Object>  selectUser(Integer id);
}
