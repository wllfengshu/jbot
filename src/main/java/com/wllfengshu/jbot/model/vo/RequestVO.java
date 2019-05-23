package com.wllfengshu.jbot.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 每个请求的model(下面以dns项目为例进行注释)
 *
 * @author wllfengshu
 */
@Data
public class RequestVO {

    /**
     * 待生成项目名，eg:dns
     */
    private String projectName;

    /**
     * 待生成包名，eg:com.wllfenghu
     */
    private String packageName;

    /**
     * 待生成rest的包名,eg:com.wllfenghu.dns.rest
     */
    private String restPack;

    /**
     * 待生成entity的包名,eg:com.wllfenghu.dns.entity
     */
    private String entityPack;

    /**
     * 待生成dao的包名,eg:com.wllfenghu.dns.dao
     */
    private String daoPack;

    /**
     * 待生成service的包名,eg:com.wllfenghu.dns.service
     */
    private String servicePack;

    /**
     * 待生成serviceImpl的包名,eg:com.wllfenghu.dns.service.impl
     */
    private String serviceImplPack;

    /**
     * 待生成aop的包名：eg:com.wllfengshu.dns.aop
     */
    private String aopPack;

    /**
     * 待生成configs的包名：eg:com.wllfengshu.dns.configs
     */
    private String configsPack;

    /**
     * 待生成exception的包名：eg:com.wllfengshu.dns.exception
     */
    private String exceptionPack;

    /**
     * 待生成utils的包名：eg:com.wllfengshu.dns.utils
     */
    private String utilsPack;

    /**
     * 待生成项目路径，eg:/home/listen/Apps/dns
     */
    private String projectPath;

    /**
     * 待生成项目doc文档存在路径，eg:/home/listen/Apps/dns/doc
     */
    private String docPath;

    /**
     * 待生成项目java文件夹所在路径，eg:/home/listen/Apps/dns/src/main/java
     */
    private String javaPath;

    /**
     * 待生成项目resources文件夹所在路径，eg:/home/listen/Apps/dns/src/main/resources
     */
    private String resourcesPath;

    /**
     * 待生成项目包路径(基础路径，包括最后的一个项目名)，eg:/home/listen/Apps/dns/src/main/java/com/wllfenghu/dns
     */
    private String packageBasePath;

    /**
     * 每张表所需要的信息
     */
    private List<TableVO> tableVOs;

}
