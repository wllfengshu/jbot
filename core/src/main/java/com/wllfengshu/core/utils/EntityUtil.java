package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

public class EntityUtil {
    /**
     * 实体类难在怎么把数据库中字段的类型，转换为java中的类型，我们使用Set
     * @param projectName
     * @param packageName
     * @param tableInfo
     * @return
     */
    public static String genEntity(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String daoClassName=packageName+"."+projectName+".dao."+tableNameFUDTU+"Dao";
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        StringBuffer rest=new StringBuffer();

        return rest.toString();
    }
}
