package com.wllfengshu.jbot.common.utils;
 
import com.wllfengshu.jbot.web.entity.ConnectInfo;
import com.wllfengshu.jbot.web.entity.FieldInfo;
import com.wllfengshu.jbot.web.entity.TableInfo;
import com.wllfengshu.jbot.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql工具类-获取指定数据库的表结构信息
 * @author wllfengshu
 */
public class MysqlUtil {

	private static Logger logger = LoggerFactory.getLogger(MysqlUtil.class);

	/**
	 * 向指定数据库中获取表结构
	 * @param connectInfo
	 * @return
	 */
	public static List<TableInfo> getDbInfo(ConnectInfo connectInfo)throws CustomException {
		logger.info("开始从用户数据库中获取表信息，connectInfo dbIp:{}",connectInfo.getDbIp());
		List<TableInfo> tableInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1 获取数据库连接
			String url = "jdbc:mysql://"
					+connectInfo.getDbIp()
					+":"
					+connectInfo.getDbPort()
					+"/"
					+connectInfo.getDbName()
					+"?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,connectInfo.getDbUsername(),connectInfo.getDbPassword());
			stmt = conn.createStatement();
			//2 执行sql获取指定数据库中所有的表
			String sql="SELECT `table_name` FROM information_schema.`tables` WHERE table_schema = '"+connectInfo.getDbName()+"' and table_type = 'base table'";
			logger.info("get table info sql:{}",sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				TableInfo ti = new TableInfo();
				ti.setTableName(rs.getString("table_name"));
				tableInfos.add(ti);
			}
			//3 再执行sql获取每个表中所有的字段
			for(TableInfo ti:tableInfos) {
				sql = "SELECT `column_name` AS fieldName, `data_type` AS fieldType, `column_comment` AS columnComment, `is_nullable` AS isNullable, `column_type` AS columnType , `column_key` AS columnKey FROM information_schema.`columns` WHERE table_schema = '"+connectInfo.getDbName()+"' AND table_name = '"+ti.getTableName()+"'";
				logger.info("get field info sql:{}",sql);
				rs = stmt.executeQuery(sql);
				List<FieldInfo> fis = new ArrayList<>();
				while (rs.next()) {
					FieldInfo fi = new FieldInfo();
					fi.setFieldName(rs.getString("fieldName"));
					fi.setFieldType(rs.getString("fieldType"));
					fi.setColumnComment(rs.getString("columnComment"));
					fi.setColumnKey(rs.getString("columnKey"));
					fi.setColumnType(rs.getString("columnType"));
					fi.setIsNullable(rs.getString("isNullable"));
					fis.add(fi);
				}
				//这里的ti是引用类型
				ti.setFields(fis);
			}
		}catch (Exception e){
			logger.error("从用户数据库中获取表信息异常",e);
			throw new CustomException("从用户数据库中获取表信息异常", CustomException.ExceptionName.CannotGetDbinfoFromUserDb);
		}finally {
			//4 关闭连接
			try {
				if (rs!=null){
					rs.close();
				}
				if (stmt!=null){
					stmt.close();
				}
				if (conn!=null){
					conn.close();
				}
			}catch (Exception e){
				logger.error("从用户数据库中获取表信息异常 finally",e);
			}
		}
		logger.info("从用户数据库中获取数据完毕");
		return tableInfos;
	}

}
