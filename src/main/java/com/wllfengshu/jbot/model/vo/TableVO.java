package com.wllfengshu.jbot.model.vo;

import com.wllfengshu.jbot.model.po.Table;
import lombok.Data;

/**
 * 每张表的model(下面以t_user表为例进行注释)
 *
 * @author wllfengshu
 */
@Data
public class TableVO {

    /**
     * 首字母大写、驼峰形式的表名,eg:User
     */
    private String tableNameFUDTU;

    /**
     * 首字母小写、驼峰形式的表名,eg:user
     */
    private String tableNameFLDTU;

    /**
     * 待生成entity的class路径,eg:com.wllfenghu.dns.entity.UserEntity
     */
    private String entityClassPack;

    /**
     * 待生成dao的class路径,eg:com.wllfenghu.dns.dao.UserDao
     */
    private String daoClassPack;

    /**
     * 待生成service的class路径,eg:com.wllfenghu.dns.service.UserService
     */
    private String serviceClassPack;

    /**
     * 待生成serviceImpl的class路径,eg:com.wllfenghu.dns.service.impl.UserServiceImpl
     */
    private String serviceImplClassPack;

    /**
     * 表信息
     */
    private Table table;

}
