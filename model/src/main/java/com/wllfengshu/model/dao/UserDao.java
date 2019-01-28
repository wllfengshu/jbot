package com.wllfengshu.model.dao;

import com.wllfengshu.model.entity.TUser;
import com.wllfengshu.model.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends MyMapper<TUser> {
	List<TUser> selectUsers(Map<String, Object> params);
}
