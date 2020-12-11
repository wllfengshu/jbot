package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import com.wllfengshu.jbot.model.Field;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql工具类-获取指定数据库的表结构信息
 *
 * @author wllfengshu
 */
@Slf4j
public class MysqlUtil {

    /**
     * 向指定数据库中获取表结构
     *
     * @param connectInfoVO
     * @return
     */
    public static List<Table> getDbInfo(ConnectInfoVO connectInfoVO) throws CustomException {
        log.info("开始从用户数据库中获取表信息，connectInfo dbIp:{}", connectInfoVO.getDbIp());
        List<Table> tables = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1 获取数据库连接
            String url = "jdbc:mysql://"
                    + connectInfoVO.getDbIp()
                    + ":"
                    + connectInfoVO.getDbPort()
                    + "/"
                    + connectInfoVO.getDbName()
                    + "?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8&connectTimeout=3000&socketTimeout=6000";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, connectInfoVO.getDbUsername(), connectInfoVO.getDbPassword());
            stmt = conn.createStatement();
            //2 执行sql获取指定数据库中所有的表
            String sql = "SELECT `table_name` FROM information_schema.`tables` WHERE table_schema = '" + connectInfoVO.getDbName() + "' and table_type = 'base table'";
            log.info("get table info sql:{}", sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table ti = new Table();
                ti.setTableName(rs.getString("table_name"));
                tables.add(ti);
            }
            //3 再执行sql获取每个表中所有的字段
            for (Table ti : tables) {
                sql = "SELECT `column_name` AS fieldName, `data_type` AS fieldType, `column_comment` AS columnComment, `is_nullable` AS nullable, `column_type` AS columnType , `column_key` AS columnKey FROM information_schema.`columns` WHERE table_schema = '" + connectInfoVO.getDbName() + "' AND table_name = '" + ti.getTableName() + "'";
                log.info("get field info sql:{}", sql);
                rs = stmt.executeQuery(sql);
                List<Field> fis = new ArrayList<>();
                while (rs.next()) {
                    Field fi = new Field();
                    fi.setFieldName(rs.getString("fieldName"));
                    fi.setFieldType(rs.getString("fieldType"));
                    fi.setColumnComment(rs.getString("columnComment"));
                    fi.setColumnKey(rs.getString("columnKey"));
                    fi.setColumnType(rs.getString("columnType"));
                    fi.setNullable(rs.getString("nullable"));
                    fis.add(fi);
                }
                //这里的ti是引用类型
                ti.setFields(fis);
            }
        } catch (Exception e) {
            log.error("从用户数据库中获取表信息异常", e);
            throw new CustomException("从用户数据库中获取表信息异常", CustomException.ExceptionName.CannotGetDbinfoFromUserDb);
        } finally {
            //4 关闭连接
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                log.error("从用户数据库中获取表信息异常 finally", e);
            }
        }
        log.info("从用户数据库中获取数据完毕");
        return tables;
    }

}
