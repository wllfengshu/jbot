package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Field;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql工具类-获取指定数据库的表结构信息
 *
 * @author wllfengshu
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MysqlUtil {

    private static final String URL = "jdbc:mysql://%s:%s/%s?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8&connectTimeout=3000&socketTimeout=6000";
    private static final String SQL_TABLE = "SELECT `table_name` FROM information_schema.`tables` WHERE table_schema = '%s' and table_type = 'base table'";
    private static final String SQL_FIELD = "SELECT `column_name` AS fieldName, `data_type` AS fieldType, `column_comment` AS columnComment, `is_nullable` AS nullable, `column_type` AS columnType , `column_key` AS columnKey FROM information_schema.`columns` WHERE table_schema = '%s' AND table_name = '%s'";

    /**
     * 向指定数据库中获取表结构
     *
     * @param connectInfoVO
     * @return
     */
    public static List<Table> getDbInfo(ConnectInfoVO connectInfoVO) throws CustomException {
        log.info("开始从用户数据库中获取表信息，connectInfo dbIp:{}", connectInfoVO.getDbIp());
        List<Table> tables = new ArrayList<>();
        try {
            //1 获取数据库连接
            String url = String.format(URL, connectInfoVO.getDbIp(), connectInfoVO.getDbPort(), connectInfoVO.getDbName());
            Class.forName("com.mysql.jdbc.Driver");
            @Cleanup Connection conn = DriverManager.getConnection(url, connectInfoVO.getDbUsername(), connectInfoVO.getDbPassword());
            @Cleanup Statement stmt = conn.createStatement();
            //2 执行sql获取指定数据库中所有的表
            String sql = String.format(SQL_TABLE, connectInfoVO.getDbName());
            log.info("get table info sql:{}", sql);
            @Cleanup ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table ti = new Table();
                ti.setTableName(rs.getString("table_name"));
                tables.add(ti);
            }
            //3 再执行sql获取每个表中所有的字段
            for (Table ti : tables) {
                sql = String.format(SQL_FIELD, connectInfoVO.getDbName(), ti.getTableName());
                log.info("get field info sql:{}", sql);
                @Cleanup ResultSet rsTemp = stmt.executeQuery(sql);
                List<Field> fis = new ArrayList<>();
                while (rsTemp.next()) {
                    Field fi = new Field();
                    fi.setFieldName(rsTemp.getString("fieldName"));
                    fi.setFieldType(rsTemp.getString("fieldType"));
                    fi.setColumnComment(rsTemp.getString("columnComment"));
                    fi.setColumnKey(rsTemp.getString("columnKey"));
                    fi.setColumnType(rsTemp.getString("columnType"));
                    fi.setNullable(rsTemp.getString("nullable"));
                    fis.add(fi);
                }
                //这里的ti是引用类型
                ti.setFields(fis);
            }
        } catch (Exception e) {
            log.error("从用户数据库中获取表信息异常", e);
            throw new CustomException("从用户数据库中获取表信息异常", CustomException.ExceptionName.CANNOT_GET_DB_INFO_FROM_USER_DB);
        }
        log.info("从用户数据库中获取数据完毕");
        return tables;
    }

}
