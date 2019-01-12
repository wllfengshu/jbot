package com.wllfengshu.model.dao;

import com.wllfengshu.model.entity.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
	void insertUser(TUser user);
	void deleteUser(@Param("id") Integer id);
	void updateUser(TUser user);
	List<TUser> selectUsers(Map<String, Object> params);
	TUser selectUser(@Param("id") Integer id);
}
