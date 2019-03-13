package com.wllfengshu.common.model;

import java.util.List;

/**
 * 每个请求的model(下面以dns项目为例进行注释)
 * @author wllfengshu
 */
public class RequestModel {

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
    private List<TableModel> tableModels;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getRestPack() {
        return restPack;
    }

    public void setRestPack(String restPack) {
        this.restPack = restPack;
    }

    public String getEntityPack() {
        return entityPack;
    }

    public void setEntityPack(String entityPack) {
        this.entityPack = entityPack;
    }

    public String getDaoPack() {
        return daoPack;
    }

    public void setDaoPack(String daoPack) {
        this.daoPack = daoPack;
    }

    public String getServicePack() {
        return servicePack;
    }

    public void setServicePack(String servicePack) {
        this.servicePack = servicePack;
    }

    public String getServiceImplPack() {
        return serviceImplPack;
    }

    public void setServiceImplPack(String serviceImplPack) {
        this.serviceImplPack = serviceImplPack;
    }

    public String getAopPack() {
        return aopPack;
    }

    public void setAopPack(String aopPack) {
        this.aopPack = aopPack;
    }

    public String getConfigsPack() {
        return configsPack;
    }

    public void setConfigsPack(String configsPack) {
        this.configsPack = configsPack;
    }

    public String getExceptionPack() {
        return exceptionPack;
    }

    public void setExceptionPack(String exceptionPack) {
        this.exceptionPack = exceptionPack;
    }

    public String getUtilsPack() {
        return utilsPack;
    }

    public void setUtilsPack(String utilsPack) {
        this.utilsPack = utilsPack;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getJavaPath() {
        return javaPath;
    }

    public void setJavaPath(String javaPath) {
        this.javaPath = javaPath;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    public String getPackageBasePath() {
        return packageBasePath;
    }

    public void setPackageBasePath(String packageBasePath) {
        this.packageBasePath = packageBasePath;
    }

    public List<TableModel> getTableModels() {
        return tableModels;
    }

    public void setTableModels(List<TableModel> tableModels) {
        this.tableModels = tableModels;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "projectName='" + projectName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", restPack='" + restPack + '\'' +
                ", entityPack='" + entityPack + '\'' +
                ", daoPack='" + daoPack + '\'' +
                ", servicePack='" + servicePack + '\'' +
                ", serviceImplPack='" + serviceImplPack + '\'' +
                ", aopPack='" + aopPack + '\'' +
                ", configsPack='" + configsPack + '\'' +
                ", exceptionPack='" + exceptionPack + '\'' +
                ", utilsPack='" + utilsPack + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", docPath='" + docPath + '\'' +
                ", javaPath='" + javaPath + '\'' +
                ", resourcesPath='" + resourcesPath + '\'' +
                ", packageBasePath='" + packageBasePath + '\'' +
                ", tableModels=" + tableModels +
                '}';
    }
}
